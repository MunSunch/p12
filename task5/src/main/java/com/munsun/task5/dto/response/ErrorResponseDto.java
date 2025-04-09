package com.munsun.task5.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorResponseDto {
    @XmlElement(name = "message")
    private String message;

    @XmlElement(name = "status_code")
    private String statusCode;

    @XmlElement(name = "exception")
    private String exception;

    @XmlElement(name = "timestamp")
    private String timestamp;

    public ErrorResponseDto() {}

    public ErrorResponseDto(String message, String statusCode, String exception, String timestamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.exception = exception;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}