package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.IMainModel;
import model.MainModel;
import model.data.User;

import java.io.IOException;


public class NewConvoContactListItem extends AnchorPane {


    @FXML
    private Label newConvoContactNameLabel;

    @FXML
    private ImageView newConvoContactProfileImageView;

    private boolean isClicked;

    private User user;


    public NewConvoContactListItem(User user) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/NewConvoContactListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        isClicked = false;

        this.user = user;
        this.newConvoContactNameLabel.setText(user.getFirstName());
        this.newConvoContactProfileImageView.setImage(user.getProfileImage());
    }

    @FXML
    public void newConvoContactClicked() {
        //this.setBackground(new Background(new BackgroundFill(Color.web("#ada9a9"), CornerRadii.EMPTY, Insets.EMPTY)));
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

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
