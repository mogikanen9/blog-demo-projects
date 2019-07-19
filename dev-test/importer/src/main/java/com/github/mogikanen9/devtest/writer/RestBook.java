package com.github.mogikanen9.devtest.writer;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RestBook {
   
    private long id;

    private String created; 

    private String createdBy;

    private String updated; 

    private String updatedBy;

    private String isbn;

    private String title;

    private int publicationYear;

    private String langCode;

    private String authors;

    private float avgRating;

    private String imgUrl;
}