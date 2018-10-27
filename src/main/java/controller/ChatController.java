package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import model.User;
import view.chat.IChatView;

import java.util.ArrayList;

/**
 * The controller class that handles all the inputs from the ChatView
 */
public class ChatController implements IChatController {

    private IChatView chatView;
    private MainModel mainModel;

    /**
     * @param chatView An IChatView holds the data that the controller is dependant on forwarding to the mainModel
     * @param mainModel The mainModel which the controller forwards data from the view to
     */
    public ChatController(IChatView chatView, MainModel mainModel) {
        this.mainModel = mainModel;
        this.chatView = chatView;
    }

    /**
     * Forwards the text typed in the chat to the model, and tells the chatView to clear its text fields
     */
    @Override
    public void onSendButtonClicked() {
        String text = chatView.getInputText().trim();
        mainModel.sendMessage(text);
        chatView.clearInputField();
    }

    /**
     * Chooses what actions to be taken according to what was typed into the chat
     * <p>Sends the message if the <b>enter</b> key is pressed</p>
     * <p>Creates a new line if <b>enter</b> in combination with <b>shift</b> key is pressed</p>
     *
     * @param event The key combination that was typed by the user
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

    /**
     * Tells the chat to show that the chatName is currently in "edit" mode
     */
    @Override
    public void onChangeChatNameClicked() {
        chatView.editChatName();
    }

    @Override
    public void onLeaveChatClicked() {
        ArrayList<User> userIterator = new ArrayList<>();
        userIterator.add(mainModel.getActiveUser());
        mainModel.removeParticipants(userIterator.iterator(), mainModel.getActiveConversation());
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

    /**
     * Tells the chat to cancel the edit of the chat name
     */
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
