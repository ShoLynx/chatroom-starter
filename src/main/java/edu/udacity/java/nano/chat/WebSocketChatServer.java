package edu.udacity.java.nano.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.net.UnknownServiceException;
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


    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        //find out how to link users to sessions
        // loop message sending process to all sessions in onlineSessions
        List<Session> sessionList = new ArrayList<Session>(onlineSessions.values());
        for (Session s: sessionList) {
            //find sendMsg function
            //find out how to incorporate this without scheduler
            // public void sendAdhocMessages() {
            //    template.convertAndSend("/topic/user", new Greeting("Scheduler"));
            //}
        }


    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //TODO: add on open connection.
        //Add user's session to the session list
        onlineSessions.put("username", session);
        System.out.println("Now Connecting...");
        //Use send all to send message (Message.username has entered the room)
        session = session;
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        //use send all to send content
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
        //remove user from sessionList
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
