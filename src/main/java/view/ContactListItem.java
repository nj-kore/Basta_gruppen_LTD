package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.User;
import java.io.IOException;

public class ContactListItem extends AnchorPane {


    @FXML
    private ImageView contactProfileImageView;
    @FXML
    private ImageView contactStatusImageView;
    @FXML
    private Label contactNameLabel;
    @FXML
    private Label contactStatusLabel;

    private IMainView mainView;
    private User user;


    public ContactListItem(User user, IMainView mainView) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ContactListItemView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainView = mainView;
        this.user = user;
        contactNameLabel.setText(user.getFullName());
        contactStatusImageView.setImage(new Image(user.getStatusImagePath()));
        contactProfileImageView.setImage(new Image(user.getProfileImagePath()));
        contactStatusLabel.setText(user.getStatus().toString());
    }

    @FXML
    public void contactListItemClicked() {
        mainView.displayContactDetailView(this.user);
    }

}
