package com.github.mogikanen9.devtest.domain;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Book implements Serializable{

    private static final long serialVersionUID = 1L;

    private String isbn;

    private String name;

    private LocalDate publishedOn;

    private String authors;

    private int pages;




}