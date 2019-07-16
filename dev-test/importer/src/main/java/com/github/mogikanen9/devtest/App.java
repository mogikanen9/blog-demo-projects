package com.github.mogikanen9.devtest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.github.mogikanen9.devtest.parser.BookParser;
import com.github.mogikanen9.devtest.scanner.FileScanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {

        log.info("App main...");
    
        ExecutorService bookExecutor = Executors.newFixedThreadPool(2);

        Consumer<Path> bookFileParser = (path) -> {
            bookExecutor.submit(()-> new BookParser(path,",","'").parse());           
        };


        Runnable bookImporter = () -> {
            log.info("Importing books...");
            new FileScanner(Paths.get("c://temp"), 10).listAll().stream().forEach(bookFileParser);
        };

        ScheduledExecutorService srcScanner = Executors.newSingleThreadScheduledExecutor();
        srcScanner.scheduleWithFixedDelay(bookImporter,10, 30, TimeUnit.SECONDS);
       
    }

}
