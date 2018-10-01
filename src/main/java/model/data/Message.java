package model.data;

public class Message {
    private int id;
    private User sender;
    private String text;
    private int sender_id;
    private int conversation_id;

    public Message(User sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public Message(int id, int sender_id, int conversation_id, String text, String time_sent){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
