/**
 * A class that holds the data of a Conversation in the application
 * responsibility:  To hold the data of a Conversation
 * used by:         A lot of classes, including but not limited to: MainModel, JsonSaver, JsonLoader
 * used for:        Modeling a Conversation.
 * @author Jonathan Köre
 * @author Filip Andréasson
 */

package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conversation {
    private int id;
    private Map<Integer, Message> messages = new HashMap<>();
    private List<User> participants;
    private String name;

    public Conversation(int id, String name, List<User> participants) {
        this.id = id;
        this.name = name;
        this.participants = participants;
    }


    //functionality
    /**
     * Adds message m to conversation
     * @param m
     */
    void addMessage(Message m) {
        messages.put(m.getId(), m);
    }


    //getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    Map<Integer, Message> getMessages() {
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
        if (!messages.equals(that.messages)) {return false;}
        if (!participants.equals(that.participants)) {return false;}
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        result = 31 * result + participants.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public List<User> getParticipants() {
        return participants;
    }

    void addParticipant(User userToAdd) {
        participants.add(userToAdd);
    }

    void removeParticipant(User userToRemove) {
        for (User participant : participants){
            if (participant.getId() == userToRemove.getId()){
                participants.remove(participant);
            }
        }
    }
}
