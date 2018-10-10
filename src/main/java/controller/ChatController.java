package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import view.ChatView;
import view.MainView;

public class ChatController implements IChatController {

    private ChatView chatView;
    private MainModel mainModel;

    public ChatController(ChatView chatView, MainModel mainModel) {
        this.mainModel = mainModel;
        this.chatView = chatView;
    }

    @Override
    public void onSendButtonClicked() {
        String text = chatView.getInputText().trim();
        mainModel.sendMessage(text);
        chatView.clearInputField();
    }

    @Override
    public void onChatAreaKeyPressed(KeyEvent event) {
        if(chatView.chatNameIsFocused()) {

        }
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (event.isShiftDown()) {
                chatView.createNewLine();
            } else {
                onSendButtonClicked();
                event.consume();
            }
        }
    }

    @Override
    public void onChangeChatNameClicked() {
        chatView.setChatNameEditable(true);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(chatView.chatNameIsFocused()) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                chatView.setChatNameEditable(false);
                mainModel.setConversationName(chatView.getChatNameText());
            }
        });
        t.start();
    }

    @Override
    public void onConversationNameKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            chatView.setChatAreaFocused();
        }
    }

}
