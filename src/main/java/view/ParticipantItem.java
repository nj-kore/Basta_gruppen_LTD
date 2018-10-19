package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.User;

import java.io.IOException;

public class ParticipantItem extends AnchorPane {

    IParticipantView participantView;
    private User user;

    @FXML
    private ImageView profilePictureImageView;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView statusImageView;
    @FXML
    private ImageView removeParticipantImageView;
    @FXML
    private Label statusLabel;

    public ParticipantItem(User user, IParticipantView participantView) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ParticipantListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.participantView = participantView;
        this.user = user;

        nameLabel.setText(user.getFullName());
        profilePictureImageView.setImage(new Image(user.getProfileImagePath()));
        statusImageView.setImage(new Image(user.getStatusImagePath()));
        statusLabel.setText(user.getStatus().toString());

    }

    @FXML
    private void participantItemSelected(){
        participantView.select(this);
    }

    public User getUser(){
        return user;
    }

}

