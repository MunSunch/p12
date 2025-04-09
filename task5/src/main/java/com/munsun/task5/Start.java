package com.munsun.task5;

import com.munsun.task5.server.TomcatServer;
import com.munsun.task5.servlet.Servlet;
import org.apache.catalina.LifecycleException;

import java.io.IOException;

public class Start {
    public static void main(String[] args) throws LifecycleException, IOException {
        new TomcatServer(new Servlet()).start(8080);
    }
}
