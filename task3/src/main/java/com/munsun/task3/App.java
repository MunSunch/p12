package com.munsun.task3;

import com.munsun.task3.FileVisitor.MultiThreadFileVisitor;
import com.munsun.task3.FileVisitor.SingleThreadFileVisitor;
import com.munsun.task3.enums.AppMode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        AppMode type = AppMode.valueOf(args[0]);
        String inputDirectory = args[1];
        String outputDirectory = args[2];

        checkExistsDirectories(inputDirectory, outputDirectory);

        long start = 0;
        long end = 0;

        switch (type) {
            case AppMode.SINGLE:
                logger.info("Running in single-thread mode");
                start = System.currentTimeMillis();
                Files.walkFileTree(Path.of(inputDirectory), new SingleThreadFileVisitor(outputDirectory));
                end = System.currentTimeMillis();
                break;
            case AppMode.PARALLEL:
                logger.info("Running in parallel mode");
                ExecutorService executorService = Executors.newFixedThreadPool(100);
                start = System.currentTimeMillis();
                Files.walkFileTree(Path.of(inputDirectory), new MultiThreadFileVisitor(executorService, outputDirectory));
                executorService.shutdown();
                try {
                    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                end = System.currentTimeMillis();
                break;
        }

        logger.info("Elapsed time: " + (end - start) + " ms");
    }

    private static void checkExistsDirectories(String inputDirectory, String outputDirectory) {
        if(!Files.exists(Path.of(inputDirectory))) {
            throw new IllegalArgumentException("Input directory doesn't exists");
        }
        if(!Files.exists(Path.of(outputDirectory))) {
            throw new IllegalArgumentException("Output directory doesn't exists");
        }
    }
}
