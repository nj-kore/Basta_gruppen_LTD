import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.Message;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class MessageItem extends AnchorPane{
    private Message message;


    @FXML
    Label messageNameLabel;

    @FXML
    ImageView messageImageView;

    @FXML
    TextArea messageTextArea;

    MessageItem(Message message){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/Message.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        this.message = message;
        messageTextArea.setText(message.getText());


    }

}
