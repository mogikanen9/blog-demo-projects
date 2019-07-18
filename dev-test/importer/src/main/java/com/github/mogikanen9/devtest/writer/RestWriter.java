package com.github.mogikanen9.devtest.writer;

import com.github.mogikanen9.devtest.domain.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestWriter implements Writer {

    @Override
    public void write(Book book) throws WriterException {
        if (log.isDebugEnabled()) {
            log.debug(String.format("RestWriter: writing book->%s", book));
        }
    }

}