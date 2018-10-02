package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.data.Message;
import model.data.User;


import java.io.IOException;
import java.sql.SQLOutput;

public class MessageItem extends AnchorPane{
    private Message message;
    private User sender;

    @FXML
    Label messageUserNameLabel;

    @FXML
    ImageView messageImageView;

    @FXML
    TextFlow messageTextFlow;

    public MessageItem(Message message, User sender){
        this.message = message;
        this.sender = sender;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/Message.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.message = message;
        messageImageView.setImage(new Image(sender.getProfileImagePath()));
        messageUserNameLabel.setText(sender.getFullName());
        messageTextFlow.getChildren().add(new Text(message.getText()));


    }

}
