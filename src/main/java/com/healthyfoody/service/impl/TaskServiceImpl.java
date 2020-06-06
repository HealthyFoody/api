package com.healthyfoody.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyfoody.entity.Task;
import com.healthyfoody.exception.InvalidOperationException;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.repository.jpa.TaskRepository;
import com.healthyfoody.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public Task findEntityById(Long id) throws ResourceNotFoundException {
		return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Task.class));
	}

	@Override
	public Task preparing() {
		return findByCode(1);
	}

	@Override
	public Task sending() {
		return findByCode(2);
	}

	@Override
	public Task awaitingPayment() {
		return findByCode(3);
	}

	@Override
	public Task getTask(DeliveryTasks task) {
		switch (task) {
		case PREPARING:
			return preparing();
		case SENDING:
			return sending();
		case AWAITING_PAYMENT:
			return awaitingPayment();
		default:
			throw new InvalidOperationException();
		}
	}

	@Override
	public Task findByCode(Integer code) {
		return taskRepository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException(code, "código", Task.class));
	}

}
