package com.healthyfoody.api.orders;

import com.healthyfoody.api.common.ConverterUtil;
import com.healthyfoody.api.customers.Customer;
import com.healthyfoody.api.customers.CustomerService;
import com.healthyfoody.api.location.Store;
import com.healthyfoody.api.location.StoreService;
import com.healthyfoody.api.product.ProductType;
import com.healthyfoody.api.product.entity.Combo;
import com.healthyfoody.api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	@Autowired
	StoreService storeService;

	@GetMapping("/get")
	public ResponseEntity<?> getAllCustomerOrder(@Valid @RequestParam("customer") UUID customerId, @RequestParam(defaultValue = "0") Integer page){

		return ResponseEntity.ok(orderService.findOrder(customerId, page));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getOrderById(@Valid @PathVariable("id") UUID id){

		return orderService.findOrderById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/post")
	public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderForm form){

		Order order = new Order();
		Customer customer = customerService.findById(form.customerId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cliente no existe"));
		List<OrderDetail> details = new ArrayList<>();

		Store store = storeService.findById(form.storeId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sede no valida"));

		form.products.stream().map(item -> {
			OrderDetail detail = new OrderDetail();

			detail.setOrder(order);

			detail.setProduct(productService.findById(item.product).orElse(null));
			detail.setQuantity(item.quantity);

			if (item.combo != null){

				Combo combo	= (Combo) productService.findTypedById(item.combo, ProductType.COMBO)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No es un combo"));

				// confirma que el plato forma parte del combo
				if (productService.isPartOfCombo(detail.getProduct().getId(), combo.getId())) {
					detail.setCombo(combo);
					detail.setBundledQuantity(item.bundledQuantity);
				}
			}

			return detail;
		}).collect(Collectors.toList());

		order.setCustomer(customer);
		order.setOrderDate(LocalDateTime.now());
		order.setProgrammedFor(ConverterUtil.toLocalDateTime(form.programedFor));
		order.setStore(store);
		order.setOrderDetails(details);
		order.setOrderDate(LocalDateTime.now());


		UUID orderId = orderService.placeOrder(order);

		return ResponseEntity.ok(orderId);
	}

	@PutMapping("/put")
	public ResponseEntity<?> modifyOrder(@Valid @RequestBody OrderForm form){

		return null;
	}
}
