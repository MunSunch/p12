package com.munsun.task5.dto.utils;

import com.munsun.task5.utils.enums.HttpStatusCode;

public class ResponseEntity {
    private HttpStatusCode statusCode;
    private String responseBody;

    public ResponseEntity(HttpStatusCode statusCode, String responseBody) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
