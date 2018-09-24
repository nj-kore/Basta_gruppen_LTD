package view;

import controller.ChatController;
import controller.IChatController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import model.*;
import view.MainView;


import java.awt.*;

import java.io.IOException;

public class ChatView extends AnchorPane {

    private final IChatController chatController = new ChatController();
    private IMainModel mainModel = MainModel.getInstance();


    @FXML
    FlowPane ChatFlowPane;

    @FXML
    ScrollPane ChatScrollPane;

    @FXML
    Button SendButton;

    @FXML
    TextArea ChatTextArea;

    public ChatView(){
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
    public void sendMessage(){
        if(!ChatTextArea.getText().isEmpty()) {             //User should not be able to send an empty message.
            chatController.sendMessage(ChatTextArea.getText().trim());
            ChatTextArea.clear();
        }
        loadMessages();
    }

    public FlowPane getChatFlowPane() {
        return ChatFlowPane;
    }

    public void loadMessages(){
        getChatFlowPane().getChildren().clear();
        //for(Message m : getConversation().getMessages() ){
          //  getChatFlowPane().getChildren().add(new view.MessageItem(m));
        //}
        for(Message m : mainModel.loadConversation(mainModel.getActiveConversation().getId()).getMessages()){
            ChatFlowPane.getChildren().add(new MessageItem(m));
        }
    }

}

