package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conversation {
    private int id;
    private Map<Integer, Message> messages = new HashMap<>();
    private List<User> participants = new ArrayList<User>();
    private String name;

    public Conversation(int id, ArrayList<User> participants) {
        this.id = id;
        this.participants = participants;
    }


    //functionality
    /**
     * Adds message m to conversation
     * @param m
     */
    public void addMessage(Message m) {
        messages.put(m.getId(), m);
    }


    //getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    public Map<Integer, Message> getMessages() {
        return messages;
    }

    //setters
    //Todo make protected
    public void setName(String name) {
        this.name = name;
    }
}
