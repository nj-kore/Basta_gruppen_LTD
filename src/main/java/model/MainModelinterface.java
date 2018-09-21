package model;

import javafx.beans.Observable;

import java.util.Observer;

interface MainModelinterface extends Observer {
    void sendMessage(int conversationId, Message message);
    Conversation loadConversation(int conversationId);
    User getActiveUser();

}
