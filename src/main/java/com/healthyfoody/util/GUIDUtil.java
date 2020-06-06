package com.healthyfoody.util;

import com.healthyfoody.validation.annotations.GUID;

public final class GUIDUtil {

    private GUIDUtil() {} 
    
    public static final String PATTERN = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";

    public static class GUIDWrapper<T> {
        @GUID
        T id;

        public GUIDWrapper(T id){
            this.id = id;
        }

        public T value() {
            return this.id;
        }
    }
}
