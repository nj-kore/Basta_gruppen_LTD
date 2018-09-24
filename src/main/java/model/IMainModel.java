package model;


public interface IMainModel {
    void sendMessage(String text);
    Conversation loadConversation(int conversationId);
    User getActiveUser();
    Conversation getActiveConversation();

}
