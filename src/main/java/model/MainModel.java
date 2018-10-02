package model;


import infrastructure.DataHandlerDummy;
import infrastructure.IDataHandler;
import infrastructure.JsonHandler;
import javafx.scene.image.Image;
import model.data.Conversation;
import model.data.Message;
import model.data.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.*;


public class MainModel extends Observable implements IMainModel{
    private User activeUser;
    private Conversation activeConversation;
    private HashMap<Integer, Conversation> conversations = new HashMap<>();
    private HashMap<Integer, User> users = new HashMap<>();
    private enum UpdateTypes {
        ACTIVE_CONVERSATION, CONTACTS, CONVERSATIONS
    }
    private ArrayList<User> contacts = new ArrayList<>();
    private Iterator<Conversation> conversationIterator;
    private Iterator<Message> messageIterator;
    private IDataHandler jsonHandler = new JsonHandler();

    public MainModel(){
        User activeUser = new User(1, "admin", "123", "eva", "olsson");
        User contactUser=new User(2, "contact", "222", "olle", "innebandysson" );
        User contactUser2=new User(3, "contact2", "222", "kalle", "kuling" );

        Image statusImage = new Image(getClass().getClassLoader().getResourceAsStream("pics/activeStatus.png"));
        Image profileImage = new Image((getClass().getClassLoader().getResourceAsStream("pics/lukasmaly.jpg")));
        setActiveUser(activeUser);
        addContact(contactUser);
        addContact(contactUser2);
        addConversation(new Conversation(1));
        setActiveConversation(1);
        //contactUser.setStatusImage(statusImage);
        //contactUser.setProfileImage(profileImage);
        contactUser.setStatus("Matematisk");
        jsonHandler.saveUser(activeUser);
        jsonHandler.saveUser(contactUser);
        jsonHandler.saveUser(contactUser);

    }


    @Override
    public void sendMessage(String text) {

        int newMessageId = 0;
        int oldHighestId;

        while(loadMessagesInConversation().hasNext()){
            oldHighestId = loadMessagesInConversation().next().getId();
            if(oldHighestId >= newMessageId){
                newMessageId = oldHighestId + 1;
            }
        }

        jsonHandler.loadConversation(activeConversation.getId()).getMessages();

        Message m = new Message(newMessageId,activeUser.getId(), text, LocalDateTime.now());

        activeConversation.addMessage(m);

        jsonHandler.saveMessage(activeConversation.getId(), m);
        update(UpdateTypes.ACTIVE_CONVERSATION);
    }

    private void update(UpdateTypes u) {
        String update = "";
        switch(u) {
            case ACTIVE_CONVERSATION:
                update = UpdateTypes.ACTIVE_CONVERSATION.toString();
                break;
            case CONVERSATIONS:
                update = UpdateTypes.CONVERSATIONS.toString();
                break;
            case CONTACTS:
                update = UpdateTypes.CONTACTS.toString();
                break;
        }
        setChanged();
        notifyObservers(update);
    }

    public Conversation loadConversation(int conversationId) {
        Conversation c;
        if(conversations.containsKey(conversationId)) {
            c = conversations.get(conversationId);
            jsonHandler.updateConversation(c);
        } else {
            c = jsonHandler.loadConversation(conversationId);
            if(c != null)
                addConversation(c);
        }
        return c;
    }

    public Iterator<Message> loadMessagesInConversation(){
        Conversation c=loadConversation(getActiveConversation().getId());
        messageIterator = c.getMessages().iterator();
        return messageIterator;
    }

    public void addContact(User u){activeUser.addContact(u);}

    public Iterator<User> getContacts(){


        return activeUser.getContacts().iterator();}

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public void setActiveConversation(int conversationId) {
        this.activeConversation = conversations.get(conversationId);
    }

    public void addConversation(Conversation c) {
        conversations.put(c.getId(), c);
    }

    public Iterator<Conversation> getConversations() {
        conversationIterator = conversations.values().iterator();
        return conversationIterator;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public Conversation getActiveConversation() {
        return activeConversation;
    }



    @Override
    public boolean login(String username, String password) {
        User user = jsonHandler.loadUser(username);
        if(user != null){
            if(user.confirmPassword(password)){
                this.activeUser = user;
                return true;
            }
        }
        return false;
    }

}
