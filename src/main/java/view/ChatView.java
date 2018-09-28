package view;

import controller.IChatController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.*;
import model.data.Message;


import java.io.IOException;

public class ChatView extends AnchorPane implements IChatController {


    private IMainModel mainModel = MainModel.getInstance();


    @FXML
    FlowPane ChatFlowPane;

    @FXML
    ScrollPane ChatScrollPane;

    @FXML
    Button SendButton;

    @FXML
    TextArea chatTextArea;

    @FXML
    TextField chatNameTextField;

    @FXML
    MenuButton optionsMenuButton = new MenuButton("Options", new ImageView(new Image("pics/optionsIcon.png")));

    @FXML
    MenuItem changeChatNameMenuItem;


    public ChatView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ChatView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        loadMessages();

    }


    @FXML
    public void sendMessage() {
        if (!chatTextArea.getText().trim().isEmpty()) {             //User should not be able to send an empty message.
            mainModel.sendMessage(chatTextArea.getText().trim());
            chatTextArea.clear();
        }
        loadMessages();
    }


    //Function takes in a KeyEvent. If enter is pressed, the message written in the chatTextArea gets sent.
    //If shift in combination with enter is pressed, it adds a new line to the message.
    //The function only gets called if the chatTextArea is focused
    @FXML
    public void chatAreaKeyPressed(KeyEvent e) {
        if (e.getCode().equals(KeyCode.ENTER)) {
            if (e.isShiftDown()) {
                chatTextArea.setText(chatTextArea.getText() + "\n");
                chatTextArea.end();
            } else {
                sendMessage();
                e.consume();
            }
        }
    }


    public FlowPane getChatFlowPane() {
        return ChatFlowPane;
    }

    public void loadMessages() {
        getChatFlowPane().getChildren().clear();
        //for(Message m : getConversation().getMessages() ){
        //  getChatFlowPane().getChildren().add(new view.MessageItem(m));
        //}
        for (Message m : mainModel.loadConversation(mainModel.getActiveConversation().getId()).getMessages()) {
            ChatFlowPane.getChildren().add(new MessageItem(m));
        }
    }


    @FXML
    public void chatNameChanged() {
        mainModel.getActiveConversation().setName(chatNameTextField.getText());
        System.out.println("reeeeee");
        chatNameTextField.setEditable(false);
    }


    @FXML
    void changeChatName() {
        chatNameTextField.setEditable(true);
        chatNameTextField.setStyle("-fx-background-insets: 2px;");
        chatNameTextField.requestFocus();

        //Starts a thread that waits for the convo name to lose focus.
        //When this happens, it calls on changeChatName
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(chatNameTextField.isFocused()) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                chatNameChanged();
            }
        });
        t.start();
    }

    //when enter is pressed, the chatTextArea gains focus, which makes the chatNameTextArea lose focus, which saves
    //the chatName
    @FXML
    public void conversationNameKeyPressed(KeyEvent e) {
        if (e.getCode().equals(KeyCode.ENTER)) {
            chatTextArea.requestFocus();
        }
    }
}

