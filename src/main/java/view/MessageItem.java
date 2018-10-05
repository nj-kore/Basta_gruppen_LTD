package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Message;
import model.User;


import java.io.IOException;

public class MessageItem extends AnchorPane{


    @FXML
    Label messageUserNameLabel;

    @FXML
    ImageView messageImageView;

    @FXML
    TextFlow messageTextFlow;

    public MessageItem(Message message, User concreteUser){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/Message.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        messageImageView.setImage(concreteUser.getProfileImage());
        messageUserNameLabel.setText(concreteUser.getFullName());
        messageTextFlow.getChildren().add(new Text(message.getText()));


    }

}
