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

    @Override
    public void sendMessage(String text) {
        text = activeUser.getId() + ";" + text+"\n";
        fh.write(Integer.toString(activeConversation.getId()), text);
    }
    public Conversation loadConversation(int conversationId) {
        return fh.loadConversation(conversationId);
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

}
