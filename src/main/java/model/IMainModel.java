package model;


import model.data.Conversation;
import model.data.Message;
import model.data.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public interface IMainModel {
    void sendMessage(String text);
    Conversation loadConversation(int conversationId);
    User getActiveUser();
    Conversation getActiveConversation();
    Iterator<User> getContacts();
    Iterator<Conversation> getConversations();
    boolean login(String username, String password);
    Iterator<Message> loadMessagesInConversation();


}
