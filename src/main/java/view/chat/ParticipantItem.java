package view.chat;

/**
 * @author Benjamin Vinnerholt
 * @since 2018-10-15
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.User;

import java.io.IOException;

public class ParticipantItem extends AnchorPane {

    private User user;
    private boolean isClicked = false;

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

    public ParticipantItem(User user) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ParticipantListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.user = user;

        nameLabel.setText(user.getFullName());
        profilePictureImageView.setImage(new Image(user.getProfileImagePath()));
        statusImageView.setImage(new Image(user.getStatusImagePath()));
        statusLabel.setText(user.getStatus().toString());

    }


    public User getUser(){
        return user;
    }

    @FXML
    private void toggleClicked(){
        this.isClicked = !this.isClicked;
        setClickedColour();
    }

    public void setClicked(boolean clicked){
        isClicked = clicked;
    }

    public boolean getIsClicked(){
        return isClicked;
    }

    public void setClickedColour(){
        if (isClicked) {
            this.setStyle("-fx-background-color: #ada9a9");
        } else {
            this.setStyle("-fx-background-color: transparent");
        }
    }

}

