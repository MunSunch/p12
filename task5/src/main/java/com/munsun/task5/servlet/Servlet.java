package com.munsun.task5.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Servlet extends HttpServlet {
    private DispatchRequestHandler requestHandler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.requestHandler = new DispatchRequestHandler();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestHandler.handle(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
