package model.data;

public class Message {
    private int id;
    private String text;
    private int senderId;
    private int conversationId;

    public Message(int senderId, String text) {
        this.senderId = senderId;
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
