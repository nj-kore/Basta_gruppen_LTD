package model;

public interface IDataHandler {
    void saveMessage(int conversationId, Message m);
    void saveUser(User u);
    void saveConversation(Conversation c);
    void updateUser(User u);
    void updateConversation(Conversation c);
    Conversation loadConversation(int conversationId);
    User loadUser(int userId);
    User loadUser(String username);
}
