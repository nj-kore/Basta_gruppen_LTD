package controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public interface IChatController {
    void onSendButtonClicked();
    void onChatAreaKeyPressed(KeyEvent event);
    void onChangeChatNameClicked();
    void onConversationNameKeyPressed(KeyEvent e);
}
