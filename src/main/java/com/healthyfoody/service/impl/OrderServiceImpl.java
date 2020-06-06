package com.healthyfoody.service.impl;

import static com.healthyfoody.entity.OrderStatus.CANCELLED;
import static com.healthyfoody.entity.OrderStatus.CHARGED;
import static com.healthyfoody.entity.OrderStatus.FULFILLED;
import static com.healthyfoody.entity.OrderStatus.PLACED;
import static com.healthyfoody.entity.OrderStatus.PREPARED;
import static com.healthyfoody.entity.OrderStatus.READY_FOR_PICKUP;
import static com.healthyfoody.entity.OrderStatus.REFUNDED;
import static com.healthyfoody.entity.OrderStatus.SENT;

import java.time.*;
import java.util.ArrayList;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.healthyfoody.dto.request.OrderRequest;
import com.healthyfoody.dto.response.OrderResponse;
import com.healthyfoody.entity.*;
import com.healthyfoody.entity.Task.DeliveryTask;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.mapper.OrderMapper;
import com.healthyfoody.repository.jpa.OrderRepository;
import com.healthyfoody.service.*;

@Service
public class OrderServiceImpl implements OrderService {

	private final static LocalTime OPENING_HOUR = LocalTime.of(12, 0);
	private final static LocalTime CLOSING_HOUR = LocalTime.of(20, 0);

	@Autowired
	StoreService storeService;
	
	@Autowired
	CustomerService customerService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CartService cartService;

	@Autowired
	TrackingService trackingService;
	
	@Autowired
	OrderMapper orderMapper;

	@Override
	public Page<OrderResponse> findCustomerOrders(UUID customerId, int page, int size) {
		return null;
	}

	@Override
	@Transactional
	public OrderResponse placeOrder(UUID cartId, OrderRequest request, PaymentType type) {

		Order order = new Order();

		// TODO: Validate customer and store
		Store store = storeService.findEntityById(request.getStoreId());
		Customer customer = customerService.findEntityById(request.getCustomerId());

		// VALIDA DIRECCION
		storeService.inRangeOfStore(store.getId(), request.getLatitude(), request.getLongitude());

		// FIXME: argument should be mapped?
		order.setCustomer(customer);
		order.setStore(store);
		order.setAddress(request.getAddress());
		order.setLatitude(request.getLatitude());
		order.setLongitude(request.getLongitude());

		order.setPaymentType(type);
		order.setOrderDate(LocalDateTime.now());

		LocalDateTime progHour = null;
		DeliveryTask firstTask;

		switch (type) {
		case UPON_DELIVERY:
			if (orderRepository.countOrdersByCustomerIdAndStatus(order.getCustomer().getId(), FULFILLED) < 5)
				throw new RuntimeException("Contraentrega no valido para el número de clientes");

			firstTask = DeliveryTask.PREPARING;
			break;
		case CARD_PAYMENT:
			progHour = order.getProgrammedFor();
			firstTask = DeliveryTask.AWAITING_PAYMENT;
			break;
		default:
			throw new RuntimeException("Tipo de pago no valido");
		}

		if (progHour == null)
			progHour = order.getOrderDate();
		// VALIDA QUE LA HORA SEA VALIDA
		if (!validOrderHour(progHour)) {
			throw new RuntimeException("La hora seleccionada no es válida");
		}

		order.setProgrammedFor(progHour);

		// PREPARAR TRACKING
		Tracking tracking = trackingService.createTracking(firstTask, 1);

		order.setTracking(new ArrayList<>());
		order.getTracking().add(tracking);
		order.setStatus(PLACED);

		// OBTIENE EL CARRITO SI FUE VALIDADO

		order.setOrderProducts(cartService.processCart(cartId, store, request.getProgramedFor().toLocalTime()));

		order.getOrderProducts().forEach(x -> x.setOrder(order));

		Order result = orderRepository.save(order);
		return orderMapper.toResponse(result);
	}

	@Override
	@Transactional
	public OrderResponse payOrder(UUID id, Object token) {
		Order order = findEntityById(id);
		Order result = null;
		if (validOperation(order.getStatus(), CHARGED, order.getPaymentType())) {

			// TODO: Call paymentGateWay and make charge

			order.setStatus(CHARGED);
			Tracking tracking = trackingService.createTracking(DeliveryTask.PREPARING, 2);

			order.getTracking().add(tracking);

			result = orderRepository.save(order);
		}
		return orderMapper.toResponse(result);
	}

	@Override
	public void cancelOrder(UUID id, boolean exceptional) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void updateStatus(UUID id, OrderStatus newStatus) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Order findEntityById(UUID id) throws ResourceNotFoundException {
		return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Order.class));
	}

	// VALIDA EL HORARIO DE ATENCION
	private boolean validOrderHour(LocalDateTime dateTime) {

		// DEBE SER PARA EL DIA DE HOY
		if (!dateTime.toLocalDate().isEqual(LocalDate.now()))
			return false;
		// NO HAY ATENCION LOS DOMINGOS
		if (dateTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return false;
		}
		LocalTime time = dateTime.toLocalTime();
		return !time.isBefore(OPENING_HOUR) && !time.isAfter(CLOSING_HOUR);
	}

	private boolean validOperation(OrderStatus currentStatus, OrderStatus newStatus, PaymentType type) {
		switch (type) {
		case CARD_PAYMENT:
			return validCardOperation(currentStatus, newStatus);
		case UPON_DELIVERY:
			return validCashOperation(currentStatus, newStatus);
		default:
			throw new RuntimeException("La operación no es válida");
		}
	}
	
	private boolean validCardOperation(OrderStatus currentStatus, OrderStatus newStatus) {
		switch (currentStatus) {
		case PLACED:
			return newStatus == CANCELLED || newStatus == CHARGED;
		case CHARGED:
			return newStatus == PREPARED || newStatus == CANCELLED;
		case PREPARED:
			return newStatus == SENT;
		case SENT:
			return newStatus == READY_FOR_PICKUP;
		case READY_FOR_PICKUP:
			return newStatus == FULFILLED;
		case CANCELLED:
			return newStatus == REFUNDED;
		case FULFILLED:
		case REFUNDED:
			return false;
		default:
			throw new RuntimeException("La operación no es válida");
		}
	}
	
	private boolean validCashOperation(OrderStatus currentStatus, OrderStatus newStatus) {
		switch (currentStatus) {
		case PLACED:
			return newStatus == CANCELLED || newStatus == PREPARED;
		case PREPARED:
			return newStatus == SENT;
		case SENT:
			return newStatus == READY_FOR_PICKUP;
		case READY_FOR_PICKUP:
			return newStatus == FULFILLED;
		case FULFILLED:
			return false;
		default:
			throw new RuntimeException("La operación no es válida");
		}
	}


}
