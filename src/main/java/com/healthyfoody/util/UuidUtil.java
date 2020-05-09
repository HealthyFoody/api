package com.healthyfoody.util;

import com.healthyfoody.validation.annotations.ValidUUID;

import java.util.List;
import java.util.UUID;

public abstract class UuidUtil {

    public static final String PATTERN = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";

    public static class Wrapper<T> {
        @ValidUUID
        T id;

        public Wrapper(T id){
            this.id = id;
        }

        public T value() {
            return this.id;
        }
    }
}
