package com.healthyfoody.service;

import com.healthyfoody.entity.Task;

public interface TaskService extends EntityFetchService<Task, Long> {
	
	Task preparing();
	Task sending();
	Task awaitingPayment();
	
	Task getTask(DeliveryTasks task);
	
	Task findByCode(Integer code);
	
	public static enum DeliveryTasks {
		PREPARING,
		SENDING,
		AWAITING_PAYMENT;
	}
}
