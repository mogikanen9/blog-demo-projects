package com.github.mogikanen9.devtest.parser;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.github.mogikanen9.devtest.domain.Book;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BookParser implements Parser {

    private Path sourceFile;
    private String delimeter;
    private String quoteSymbol;
    private boolean skipFirstLine;

    @Override
    public void parse() throws ParserException {
        log.info(String.format("Parsing sourceFile->%s", sourceFile.toString()));

        try (BufferedReader reader = Files.newBufferedReader(sourceFile, Charset.forName("UTF-8"))) {
            String line = null;
            boolean skipLine = true;
            while ((line = reader.readLine()) != null) {

                // skip header
                if (skipFirstLine && skipLine) {
                    skipLine = false;
                    continue;
                }

                Book book = this.parse(line);
                if (log.isDebugEnabled()) {
                    log.debug(String.format("book->%s", book));
                }
            }
        } catch (Exception x) {
            log.error(x.getMessage(), x);
            throw new ParserException(x.getMessage(), x);
        }

        // Files.move(sourceFile, target, options)
    }

    protected Book parse(String line) throws ParserException {
        if (log.isDebugEnabled()) {
            log.debug(String.format("Parsing line -> %s", line));
        }

        // String[] columns = line.split(delimeter);
        String[] columns = new String[25];
        columns = this.customSplit(line).toArray(columns);

        if (columns.length < 10) {
            throw new ParserException("Invalid number of columns");
        } else {
            String isbn = null;
            String title = null;
            int publicationYear = 0;
            String authors = null;
            String langCode = null;
            float avgRating = 0.0f;
            String imgUrl = null;

            isbn = this.removeQuotes(columns[5]);
            title = this.removeQuotes(columns[9]);
            publicationYear = (int) this.parseFload(this.removeQuotes(columns[8]));
            authors = this.removeQuotes(columns[7]);
            langCode = this.removeQuotes(columns[10]);
            // avgRating = this.parseFload(this.removeQuotes(columns[4]));
            // imgUrl

            return new Book(isbn, title, publicationYear, langCode, authors, avgRating, imgUrl);
        }

    }

    protected String removeQuotes(String value) {

        if (quoteSymbol != null && value.startsWith(quoteSymbol) && value.endsWith(quoteSymbol)) {
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

    protected float parseFload(String value) {
        return Float.parseFloat(value);
    }

    protected List<String> customSplit(String line) throws ParserException {
        List<String> result = new ArrayList<>();
        boolean move = true;
        int currentIndex = 0;
        int startQuoteIndex = 0;
        int endQuoteIndex = 0;
        while (move) {

            int separatorIndex = line.indexOf(",", currentIndex);

            startQuoteIndex = line.indexOf("\"", endQuoteIndex == 0 ? endQuoteIndex : endQuoteIndex + 1);

            if (separatorIndex < line.length() - 1) { // not the last symbol
                if (separatorIndex < 0) {
                    result.add(line.substring(currentIndex)); // last column
                }else if (startQuoteIndex < 0 || separatorIndex < startQuoteIndex) {
                    result.add(line.substring(currentIndex, separatorIndex));
                    currentIndex = separatorIndex + 1;
                }  else {
                    // find index fo the end quote
                    endQuoteIndex = line.indexOf("\"", startQuoteIndex + 1);
                    if (endQuoteIndex > startQuoteIndex && endQuoteIndex != -1 && startQuoteIndex != -1) {
                        result.add(line.substring(startQuoteIndex + 1, endQuoteIndex));
                        currentIndex = endQuoteIndex + 2; // asuume that delimeter follows quote symbol
                        endQuoteIndex = currentIndex;
                    } else {
                        throw new ParserException("Cannot find second quotation symbol from from pair!");
                    }
                }
            }

            if (currentIndex < 0 || currentIndex >= line.length() - 1 || separatorIndex < 0) {
                move = false;
            }

        }

        return result;
    }
}