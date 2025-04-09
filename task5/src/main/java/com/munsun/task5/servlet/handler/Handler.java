package com.munsun.task5.servlet.handler;

import com.munsun.task5.dto.utils.ResponseEntity;
import com.munsun.task5.utils.enums.ContentType;
import com.munsun.task5.utils.enums.HttpMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class Handler {
    private HttpMethod httpMethod;
    private ContentType contentType;
    private String url;

    public Handler(HttpMethod httpMethod, ContentType contentType, String url) {
        this.httpMethod = httpMethod;
        this.contentType = contentType;
        this.url = url;
    }

    public boolean isMatch(HttpServletRequest req) {
        String url = req.getRequestURL().toString();
        String contentType = req.getContentType();
        String httpRequest = req.getMethod();
        return url.endsWith(this.url)
                && this.contentType.getContentType().equals(contentType)
                &&  this.httpMethod.name().equals(httpRequest);
    }

    public abstract ResponseEntity handle(HttpServletRequest req) throws IOException;

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public String getUrl() {
        return url;
    }
}