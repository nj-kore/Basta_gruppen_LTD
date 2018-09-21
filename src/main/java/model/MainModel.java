package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainModel implements IMainModel {
    private FileHandler fh = new FileHandler();
    private User activeUser;
    private Conversation activeConversation;
    private static MainModel mainModel = new MainModel();

    private MainModel(){
    }

    public static MainModel getInstance() {
        return mainModel;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public void setActiveConversation(Conversation activeConversation) {
        this.activeConversation = activeConversation;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public Conversation getActiveConversation() {
        return activeConversation;
    }

    @Override
    public void sendMessage(String text) {
        Message message = new Message(activeUser, text);
        fh.write(Integer.toString(activeConversation.getId()), text);
    }
    public Conversation loadConversation(int conversationId) {
        return fh.loadConversation(conversationId);
    }

}
