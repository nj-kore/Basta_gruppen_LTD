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
import model.*;
import model.Message;


import java.io.IOException;
import java.util.Iterator;

public class ChatView extends AnchorPane{


    private MainModel mainModel;


    @FXML
    private FlowPane chatFlowPane;

    @FXML
    private ScrollPane chatScrollPane;

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

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chatController.onSendButtonClicked();
            }
        });

        chatTextArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                chatController.onChatAreaKeyPressed(event);
            }
        });

        chatNameTextField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                chatController.onChangeChatNameClicked();
            }
        });

        chatNameTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                chatController.onConversationNameKeyPressed(event);
            }
        });

        changeChatNameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chatController.onChangeChatNameClicked();
            }
        });
    }
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
    public void update() {
        loadMessages();
        chatNameTextField.setText(mainModel.getActiveConversation().getName());
    }
    public String getInputText() {
        return chatTextArea.getText();
    }

    public void createNewLine() {
        chatTextArea.setText(chatTextArea.getText() + "\n");
        chatTextArea.end();
    }

    public void clearInputField() {
        chatTextArea.clear();
    }

    public String getChatNameText() {
        return chatNameTextField.getText();
    }

    public boolean chatNameIsFocused() {
        return chatNameTextField.isFocused();
    }

    public void setChatNameEditable(boolean editable) {
        chatNameTextField.setEditable(editable);
        if(editable) {
            chatNameTextField.setStyle("-fx-background-color: cyan;");
            chatNameTextField.requestFocus();
        }
        else {
            chatNameTextField.setStyle("-fx-background-color: white;");
        }

    }
    public void setChatAreaFocused() {
        chatTextArea.requestFocus();
    }
}

