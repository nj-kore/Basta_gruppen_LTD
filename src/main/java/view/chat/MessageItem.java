package view.chat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Message;
import model.User;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * The class that is used as a visual representation for the message in the chat.
 */
public class MessageItem extends AnchorPane {

    @FXML
    private Label messageUserNameLabel;

    @FXML
    private ImageView messageImageView;

    @FXML
    private TextFlow messageTextFlow;

    @FXML
    private Label messageTimeStampLabel;

    /**
     * Initialises the MessageItem and builds the item according to the data found inside the two parameters
     *
     * @param message
     * @param concreteUser
     */
    public MessageItem(Message message, User concreteUser) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Message.fxml"));
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        messageTimeStampLabel.setText(message.getTime().format(formatter));
    }
}