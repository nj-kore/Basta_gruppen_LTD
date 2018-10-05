package model;


import java.util.ArrayList;
import java.util.Iterator;

public interface IMainModel {
    void sendMessage(String text);
    Conversation loadConversation(int conversationId);
    void createConversation(ArrayList<User> users, String name);
    User getActiveUser();
    Conversation getActiveConversation();
    Iterator<User> getContacts();
    Iterator<Conversation> getConversations();
    boolean login(String username, String password);
    Iterator<Message> loadMessagesInConversation();
    User getUser(int userId);


}
