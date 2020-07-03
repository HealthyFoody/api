package com.healthyfoody.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public final class GUIDUtil {

    private GUIDUtil() {} 
    
    public static final String PATTERN = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";

    public static boolean validGUID(UUID value) {
        return value.toString().matches(GUIDUtil.PATTERN);
    }

    public static boolean validGUID(String value) {
        return value.matches(GUIDUtil.PATTERN);
    }
}
