package com.github.mogikanen9.chat;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class MyChatEndpoint {

    private Session session;

    private static Map<String, MyChatEndpoint> endPoints = new ConcurrentHashMap<>();

    protected Session getSession() {
        return this.session;
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("onOpen#session.getId()->" + session.getId());
        this.session = session;
        endPoints.put(session.getId(), this);
        this.broadcast(new Message(session.getId(),String.format("new user joined with id=%s", session.getId())));
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException {
        System.out.println("onMessage->" + message);
        this.broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        endPoints.remove(session.getId());
        this.broadcast(new Message(session.getId(), String.format("user with id=%s has just left",session.getId())));
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    protected void broadcast(Message message) {
        endPoints.entrySet().forEach(entry -> {
            synchronized (entry) {
                try {
                    entry.getValue().getSession().getBasicRemote().sendObject(message);
                    System.out.println("broadcasted to session %s->" + entry.getValue().getSession().getId());
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}