package model;


import infrastructure.DataHandlerDummy;
import infrastructure.IDataHandler;
import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;
import model.data.Conversation;
import model.data.Message;
import model.data.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.*;


/**
 * The facade for the model package.
 */
public class MainModel extends Observable implements IMainModel{
    private User activeUser;
    private Conversation activeConversation;
    private IDataHandler dataHandler = new DataHandlerDummy();
    private HashMap<Integer, Conversation> conversations = new HashMap<>();
    private HashMap<Integer, User> users = new HashMap<>();
    private enum UpdateTypes {
        ACTIVE_CONVERSATION, CONTACTS, CONVERSATIONS, INIT
    }
    private ArrayList<User> contacts = new ArrayList<>();
    private Iterator<Conversation> conversationIterator;
    private Iterator<Message> messageIterator;

    public MainModel(){

    }


    public void initFillers() {
        User activeUser = new User(1, "admin", "123", "eva", "olsson");
        User contactUser=new User(2, "contact", "222", "olle", "innebandysson" );
        User contactUser2=new User(3, "contact2", "222", "kalle", "kuling" );
        users.put(activeUser.getId(), activeUser);
        users.put(contactUser.getId(), contactUser);
        users.put(contactUser2.getId(), contactUser2);
        Image statusImage = new Image(getClass().getClassLoader().getResourceAsStream("pics/activeStatus.png"));
        Image profileImage = new Image((getClass().getClassLoader().getResourceAsStream("pics/lukasmaly.jpg")));
        //setActiveUser(activeUser);
        addContact(contactUser.getId());
        addContact(contactUser2.getId());
        addConversation(new Conversation(1));
        setActiveConversation(1);
        contactUser.setStatusImage(statusImage);
        contactUser.setProfileImage(profileImage);
        contactUser.setStatus("Matematisk");
    }
    /**
     * @param text
     *
     * Sends a text to the active conversation from the active user
     *
     * Tells the view to update itself
     */
    @Override
    public void sendMessage(String text) {
        Message m = new Message(activeUser.getId(), text);
        activeConversation.addMessage(m);
        dataHandler.saveMessage(activeConversation.getId(), m);
        update(UpdateTypes.ACTIVE_CONVERSATION);
    }


    /**
     * @param u the type of "update" that the observers should do
     *
     * Notifies the observers with the String update as an argument
     */
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
            case INIT:
                update = UpdateTypes.INIT.toString();
                break;
        }
        setChanged();
        notifyObservers(update);
    }


    public Conversation loadConversation(int conversationId) {
        Conversation c;
        if(conversations.containsKey(conversationId)) {
            c = conversations.get(conversationId);
            dataHandler.updateConversation(c);
        } else {
            c = dataHandler.loadConversation(conversationId);
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

    public void addContact(int userId){activeUser.addContact(userId);}



    public Iterator<User> getContacts(){
        List<User> list = new ArrayList<>();
        for(int id : activeUser.getContacts()) {
            list.add(getUser(id));
        }
        return list.iterator();
    }

    @Override
    public User getUser(int userId) {
        return users.get(userId);
    }

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


    /**
     *
     * @param username
     * @param password
     * @return if the login was successfull or not
     *
     * Checks if a User was found with the corresponding username and password
     */
    @Override
    public boolean login(String username, String password) {
        User user = dataHandler.loadUser(username);
        if(!user.equals(null)){
            if(user.confirmPassword(password)){
                setActiveUser(user);
                users.put(user.getId(), user);
                initFillers();
                update(UpdateTypes.INIT);

                return true;
            }
        }
        return false;
    }

}
