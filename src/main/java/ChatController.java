import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import model.*;


import java.awt.*;
import java.io.IOException;

public class ChatController extends AnchorPane {

    private Conversation conversation;
    private final MainModel mainModel = new MainModel();

    final private User currentUser;
    final private MainController parent;

    @FXML
    FlowPane ChatFlowPane;

    @FXML
    ScrollPane ChatScrollPane;

    @FXML
    Button SendButton;

    @FXML
    TextArea ChatTextArea;

    public ChatController(MainController parent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/ChatView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        this.parent = parent;
        currentUser = parent.currentUser;
        conversation = new Conversation(1);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }



    @FXML
    public void sendMessage(){
        if(!ChatTextArea.getText().isEmpty()) {             //User should not be able to send an empty message.
            conversation.addMessage(new Message(currentUser, ChatTextArea.getText().trim()));       //trim removes excess newlines in message
            //mainModel.sendMessage(conversation.getId(), new Message(currentUser, ChatTextArea.getText()));
            ChatTextArea.clear();
            loadMessages();
        }
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public MainModel getMainModel() {
        return mainModel;
    }

    public FlowPane getChatFlowPane() {
        return ChatFlowPane;
    }

    public void loadMessages(){
        getChatFlowPane().getChildren().clear();
        for(Message m : getConversation().getMessages() ){
            getChatFlowPane().getChildren().add(new MessageItem(m));
        }
    }

}

