package com.example.rohith.hw5;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Map;

/**
 * Created by Rohith on 6/27/2016.
 */
public class Message implements Serializable {
    String sender, receiver, message;
    Boolean isRead;
    Map<String, String> timestamp;

    public Map<String, String> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Map<String, String> timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
