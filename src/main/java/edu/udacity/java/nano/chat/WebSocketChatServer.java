package edu.udacity.java.nano.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();


    private static void sendMessageToAll(String msg) throws IOException {
        //TODO: add send message method.
        //loop message sending process to all sessions in onlineSessions
        List<Session> sessionList = new ArrayList<Session>(onlineSessions.values());
        for (Session s: sessionList) {
            onMessage(s, msg);
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //TODO: add on open connection.
        onlineSessions.put("username", session);
        System.out.println("Now Connecting...");
        session = session;
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public static void onMessage(Session session, String jsonStr) throws IOException {
        //TODO: add send message.
        if (session.isOpen()) {
            session.getBasicRemote().sendText(jsonStr);
        }

    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    @RequestMapping("/logout")
    public void onClose(Session session) throws IOException {
        //TODO: add close connection.
        session.close();
        onlineSessions.remove("username", session);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
