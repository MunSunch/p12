package com.munsun.task5.server;

import jakarta.servlet.Servlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

public class TomcatServer {
    private final Servlet dispatcherServlet;

    public TomcatServer(Servlet dispatcherServlet) {
        this.dispatcherServlet = dispatcherServlet;
    }

    public void start(int port) throws IOException, LifecycleException {
        var tomcat = new Tomcat();
        var baseDir = Files.createTempDirectory("tomcat");
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        String pathContext = "/";
        String docBase = ".";
        String nameServlet = "dispatcher";
        var context = tomcat.addContext(pathContext, docBase);
        tomcat.addServlet(pathContext, nameServlet, dispatcherServlet.getClass().getName());
        context.addServletMappingDecoded(pathContext, nameServlet);

        var connector = new Connector();
        connector.setPort(port);
        tomcat.setConnector(connector);
        tomcat.getHost().setAppBase(docBase);

        tomcat.start();
        tomcat.getServer().await();
    }
}
