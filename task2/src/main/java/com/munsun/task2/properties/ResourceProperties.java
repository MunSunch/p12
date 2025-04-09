package com.munsun.task2.properties;

import java.util.Properties;

public class ResourceProperties {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(ResourceProperties.class.getResourceAsStream("/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T getProperty(String key, Class<T> clazz) {
        String value = properties.getProperty(key);
        if (clazz == Integer.class) {
            return clazz.cast(Integer.parseInt(value));
        }
        if(clazz == String.class) {
            return clazz.cast(value);
        }
        if (clazz == Boolean.class || clazz == boolean.class) {
            return clazz.cast(Boolean.parseBoolean(value));
        }
        throw new IllegalArgumentException("Unsupported type: " + clazz);
    }
}
