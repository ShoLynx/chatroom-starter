package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    private String username;
    private String msg;
    private String type;
    private String onlineCount;

    public Message() {}

    public String greeting(String name, Integer members) {
        final String s = name + " has logged in.  There are " + members + " user(s) in this room.";
        return s;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }
}


