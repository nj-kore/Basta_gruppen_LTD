package model;

import javafx.beans.Observable;

import java.util.Observer;

interface IMainModel extends Observer {
    void sendMessage(int conversationId, Message message);
    Conversation loadConversation(int conversationId);
    User getActiveUser();

}
