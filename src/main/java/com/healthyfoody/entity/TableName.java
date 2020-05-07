package com.healthyfoody.entity;

public abstract class TableName {

	private TableName() {
	}

	public final static String ADDRESS = "addresses";
	public final static String CUSTOMER = "customers";
	public final static String USER = "users";
	public final static String ROLE = "roles";

	public final static String ORDER = "orders";
	public final static String ORDER_PRODUCT = "order_product";
	public final static String ORDER_PRODUCT_COMPONENT = "order_product_components";

	public final static String PAYMENT = "payments";
	public final static String TRACKING = "trackings";
	public final static String COURIER = "couriers";
	public final static String TASK = "tasks";

	public final static String STORE = "stores";
	public final static String STOCK = "stocks";

	public final static String CATEGORY = "categories";
	public final static String CATEGORY_PRODUCT = "category_products";
	public final static String PRODUCT = "products";
	public final static String MEAL = "meals";
	public final static String COMBO = "combos";
	public final static String COMBO_GROUP_MEAL = "combo_group_meals";
	public final static String MEAL_GROUP = "meal_groups";

	public final static String SALE_TIME_SPAN = "sale_time_spans";
}
