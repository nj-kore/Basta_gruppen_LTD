package model.data;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String text;
    private int sender_id;
    private LocalDateTime time;

    public Message(int id, int sender_id, String text, LocalDateTime time){
        this.sender_id = sender_id;
        this.text = text;
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getSender_id(){return sender_id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
