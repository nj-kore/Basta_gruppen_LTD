package model.data;


import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private int id;
    private List<Message> messages = new ArrayList<Message>();
    private List<User> participants = new ArrayList<User>();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<Message> getMessages() {
        return messages;
    }
}
