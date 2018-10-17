package view;

import controller.ParticipantsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.MainModel;
import model.User;

import java.io.IOException;

public class ParticipantsView extends AnchorPane {

    MainModel mainModel;
    IChatView chatView;

    @FXML
    FlowPane participantsFlowPane;

    @FXML
    TextField searchParticipantsTextField;

    @FXML
    ImageView searchParticipantsImageView;

    public ParticipantsView(MainModel mainModel, IChatView chatView) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ParticipantList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainModel = mainModel;
        this.chatView = chatView;

        ParticipantsController controller = new ParticipantsController(this, mainModel);


    }



    public void updateParticipants(){
        participantsFlowPane.getChildren().clear();
        for(User u : mainModel.getActiveConversation().getParticipants()){
            participantsFlowPane.getChildren().add(new ParticipantItem(u));
        }
    }

    @FXML
    private void closeParticipants(){
        chatView.closeParticipants();
    }

    private class ParticipantItem extends AnchorPane {

        @FXML
        ImageView profilePictureImageView;
        @FXML
        Label nameLabel;
        @FXML
        ImageView statusImageView;
        @FXML
        ImageView removeParticipantImageView;
        @FXML
        Label statusLabel;

        public ParticipantItem(User user) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ParticipantListItem.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
            nameLabel.setText(user.getFullName());
            profilePictureImageView.setImage(new Image(user.getProfileImagePath()));
            statusImageView.setImage(new Image(user.getStatusImagePath()));
            statusLabel.setText(user.getStatus().toString());
        }
    }
}
