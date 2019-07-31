package com.github.mogikanen9.devtest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.github.mogikanen9.devtest.parser.BookParser;
import com.github.mogikanen9.devtest.parser.BookResult;
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
        final String apiUname = "importer";
        final String apiPwd = "Welcome13";

        ExecutorService bookExecutor = Executors.newFixedThreadPool(2);

        Function<Path, BookResult> bookFileParser = (path) -> {
            try {
                return bookExecutor.submit(() -> {
                    return new BookParser(path, delimeterSymbol, quoteSymbol, doubleQuoteSymbol, doubleQuoteReplacement,
                            true, new RestWriter(apiBaseUrl, apiUname, apiPwd)).parse();
                }).get();
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage(), e);
                return new BookResult(path, 0, 0, new ParserException(e.getMessage()));
            }

        };

        Runnable bookImporter = () -> {
            log.info("Importing books...");
            new FileScanner(Paths.get("c://temp"), 10).listAll().stream().map(bookFileParser).forEach((rs) -> {
                log.info(String.format("Imported result->%s",rs.toString()));
            });
        };

        ScheduledExecutorService srcScanner = Executors.newSingleThreadScheduledExecutor();
        srcScanner.scheduleWithFixedDelay(bookImporter, 10, 100, TimeUnit.SECONDS);

    }

}
