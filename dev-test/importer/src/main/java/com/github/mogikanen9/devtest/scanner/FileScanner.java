package com.github.mogikanen9.devtest.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileScanner implements Scanner {

    private Path srcFolder;

    public FileScanner(Path srcFolder) {
        this.srcFolder = srcFolder;
    }

    @Override
    public List<Path> listAll() throws ScannerException {

        try {
            return Files.list(srcFolder).limit(10).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ScannerException(e.getMessage(), e);
        }
    }
}