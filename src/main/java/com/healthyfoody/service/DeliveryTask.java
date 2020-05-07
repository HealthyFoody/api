package com.healthyfoody.service;

public enum DeliveryTask {
    PREPARING(1),
    SENDING(2),
    AWAITING_PAYMENT(3);

    public final int code;

    DeliveryTask(int code) {
        this.code = code;
    }

    public static DeliveryTask valueOfCode(int code) {
        for (DeliveryTask e : values()) {
            if (e.code == code)
                return e;
        }
        return null;
    }
}
