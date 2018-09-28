package model;


import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private int id;
    private List<Message> messages = new ArrayList<Message>();
    private List<User> participants = new ArrayList<User>();
    private String name;
    private Image picture;

    public Conversation(int id) {
        this.id = id;
    }


    //functionality
    public void addMessage(Message m) {
        messages.add(m);
    }

    public void addParticipant(User u) {
        participants.add(u);
    }


    //getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Image getPicture() {
        return picture;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }











}
