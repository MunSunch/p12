package com.munsun.task3.FileVisitor;

import com.munsun.task2.service.CalculatorService;
import com.munsun.task2.service.impl.DefaultCalculatorService;
import jakarta.xml.bind.JAXBException;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

public class MultiThreadFileVisitor implements FileVisitor<Path> {
    private final Logger logger = Logger.getLogger(MultiThreadFileVisitor.class.getName());
    private final CalculatorService service = new DefaultCalculatorService();
    private final ExecutorService executor;
    private final String outputDirectory;

    public MultiThreadFileVisitor(ExecutorService executorService, String outputDirectory) {
        this.executor = executorService;
        this.outputDirectory = outputDirectory;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        CompletableFuture
                .supplyAsync(() -> openFile(file), executor)
                .thenApply(this::processFile)
                .thenAccept(processed -> saveFile(file, processed))
                .exceptionally(ex -> handleError(ex, file));
        return FileVisitResult.CONTINUE;
    }

    private InputStream openFile(Path file) {
        try {
            logger.info("Reading file: " + file.toAbsolutePath());
            return Files.newInputStream(file);
        } catch (IOException e) {
            throw new CompletionException("Error reading file: " + file.getFileName(), e);
        }
    }

    private InputStream processFile(InputStream input) {
        try (input) {
            InputStream result = service.calculateCredit(input);
            if (result == null) {
                throw new IllegalStateException("Calculated stream is null");
            }
            return result;
        } catch (IOException | JAXBException | DatatypeConfigurationException e) {
            throw new CompletionException("Error processing file", e);
        }
    }

    private void saveFile(Path srcFile, InputStream processed) {
        try (processed) {
            Path outputDir = Paths.get(outputDirectory);
            Files.createDirectories(outputDir);

            String newName = "paymentschedule_" + srcFile.getFileName();
            Path outputPath = outputDir.resolve(newName);
            Files.copy(processed, outputPath, StandardCopyOption.REPLACE_EXISTING);

            logger.info("Saved: " + outputPath);
        } catch (IOException e) {
            throw new CompletionException("Error saving file: " + srcFile.getFileName(), e);
        }
    }

    private Void handleError(Throwable ex, Path file) {
        Throwable cause = ex.getCause() != null ? ex.getCause() : ex;
        logger.severe("Error processing " + file.getFileName() + ": " + cause.getMessage());
        return null;
    }


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
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
