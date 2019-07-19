package com.github.mogikanen9.devtest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.github.mogikanen9.devtest.parser.BookParser;
import com.github.mogikanen9.devtest.parser.ParserException;
import com.github.mogikanen9.devtest.scanner.FileScanner;
import com.github.mogikanen9.devtest.writer.RestWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {

        log.info("App main...");

        final String delimeterSymbol = ",";
        final String quoteSymbol = "\"";
        final String doubleQuoteSymbol = "\"\"";
        final String doubleQuoteReplacement = "'";
        final String apiBaseUrl = "http://localhost:8080";

        ExecutorService bookExecutor = Executors.newFixedThreadPool(1);

        Consumer<Path> bookFileParser = (path) -> {
            bookExecutor.submit(() -> {
                try {
                    new BookParser(path, delimeterSymbol, quoteSymbol, doubleQuoteSymbol, doubleQuoteReplacement, true,
                            new RestWriter(apiBaseUrl)).parse();
                } catch (ParserException e) {
                    log.error(e.getMessage(), e);
                }
            });
        };

        Runnable bookImporter = () -> {
            log.info("Importing books...");
            new FileScanner(Paths.get("c://temp"), 10).listAll().stream().forEach(bookFileParser);
        };

        ScheduledExecutorService srcScanner = Executors.newSingleThreadScheduledExecutor();
        srcScanner.scheduleWithFixedDelay(bookImporter, 10, 300, TimeUnit.SECONDS);

    }

}
