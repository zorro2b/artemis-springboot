package com.example.amqdemo;

import java.io.Serializable;
import java.time.LocalDateTime;

public final class CustomMessage implements Serializable {

    private String text;
    private int sequence;
    private boolean secret;
//    private LocalDateTime sent;

    public CustomMessage() {
    }

    public CustomMessage(String text,
                         int sequence,
                         boolean secret //,
//                         LocalDateTime sent
                         ) {
        this.text = text;
        this.sequence = sequence;
        this.secret = secret;
//        this.sent = sent;
    }

    public String getText() {
        return text;
    }

    public int getSequence() {
        return sequence;
    }

    public boolean isSecret() {
        return secret;
    }

//    public LocalDateTime getSent() {
//        return sent;
//    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "text='" + text + "'" +
                ", sequence=" + sequence +
                ", secret=" + secret +
//                ", sent=" + sent +
                '}';
    }
}

