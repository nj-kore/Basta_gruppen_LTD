package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainModel implements MainModelinterface, DataBaseHandlerinterface {
    private FileHandler fh = new FileHandler();
    private User activeUser = new User(0);

    public void sendMessage(int conversationId, Message message) {
        String text = message.getSender().getId()+";"+message.getText()+"\n";
        fh.write(Integer.toString(conversationId), text);
    }
    public Conversation loadConversation(int conversationId) {
        return fh.loadConversation(conversationId);
    }

    public User getActiveUser() {
        return activeUser;
    }
}
