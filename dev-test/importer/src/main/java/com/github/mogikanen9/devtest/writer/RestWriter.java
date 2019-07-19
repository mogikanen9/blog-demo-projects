package com.github.mogikanen9.devtest.writer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.mogikanen9.devtest.domain.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestWriter implements Writer {

    @Override
    public void write(Book book) throws WriterException {
        if (log.isDebugEnabled()) {
            log.debug(String.format("RestWriter: writing book->%s", book));
        }

        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target("http://localhost:8080");
            WebTarget bookTarget = webTarget.path("/book");
            Invocation.Builder invocationBuilder = bookTarget.request(MediaType.APPLICATION_JSON);

            RestBook restBook = new RestBook();
            restBook.setAuthors(book.getAuthors());
            restBook.setAvgRating(book.getAvgRating());
            //restBook.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MI:SS")));
            restBook.setCreated("2019-07-19T10:30:29");
            restBook.setCreatedBy("book-importer");
            restBook.setImgUrl(book.getImgUrl());
            restBook.setIsbn(book.getIsbn());
            restBook.setLangCode(book.getLangCode());
            restBook.setPublicationYear(book.getPublicationYear());
            restBook.setTitle(book.getTitle());
            restBook.setUpdated(restBook.getCreated());
            restBook.setUpdatedBy(restBook.getCreatedBy());

            Response response = invocationBuilder.post(Entity.entity(restBook, MediaType.APPLICATION_JSON));
            if (log.isDebugEnabled()) {
                log.debug(String.format("RestWriter: response from post book->%s", response.toString()));
            }
        } catch (Exception e) {
           throw new WriterException(e.getMessage(),e);
        }
       
    }

}