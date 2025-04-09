package com.munsun.task3.FileVisitor;

import com.munsun.task2.service.CalculatorService;
import com.munsun.task2.service.impl.DefaultCalculatorService;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class SingleThreadFileVisitor implements FileVisitor<Path> {
    private final Logger logger = Logger.getLogger(SingleThreadFileVisitor.class.getName());

    private final CalculatorService service = new DefaultCalculatorService();
    private final String outputDirectory;

    public SingleThreadFileVisitor(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        logger.info("Processing file: " + file.getFileName().toString());
        try (var stream = service.calculateCredit(Files.newInputStream(file))) {
            String newFileName = "paymentschedule_" + file.getFileName();
            Files.copy(stream, Path.of(outputDirectory, newFileName), StandardCopyOption.REPLACE_EXISTING);
            logger.info("Create file " + outputDirectory + newFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
