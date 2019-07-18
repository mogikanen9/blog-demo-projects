package com.github.mogikanen9.devtest.parser;

public class ParserException extends Exception{

    private static final long serialVersionUID = 1L;

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserException(String message) {
        super(message);
    }
}