package model;


import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private int id;
    private ArrayList<Message> messages = new ArrayList();
    private ArrayList<User> participants = new ArrayList<>();

    public Conversation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addMessage(Message m) {
        messages.add(m);
    }

    public void addParticipant(User u) {
        participants.add(u);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
