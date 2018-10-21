package model;

/**
 * @author Jonathan Köre
 *
 */

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String text;
    private int senderId;
    private LocalDateTime time;

    public Message(int id, int senderId, String text, LocalDateTime time) {
        this.id = id;
        this.senderId = senderId;
        this.text = text;
        this.time = time;
    }
    //Getters
    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    //Setters
    protected void setText(String text) {
        this.text = text;
    }

}
