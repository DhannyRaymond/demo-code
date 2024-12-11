package com.example.demo.utils;

import java.util.Map;
import java.util.Objects;

public class CommonUtils {

    public static <T> T getMapValue(String key, Map dataMap) {
        T value = null;
        if (Objects.nonNull(dataMap)) {
            Object objValue = dataMap.get(key);
            if (Objects.nonNull(objValue)) {
                value = convertObject(objValue);
            }
        }
        return value;
    }

    private static <T> T convertObject(Object object) {
        return (T) object;
    }
}
