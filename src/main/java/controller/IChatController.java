package controller;

import javafx.scene.input.KeyEvent;

/**
 * The interface for controlling the user input from a chat
 */
public interface IChatController {
    void onSendButtonClicked();
    void onChatAreaKeyPressed(KeyEvent event);
    void onChangeChatNameClicked();
    void onLeaveChatClicked();
    void onChatNameKeyPressed(KeyEvent e);
    void onChatNameAccept();
    void onChatNameDecline();


}
