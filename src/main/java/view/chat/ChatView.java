package view.chat;

/**
 * @author Jonathan Köre
 * @author Benjamin Vinnerholt
 * @author Gustav Häger
 * @author Filip Andréasson
 * @author Gustaf Spjut
 */

import controller.IChatController;
import controller.IControllerFactory;
import controller.participants.IParticipantsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.MainModel;
import model.Message;
import view.*;


import java.io.IOException;
import java.util.Iterator;

/**
 * The View in which the current on-going conversations are displayed.
 */
public class ChatView extends AnchorPane implements IChatView {


    private MainModel mainModel;
    private AbstractParticipantView removeParticipantsView;
    private AbstractParticipantView addParticipantsView;
    private IMainView mainView;


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
    private MenuItem viewParticipantsMenuItem;

    @FXML
    private MenuItem addParticipantsMenuItem;

    @FXML
    private ImageView acceptImageView;

    @FXML
    private ImageView declineImageView;

    @FXML
    private ImageView participantsImageView;

    @FXML
    private Label participantsLabel;


    @FXML
    private AnchorPane participantsAnchorPane;


    private String editingColor = "-fx-background-color: cyan;";
    private String notEditingColor = "-fx-background-color: white;";

    @FXML
    private Button createUserButton;


    /**
     * @param mainModel Initialises the ChatViews components and links all the controlling input to an IChatController
     * @param mainView The parent MainView of the ChatView.
     */
    public ChatView(MainModel mainModel, IMainView mainView, IControllerFactory factory) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ChatView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        this.mainModel = mainModel;
        removeParticipantsView = new RemoveParticipantsView(mainModel, this);
        IParticipantsController removeParticipantsController=factory.createRemoveParticipantsController(removeParticipantsView, mainModel);
        removeParticipantsView.bindController(removeParticipantsController);
        addParticipantsView = new AddParticipantsView(mainModel, this);
        IParticipantsController addParticipantsController=factory.createAddParticipantsController(addParticipantsView, mainModel);
        addParticipantsView.bindController(addParticipantsController);
        this.mainView = mainView;

    }

    /**
     * Loads the messages from the model and displays them as MessageItems
     */

    public void bindController(IChatController controller){
        createUserButton.setOnMouseClicked(event -> mainView.displayCreateUserView());

        sendButton.setOnAction(event -> controller.onSendButtonClicked());

        chatTextArea.setOnKeyPressed(controller::onChatAreaKeyPressed);

        chatNameTextField.setOnMouseClicked(event -> controller.onChangeChatNameClicked());

        chatNameTextField.setOnKeyPressed(controller::onChatNameKeyPressed);

        changeChatNameMenuItem.setOnAction(event -> controller.onChangeChatNameClicked());

        leaveChatMenuItem.setOnAction(event -> controller.onLeaveChatClicked());

        acceptImageView.setOnMouseClicked(event -> controller.onChatNameAccept());

        declineImageView.setOnMouseClicked(event -> controller.onChatNameDecline());

        chatNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                controller.onChatNameDecline();
            }
        });
    }
    private void loadMessages() {
        chatFlowPane.getChildren().clear();
        Iterator<Message> itr = mainModel.loadMessagesInConversation();
        if (itr != null) {
            while (itr.hasNext()) {
                Message message = itr.next();
                chatFlowPane.getChildren().add(new MessageItem(message, mainModel.getUser(message.getSenderId())));
            }
        }
    }

    public void creatUserButtonVisible(){
        createUserButton.setVisible(true);
    }
    public void createUserButtonInVisible(){
        createUserButton.setVisible(false);
    }

    /**
     * Updates the ChatView to be up to date with the model
     */
    public void update() {
        loadMessages();
        loadChatName();
        loadParticipants();
    }

    public void init() {
        if(mainModel.getActiveUser().getIsManager()){
            createUserButton.setVisible(true);
        }
        this.participantsImageView.setImage(new Image("pics/userIcon.png"));
        update();
    }

    private void loadParticipants() {
        if (mainModel.getActiveConversation() != null && mainModel.getActiveConversation().getParticipants() != null) {//There is an active conversation from the start with id -1
            participantsLabel.setText(Integer.toString(mainModel.getActiveConversation().getParticipants().size()));
            participantsLabel.setVisible(true);
            participantsImageView.setVisible(true);
        }
    }

    private void loadChatName() {
        String name = mainModel.getActiveConversation().getName();
        if (name.length() > 0) {
            chatNameTextField.setText(mainModel.getActiveConversation().getName());
        } else {
            chatNameTextField.setText(mainModel.generatePlaceholderName(mainModel.getActiveConversation()));
        }
    }


    public void setDefaultConversation() {
        mainView.setDefaultConversation();
    }



    /**
     * Makes the ChatName editable and makes the accept and decline button visible
     *
     * @param editable
     */
    private void setChatNameEditable(boolean editable) {
        chatNameTextField.setEditable(editable);
        acceptImageView.setVisible(editable);
        declineImageView.setVisible(editable);
        if (editable) {
            chatNameTextField.setStyle(editingColor);
        } else {
            chatNameTextField.setStyle(notEditingColor);
        }
    }

    @FXML
    private void displayRemoveParticipants(){
        participantsAnchorPane.getChildren().clear();
        participantsAnchorPane.getChildren().add(removeParticipantsView);
        removeParticipantsView.update();
        participantsAnchorPane.toFront();
    }

    @FXML
    private void displayAddParticipants(){
        participantsAnchorPane.getChildren().clear();
        participantsAnchorPane.getChildren().add(addParticipantsView);
        addParticipantsView.update();
        participantsAnchorPane.toFront();
    }


    @Override
    public void closeParticipantsView() {
        participantsAnchorPane.toBack();
    }



    /**
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
     * @return the text inside the chatNameTextField
     */
    @Override
    public String getChatNameText() {
        return chatNameTextField.getText();
    }




}

