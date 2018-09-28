package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Conversation;
import model.User;
import java.io.IOException;
import java.util.HashMap;

public class ConversationListItem extends AnchorPane{

    @FXML
    ImageView conversationProfileImageView;

    @FXML
    ImageView conversationStatusImageView;

    @FXML
    Label conversationNameLabel;

    @FXML
    Label conversationStatusLabel;


    public ConversationListItem(Conversation conversation) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ConversationListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

}
