package view;

import controller.IChatController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import model.*;


import java.awt.*;

import java.io.IOException;

public class ChatView extends AnchorPane implements IChatController{


    private IMainModel mainModel = MainModel.getInstance();


    @FXML
    FlowPane ChatFlowPane;

    @FXML
    ScrollPane ChatScrollPane;

    @FXML
    Button SendButton;

    @FXML
    TextArea chatTextArea;

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
    public void keyPressed(KeyEvent e) {
        if(e.getCode().equals(KeyCode.ENTER)) {
            if(e.isShiftDown()) {
                chatTextArea.setText(chatTextArea.getText()+"\n");
                chatTextArea.end();
            }
            else {
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
}

