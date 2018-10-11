/**
 *
 * @author Filip Andréasson
 * @author Gustav Häger
 * @author Jonathan Köre
 * @author Gustaf Spjut
 * @author Benjamin Vinnerholt
 * @version 0.5
 * @since 2018-09-09
 * The MainModel is responsible for the logic and state of the application. It creates and handles the data
 */
package model;

import java.time.LocalDateTime;
import java.util.*;


/**
 * The façade for the model package.
 */
public class MainModel extends Observable{
    private User activeUser;
    private Conversation activeConversation;

    public enum UpdateTypes {
        ACTIVE_CONVERSATION, CONTACTS, CONVERSATIONS, INIT, USER_INFO
    }
    private Map<Integer, Conversation>  conversations;
    private Map<Integer, User>  users = new HashMap<>();
    ArrayList<User> newConvoUsers = new ArrayList();
    private User detailedUser;


    public MainModel(Map<Integer, User>  users, Map<Integer, Conversation>  conversations){
        this.users = users;
        this.conversations = conversations;
        activeConversation = new Conversation(-1, "", null);
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
    public void sendMessage(String text) {
        if(text.length() > 0) {
            //The new ID is going to be one more than the current highest message id in the conversation
            int newMessageId = 0;

            if(!activeConversation.getMessages().keySet().isEmpty())
                newMessageId = Collections.max(activeConversation.getMessages().keySet()) + 1;

            Message m = new Message(newMessageId, activeUser.getId(), text, LocalDateTime.now());
            activeConversation.addMessage(m);
            update(UpdateTypes.ACTIVE_CONVERSATION);
        }

    }
    public void setUserInfo(String firstName, String lastName, String email) {
        activeUser.setFirstName(firstName);
        activeUser.setLastName(lastName);
        activeUser.setEmail(email);
        update(UpdateTypes.USER_INFO);
    }


    /**
     * @param u the type of "update" that the observers should do
     *
     * Notifies the observers with the String update as an argument
     */
    private void update(UpdateTypes u) {
        setChanged();
        notifyObservers(u);
    }

    public Conversation loadConversation(int conversationId) {
        return conversations.get(conversationId);
    }

    public Iterator<Message> loadMessagesInConversation(){
        return activeConversation.getMessages().values().iterator();
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


    public User getUser(int userId) {
        return users.get(userId);
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public void setActiveConversation(int conversationId) {
        this.activeConversation = conversations.get(conversationId);
    }

    public void createConversation(ArrayList<User> users, String name) {
        int newConversationId = 0;
        if(!conversations.keySet().isEmpty())
            newConversationId = Collections.max(conversations.keySet()) + 1;

        Conversation conversation = new Conversation(newConversationId, name, users);
        conversations.put(conversation.getId(), conversation);
        setActiveConversation(conversation.getId());
        update(UpdateTypes.ACTIVE_CONVERSATION);
        //TODO update view conversationlist
    }

    //Exists for testing purposes
    public void addConversation(Conversation c) {
        conversations.put(c.getId(), c);
    }

    public void setConversationName(String name) {
        if(name.length() > 0 && name.length() < 30) {
            activeConversation.setName(name);
        }
    }

    public Map<Integer,Conversation> getConversations() {
        return conversations;}


    public void setStatus(String s){
        switch (s){
            case"AVAILABLE":
                activeUser.setStatusImagePath("pics/statusGreen.png");
                break;
            case "BUSY":
                activeUser.setStatusImagePath("pics/statusOrange.png");
                break;
            case "MATEMATISK":
                activeUser.setStatusImagePath("pics/statusRed.png");
                break;
        }
        activeUser.setStatus(s);
        update(UpdateTypes.USER_INFO);
    }

    //@Override
    //public HashMap<Integer,Conversation> getConversations() {
       // return conversations;            //TODO returns null
  //  }

    public Map<Integer, User> getUsers() {
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
    public boolean login(String username, String password) {

        for(User u : users.values()) {
            if(u.getUsername().equals(username)) {
                if(u.getPassword().equals(password)) {
                    setActiveUser(u);
                    update(UpdateTypes.INIT);
                    return true;
                }
            }
        }
        return false;
    }

}
