package com.munsun.task5.utils.enums;

public enum ContentType {
    JSON("application/json"), XML("application/xml");

    private String contentType;

    ContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getContentType() {
        return contentType;
    }
}
