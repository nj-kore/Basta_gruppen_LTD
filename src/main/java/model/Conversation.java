package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conversation {
    private int id;
    private Map<Integer, Message> messages = new HashMap<>();
    private List<User> participants;
    private String name;

    public Conversation(int id, String name, ArrayList<User> participants) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Conversation that = (Conversation) o;

        if (id != that.id) {return false;}
        if (messages != null ? !messages.equals(that.messages) : that.messages != null) {return false;}
        if (!participants.equals(that.participants)) {return false;}
        return name != null ? name.equals(that.name) : that.name == null;
    }
    
    public List<User> getParticipants() {
        return participants;
    }
}
