package com.github.mogikanen9.devtest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import com.github.mogikanen9.devtest.parser.BookParser;
import com.github.mogikanen9.devtest.scanner.FileScanner;
import com.github.mogikanen9.devtest.scanner.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {

        log.info("App main...");
        
        Consumer<Path> bookFileParse = (path) -> {
            new BookParser(path).parse();
        };

        Scanner scanner = new FileScanner(Paths.get("c://temp"));
        scanner.listAll().stream().forEach(bookFileParse);
    }

}
