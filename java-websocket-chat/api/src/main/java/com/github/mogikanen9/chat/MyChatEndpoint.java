package com.github.mogikanen9.chat;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{username}")
public class MyChatEndpoint {

    //private Session session;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        //this.session = session;
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("message->" + message);
        session.getBasicRemote().sendText("Got it!");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}