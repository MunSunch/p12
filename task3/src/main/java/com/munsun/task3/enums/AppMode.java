package com.munsun.task3.enums;

public enum AppMode {
    SINGLE("single"),
    PARALLEL("parallel");

    private final String name;

    AppMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
