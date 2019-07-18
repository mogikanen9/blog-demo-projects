package com.github.mogikanen9.devtest.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileScanner {

    private Path srcFolder;

    private int limit;

    public List<Path> listAll() throws ScannerException {

        try {
            return Files.list(srcFolder).limit(limit).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ScannerException(e.getMessage(), e);
        }
    }
}