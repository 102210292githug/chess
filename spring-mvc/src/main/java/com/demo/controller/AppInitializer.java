package com.demo.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInitializer implements ServletContextListener {

    private Thread serverThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        serverThread = new Thread(() -> ServerAI.main(new String[]{}));
        serverThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Clean up resources, stop the server, etc.
        if (serverThread != null && serverThread.isAlive()) {
            serverThread.interrupt(); // Or other methods to stop your server
        }
    }
}
