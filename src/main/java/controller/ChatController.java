package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import view.ConversationListItem;
import view.IChatView;

/**
 * The controller class that handles all the inputs from the ChatView
 */
public class ChatController implements IChatController {

    private IChatView chatView;
    private MainModel mainModel;

    public ChatController(IChatView chatView, MainModel mainModel) {
        this.mainModel = mainModel;
        this.chatView = chatView;
    }

    /**
     * Forwards the text typed in the chat to the model
     */
    @Override
    public void onSendButtonClicked() {
        String text = chatView.getInputText().trim();
        mainModel.sendMessage(text);
        chatView.clearInputField();
    }

    /**
     *
     * Chooses what actions to be taken according to what was typed into the chat input
     *
     * @param event the key combination that was typed by the user
     */
    @Override
    public void onChatAreaKeyPressed(KeyEvent event) {

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
        chatView.editChatName();
    }

    @Override
    public void onLeaveChatClicked() {
        mainModel.getConversations().remove(mainModel.getActiveConversation().getId());
        chatView.setDefaultConversation();
    }

    /**
     * Chooses what actions the be taken according to what key was pressed while editing the chat name
     *
     * @param event the key combination that was typed by the user
     */
    @Override
    public void onChatNameKeyPressed(KeyEvent event) {

        if (event.getCode().equals(KeyCode.ENTER)) {
            onChatNameAccept();
        }
    }

    @Override
    public void onChatNameDecline() {
        chatView.cancelEditChatName();
    }

    /**
     * Sets the active conversations name to the name that is currently in the chatNameTextField
     */
    @Override
    public void onChatNameAccept() {
        mainModel.setConversationName(chatView.getChatNameText());
        chatView.finishEditChatName();
    }

}
