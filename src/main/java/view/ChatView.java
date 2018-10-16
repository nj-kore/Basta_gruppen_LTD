package view;

import controller.ChatController;
import controller.IChatController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.*;
import model.Message;


import java.io.IOException;
import java.util.Iterator;

/**
 * The View in which the current on-going conversations are displayed.
 */
public class ChatView extends AnchorPane implements IChatView {


    private MainModel mainModel;

    @FXML
    private FlowPane chatFlowPane;

    @FXML
    private Button sendButton;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private TextField chatNameTextField;

    @FXML
    private MenuButton optionsMenuButton = new MenuButton("Options", new ImageView(new Image("pics/optionsIcon.png")));

    @FXML
    private MenuItem changeChatNameMenuItem;

    @FXML
    private MenuItem leaveChatMenuItem;

    @FXML
    private ImageView acceptImageView, declineImageView;


    /**
     *
     * @param mainModel
     *
     * Initialises the ChatViews components and links all the controlling input to an IChatController
     */
    public ChatView(MainModel mainModel) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ChatView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        this.mainModel = mainModel;

        IChatController chatController = new ChatController(this, mainModel);

        sendButton.setOnAction(event -> chatController.onSendButtonClicked());

        chatTextArea.setOnKeyPressed(event -> chatController.onChatAreaKeyPressed(event));

        chatNameTextField.setOnMouseClicked(event -> chatController.onChangeChatNameClicked());

        chatNameTextField.setOnKeyPressed(event -> chatController.onChatNameKeyPressed(event));

        changeChatNameMenuItem.setOnAction(event -> chatController.onChangeChatNameClicked());

        leaveChatMenuItem.setOnAction(event -> chatController.onLeaveChatClicked());

        acceptImageView.setOnMouseClicked(event -> chatController.onChatNameAccept());

        declineImageView.setOnMouseClicked(event -> chatController.onChatNameDecline());
    }

    /**
     * Loads the messages from the model and displays them as MessageItems
     */

    public void loadMessages() {
        chatFlowPane.getChildren().clear();
        Iterator<Message> itr = mainModel.loadMessagesInConversation();
        if (itr != null) {
            while (itr.hasNext()) {
                Message m = itr.next();
                chatFlowPane.getChildren().add(new MessageItem(m, mainModel.getUser(m.getSenderId())));
            }
        }
    }

    /**
     * Updates the ChatView to be up to date with the model
     */
    public void update() {
        loadMessages();
        loadChatName();
    }

    private void loadChatName() {
        String name = mainModel.getActiveConversation().getName();
        if(name.length() > 0)
            chatNameTextField.setText(mainModel.getActiveConversation().getName());
        else
            chatNameTextField.setText(mainModel.generatePlaceholderName(mainModel.getActiveConversation()));
    }

    /**
     * Makes the ChatName editable and makes the accept and decline button visible
     * @param editable
     *
     */
    private void setChatNameEditable(boolean editable) {
        chatNameTextField.setEditable(editable);
        acceptImageView.setVisible(editable);
        declineImageView.setVisible(editable);
        if(editable)
            chatNameTextField.setStyle("-fx-background-color: cyan;");
        else
            chatNameTextField.setStyle("-fx-background-color: white;");
    }

    /**
     *
     * @return the text that is currently in the chat input area
     */
    @Override
    public String getInputText() {
        return chatTextArea.getText();
    }

    /**
     * Creates a new line in the chat input area
     */
    @Override
    public void createNewLine() {
        chatTextArea.setText(chatTextArea.getText() + "\n");
        chatTextArea.end();
    }

    @Override
    public void clearInputField() {
        chatTextArea.clear();
    }

    /**
     * Runs the methods for making the chatName editable, and puts the cursor conveniently inside the chatNameTextField
     */
    @Override
    public void editChatName() {
        setChatNameEditable(true);
        chatNameTextField.requestFocus();
    }

    /**
     * Cancels the chatName editing, meaning that the chat name should be restored to what is previously was
     */
    @Override
    public void cancelEditChatName() {
        setChatNameEditable(false);
        //restores it to the old name
        loadChatName();
    }

    /**
     * Canceling the chatName editing, but the name that was set gets updated from the model via the update method
     */
    @Override
    public void finishEditChatName() {
        //The code that updates the name itself does come from the model
        setChatNameEditable(false);
    }

    /**
     *
     * @return the text inside the chatNameTextField
     */
    @Override
    public String getChatNameText() {
        return chatNameTextField.getText();
    }


    /**
     * The class that is used as a visual representation for the message in the chat.
     */
    private class MessageItem extends AnchorPane {

        @FXML
        private Label messageUserNameLabel;

        @FXML
        private ImageView messageImageView;

        @FXML
        private TextFlow messageTextFlow;

        /**
         * Initialises the MessageItem and builds the item according to the data found inside the two parameters
         *
         * @param message
         * @param concreteUser
         *
         */
        public MessageItem(Message message, User concreteUser) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/Message.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
            Image profileImage = new Image(concreteUser.getProfileImagePath());
            messageImageView.setImage(profileImage);
            messageUserNameLabel.setText(concreteUser.getFullName());
            messageTextFlow.getChildren().add(new Text(message.getText()));
        }
    }

}

