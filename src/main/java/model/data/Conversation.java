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

    /**
     * Adds message m to conversation
     * @param m
     */
    public void addMessage(Message m) {
        messages.add(m);
    }

    /**
     * Adds user u to the conversation
     * @param u
     */
    public void addParticipant(User u) {
        participants.add(u);
    }

    public List<User> getParticipants() {
        return participants;
    }

    public User getParticipant(int id) {
        for (User u : participants) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
