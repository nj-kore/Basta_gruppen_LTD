package controller;

import javafx.scene.input.KeyEvent;

public interface IChatController {
    void onSendButtonClicked();
    void onChatAreaKeyPressed(KeyEvent event);
    void onChangeChatNameClicked();
    void onLeaveChatClicked();
    void onChatNameKeyPressed(KeyEvent e);
    void onChatNameAccept();
    void onChatNameDecline();


}
