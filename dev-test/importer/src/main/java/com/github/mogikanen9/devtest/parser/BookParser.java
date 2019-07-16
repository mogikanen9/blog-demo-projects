package com.github.mogikanen9.devtest.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.mogikanen9.devtest.domain.Book;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BookParser implements Parser {

    private Path sourceFile;
    private String delimeter;
    private String quoteSymbol;

    @Override
    public void parse() throws ParserException {
        log.info(String.format("Parsing sourceFile->%s", sourceFile.toString()));

        try (BufferedReader reader = Files.newBufferedReader(sourceFile, Charset.forName("UTF-8"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Book book = this.parse(line);
                if (log.isDebugEnabled()) {
                    log.debug(String.format("book->%s", book));
                }
            }
        } catch (IOException x) {
            throw new ParserException(x.getMessage(), x);
        }
    }

    protected Book parse(String line) throws ParserException {
        if (log.isDebugEnabled()) {
            log.debug(String.format("Parsing line -> %s", line));
        }

        String[] columns = line.split(delimeter);

        if (columns.length != 5) {
            throw new ParserException("Invalid number of columns");
        } else {
            String isbn = null;
            String name = null;
            LocalDate publishedOn = null;
            String authors = null;
            int pages = 0;

            isbn = this.removeQuotes(columns[0]);
            name = this.removeQuotes(columns[1]);
            publishedOn = this.parseDate(this.removeQuotes(columns[2]));
            authors = this.removeQuotes(columns[3]);
            pages = this.parseInt(this.removeQuotes(columns[4]));

            return new Book(isbn, name, publishedOn, authors, pages);
        }

    }

    protected String removeQuotes(String value) {

        if (quoteSymbol != null) {
            return value.substring(1, value.length() - 1);
        } else {
            return value;
        }

    }

    protected int parseInt(String value) {
        if (value.length() > 0) {
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }

    protected LocalDate parseDate(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}