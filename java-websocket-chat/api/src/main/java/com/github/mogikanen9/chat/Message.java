package com.github.mogikanen9.chat;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Message {

    private String id;
    private LocalDateTime created;
    private String title;
    private String userId;
    private String body;
}
