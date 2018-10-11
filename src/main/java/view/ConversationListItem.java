package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.MainModel;
import model.Conversation;

import java.io.IOException;

/**
 * ConversationListItem is view that displays one conversation as an item
 */
public class ConversationListItem extends AnchorPane{

    private MainModel mainModel;
    private Conversation conversation;

    @FXML
    private ImageView conversationProfileImageView;

    @FXML
    private ImageView conversationStatusImageView;

    @FXML
    private Label conversationNameLabel;

    @FXML
    private Label conversationStatusLabel;


    public ConversationListItem(Conversation conversation, MainModel mainModel) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ConversationListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainModel = mainModel;
        this.conversation = conversation;
        this.conversationNameLabel.setText(conversation.getName());

        if (this.conversation.getParticipants().size() == 2) {
            this.conversationProfileImageView.setImage(new Image(this.conversation.getParticipants().get(0).getStatusImagePath()));
        } else {
            this.conversationProfileImageView.setImage(new Image("pics/groupConvoDefaultImage.jpg"));
        }

    }

    @FXML
    public void conversationListItemClicked() {
        mainModel.setActiveConversation(this.conversation.getId());
    }

}
