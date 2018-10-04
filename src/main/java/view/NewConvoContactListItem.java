package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.IMainModel;
import model.MainModel;
import model.data.User;

import java.io.IOException;


public class NewConvoContactListItem extends AnchorPane {


    @FXML
    Label newConvoContactNameLabel;

    @FXML
    ImageView newConvoContactProfileImageView;

    boolean isAdded;

    boolean isFocused;

    User user;


    public NewConvoContactListItem(User user) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/NewConvoContactListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        isAdded = false;
        isFocused = false;

        this.user = user;
        this.newConvoContactNameLabel.setText(user.getFirstName());
        this.newConvoContactProfileImageView.setImage(null); //TODO add image
    }

    @FXML
    public void newConvoContactClicked() {
        //this.setBackground(new Background(new BackgroundFill(Color.web("#ada9a9"), CornerRadii.EMPTY, Insets.EMPTY)));
        if (this.isFocused) {
            this.isFocused = false;
            this.setStyle("-fx-background-color: #f7efef");
        } else {
            this.isFocused = true;
            this.setStyle("-fx-background-color: #ada9a9");
        }

    }

    public User getUser() {
        return user;
    }
}
