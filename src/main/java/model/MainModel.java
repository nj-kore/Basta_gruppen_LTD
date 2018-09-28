package model;



import java.util.ArrayList;
import java.util.HashMap;

public class MainModel implements IMainModel {
    private User activeUser;
    private Conversation activeConversation;
    private IDataHandler dataHandler = new DataHandlerDummy();
    private HashMap<Integer, Conversation> conversations = new HashMap<>();
    private HashMap<Integer, User> users = new HashMap<>();
    private static MainModel mainModel = new MainModel();
    private ArrayList<User> contacts = new ArrayList<>();

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

    public void addContact(User u){activeUser.addContact(u);}

    public ArrayList<User> getContacts(){return activeUser.getContacts();}

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

    public HashMap<Integer, Conversation> getConversations() {
        return conversations;
    }

    /*public void createConversation(String name, int[] participants) {
        //The dataHandler is going to create a blank conversation, with a valid ID that we can use.
        // For now, the Id is just a random int between 0 and 100000
        Conversation c = dataHandler.createConversation();
        for(int i = 0; i < participants.length; i++)
            c.addParticipant(users.get(participants[i]));
        c.setName(name);
        dataHandler.saveConversation(c);
        addConversation(c);

    }*/

    //Is going to be used while testing
    public void addUser(User u) {
        users.put(u.getId(), u);
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
