package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.User;

import java.io.IOException;


public class SmallContactListItem extends AnchorPane {


    @FXML
    private Label newConvoContactNameLabel;

    @FXML
    private ImageView newConvoContactProfileImageView;

    private boolean isClicked;

    private User user;


    SmallContactListItem(User user) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/NewConvoContactListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        isClicked = false;

        this.user = user;
        this.newConvoContactNameLabel.setText(user.getFullName());
        Image profileImage = new Image(user.getProfileImagePath());
        this.newConvoContactProfileImageView.setImage(profileImage);
    }

    @FXML
    public void newConvoContactClicked() {
        if (this.isClicked) {
            this.isClicked = false;
            this.setStyle("-fx-background-color: #f7efef");
        } else {
            this.isClicked = true;
            this.setStyle("-fx-background-color: #ada9a9");
        }

    }

    public User getUser() {
        return user;
    }

    boolean isClicked() {
        return isClicked;
    }

    void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
