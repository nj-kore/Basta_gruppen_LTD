package model;


import java.util.ArrayList;
import java.util.HashMap;

public interface IMainModel {
    void sendMessage(String text);
    Conversation loadConversation(int conversationId);
    User getActiveUser();
    Conversation getActiveConversation();
    ArrayList<User> getContacts();
    HashMap<Integer, Conversation> getConversations();
    boolean login(String username, String password);


}
