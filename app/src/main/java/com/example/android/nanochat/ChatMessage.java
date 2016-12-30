package com.example.android.nanochat;

/**
 * Created by Fatima Mostafa on 25-Dec-16.
 */

public class ChatMessage {

    String name;
    String message;

    public ChatMessage() {
    }

    public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
