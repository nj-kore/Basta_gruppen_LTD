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

public class ChatView extends AnchorPane implements IChatView{


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
    @Override
    public String getInputText() {
        return chatTextArea.getText();
    }

    @Override
    public void createNewLine() {
        chatTextArea.setText(chatTextArea.getText() + "\n");
        chatTextArea.end();
    }

    @Override
    public void clearInputField() {
        chatTextArea.clear();
    }

    @Override
    public String getChatNameText() {
        return chatNameTextField.getText();
    }

    @Override
    public boolean chatNameIsFocused() {
        return chatNameTextField.isFocused();
    }

    @Override
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

    @Override
    public void setChatAreaFocused() {
        chatTextArea.requestFocus();
    }

    private class MessageItem extends AnchorPane {

        @FXML
        private Label messageUserNameLabel;

        @FXML
        private ImageView messageImageView;

        @FXML
        private TextFlow messageTextFlow;

        public MessageItem(Message message, User concreteUser){
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

