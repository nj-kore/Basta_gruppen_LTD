package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.data.Message;



import java.io.IOException;

public class MessageItem extends AnchorPane{
    private Message message;


    @FXML
    Label messageNameLabel;

    @FXML
    ImageView messageImageView;

    @FXML
    TextFlow messageTextFlow;

    public MessageItem(Message message){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/Message.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.message = message;
        messageTextFlow.getChildren().add(new Text(message.getText()));


    }

}
