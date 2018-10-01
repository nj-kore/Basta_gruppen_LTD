package model;


import infrastructure.DataHandlerDummy;
import infrastructure.IDataHandler;
import model.data.Conversation;
import model.data.Message;
import model.data.User;

import java.util.*;

public class MainModel implements IMainModel {
    private User activeUser;
    private Conversation activeConversation;
    private IDataHandler dataHandler = new DataHandlerDummy();
    private HashMap<Integer, Conversation> conversations = new HashMap<>();
    private HashMap<Integer, User> users = new HashMap<>();
    private static MainModel mainModel = new MainModel();
    private ArrayList<User> contacts = new ArrayList<>();
    private Iterator<Conversation> conversationIterator;
    private Iterator<Message> messageIterator;

    private MainModel(){
    }

    public static MainModel getInstance() {
        return mainModel;
    }

    @Override
    public void sendMessage(String text) {
        Message m = new Message(activeUser, text);
        activeConversation.addMessage(m);
        dataHandler.saveMessage(activeConversation.getId(), m);
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
        User user = dataHandler.loadUser(username);
        if(!user.equals(null)){
            if(user.confirmPassword(password)){
                this.activeUser = user;
                return true;
            }
        }
        return false;
    }

}
