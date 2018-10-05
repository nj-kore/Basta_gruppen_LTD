package model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.*;


/**
 * The façade for the model package.
 */
public class MainModel extends Observable implements IMainModel{
    private User activeUser;
    private Conversation activeConversation;
    private enum UpdateTypes {
        ACTIVE_CONVERSATION, CONTACTS, CONVERSATIONS, INIT
    }
    private HashMap<Integer, Conversation>  conversations;
    private HashMap<Integer, User>  users = new HashMap<>();
    ArrayList<User> newConvoUsers = new ArrayList();
    private User detailedUser;

    public MainModel(HashMap<Integer, User>  users, HashMap<Integer, Conversation>  conversations){
        this.users = users;
        this.conversations = conversations;
        activeConversation = new Conversation(-1, null);
    }


    public void initFillers() {
        User admin = new User(1, "admin", "123", "Admin", "Boy");
        User contactUser=new User(2, "contact", "222", "olle", "innebandysson" );
        User contactUser2=new User(3, "contact2", "222", "kalle", "kuling" );
        admin.addContact(contactUser.getId());
        admin.addContact(contactUser2.getId());
        createUser(admin);
        createUser(contactUser);
        createUser(contactUser2);
        contactUser.setStatusImagePath("pics/activeStatus.png");
        contactUser.setProfileImagePath("pics/lukasmaly.jpg");
        contactUser.setStatus("Matematisk");
        users.put(admin.getId(),admin);
        users.put(contactUser.getId(),contactUser);
        users.put(contactUser2.getId(),contactUser2);
        update(UpdateTypes.INIT);
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
        int newMessageId = Collections.max(users.keySet()) + 1;
        Message m = new Message(newMessageId, activeUser.getId(), text, LocalDateTime.now());
        activeConversation.addMessage(m);
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
        return conversations.get(conversationId);
    }

    public Iterator<Message> loadMessagesInConversation(){
        /*try {
            Iterator<Message> messageIterator = activeConversation.getMessages().iterator();
            return messageIterator;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return activeConversation.getMessages().iterator();
    }

    public void addContact(int userId){
        activeUser.addContact(userId);
    }

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

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    private void setActiveConversation(int conversationId) {
        this.activeConversation = conversations.get(conversationId);
    }

    public void createConversation(ArrayList<User> users, String name) {
        int newConversationId = 0;
        try {
            newConversationId = Collections.max(conversations.keySet()) + 1;
        } catch (Exception e) {

        }
        Conversation conversation = new Conversation(newConversationId, users);
        conversations.put(conversation.getId(), conversation);
        setActiveConversation(conversation.getId());
        update(UpdateTypes.ACTIVE_CONVERSATION);
        //TODO update view conversationlist
    }

    //Exists for testing purposes
    public void addConversation(Conversation c) {
        conversations.put(c.getId(), c);
    }

    @Override
    public HashMap<Integer,Conversation> getConversations() {
        return conversations;            //TODO returns null
    }

    @Override
    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public void createUser(User u) {
        users.put(u.getId(), u);
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

        for(User u : users.values()) {
            if(u.getUsername().equals(username)) {
                if(u.getPassword().equals(password)) {
                    setActiveUser(u);
                    //setActiveConversation(conversations.get(0).getId());
                    //initFillers();
                    update(UpdateTypes.INIT);
                    return true;
                }
            }
        }
        return false;
    }

}
