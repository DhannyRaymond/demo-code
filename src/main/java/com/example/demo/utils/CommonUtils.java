package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;

public class CommonUtils {

    final static ObjectMapper DEFAULT_MAPPER = new ObjectMapper();

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

    public static String convertUsingJackson(Object data) throws JsonProcessingException {
        if (Objects.isNull(data)) {
            return null;
        }
        return DEFAULT_MAPPER.writeValueAsString(data);
    }
}
