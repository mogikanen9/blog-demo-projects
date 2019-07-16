package com.github.mogikanen9.devtest.parser;

import java.nio.file.Path;

import com.github.mogikanen9.devtest.domain.Book;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BookParser implements Parser {

    private Path sourceFile;

    @Override
    public void parse() throws ParserException {
        log.info(String.format("Parsing sourceFile->%s", sourceFile.toString()));
        //emulate some work
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Book praseLine(String line) {
        return new Book();
    }
}