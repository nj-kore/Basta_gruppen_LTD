package model;


import java.util.ArrayList;

public interface IMainModel {
    void sendMessage(String text);
    Conversation loadConversation(int conversationId);
    User getActiveUser();
    Conversation getActiveConversation();
    ArrayList<User> getContacts();


}
