package com.github.mogikanen9.devtest.scanner;

import java.nio.file.Path;
import java.util.List;

public interface Scanner {

    List<Path> listAll() throws ScannerException;
}