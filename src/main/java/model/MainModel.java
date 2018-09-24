package model;


import java.util.HashMap;

public class MainModel implements IMainModel {
    private User activeUser;
    private Conversation activeConversation;
    private IDataHandler dataHandler = new DataHandlerDummy();
    private HashMap<Integer, Conversation> conversations = new HashMap<>();
    private static MainModel mainModel = new MainModel();

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
            conversations.put(conversationId, c);
        }
        return c;
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

    public User getActiveUser() {
        return activeUser;
    }

    public Conversation getActiveConversation() {
        return activeConversation;
    }

}
