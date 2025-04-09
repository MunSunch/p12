package com.munsun.task5.servlet;

import com.munsun.task5.advice.ExceptionAdvice;
import com.munsun.task5.dto.utils.ResponseEntity;
import com.munsun.task5.servlet.handler.Handler;
import com.munsun.task5.servlet.handler.impl.CalculateCreditHandler;
import com.munsun.task5.utils.enums.ContentType;
import com.munsun.task5.utils.enums.HttpStatusCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DispatchRequestHandler {
    private List<Handler> handlers = List.of(new CalculateCreditHandler());

    public void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Optional<Handler> handlerOptional = handlers.stream()
                    .filter(handler -> handler.isMatch(req))
                    .findFirst();
            if (handlerOptional.isEmpty()) {
                resp.setStatus(HttpStatusCode.NOT_FOUND.getStatusCode());
                resp.getWriter().println("Page not found");
            }
            ResponseEntity responseEntity = handlerOptional.get().handle(req);
            resp.setStatus(responseEntity.getStatusCode().getStatusCode());
            resp.setContentType(ContentType.XML.getContentType());
            resp.getWriter().println(responseEntity.getResponseBody());
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpStatusCode.INTERNAL_SERVER_ERROR.getStatusCode());
            resp.getWriter().println(ExceptionAdvice.handleException(e, HttpStatusCode.INTERNAL_SERVER_ERROR.getStatusCode()));
        }
    }
}
