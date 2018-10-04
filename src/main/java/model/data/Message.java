package model.data;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String text;
    private int senderId;
    private int conversationId;
    private LocalDateTime time;

    public Message(int id, int senderId, String text, LocalDateTime time) {
        this.id = id;
        this.senderId = senderId;
        this.text = text;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
