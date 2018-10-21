package view;

import controller.IControllerFactory;
import controller.IUserPageController;
import controller.UserPageController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.MainModel;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class UserPageView extends AnchorPane {

    MainView parent;
    MainModel mainModel;
    String imagePath;



    @FXML
    TextField firstNameTextField;

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField oldPasswordField;

    @FXML
    PasswordField newPasswordField;

    @FXML
    TextField lastNameTextField;

    @FXML
    Button editInfoButton;

    @FXML
    Button saveChangesButton;

    @FXML
    Button changePictureButton;

    @FXML
    Label passwordChangedLabel;

    @FXML
    Label wrongPasswordLabel;

    @FXML
    ImageView profilePicImageView;

    @FXML
    Button changePasswordButton;

    public UserPageView(MainView parentView, MainModel mainModel, IControllerFactory factory) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UserPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parent = parentView;
        this.mainModel = mainModel;

        IUserPageController controller = factory.getUserPageController(mainModel);

        saveChangesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.saveUserInfo(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), imagePath);
                enableInfoTextFields(false);

                changePictureButton.setVisible(false);
                changePictureButton.setDisable(true);
                saveChangesButton.setDisable(true);
                saveChangesButton.setVisible(false);
            }
        });

        changePasswordButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(oldPasswordField.getText().equals(mainModel.getActiveUser().getPassword())){
                    wrongPasswordLabel.setVisible(false);
                    if(!newPasswordField.getText().equals("")) {
                        controller.changePassword(newPasswordField.getText());
                        passwordChangedLabel.setVisible(true);
                    }
                } else {
                    wrongPasswordLabel.setVisible(true);
                }
            }
        });

        changePictureButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if(selectedFile != null){
                    imagePath = selectedFile.toURI().toString();
                    profilePicImageView.setImage(new Image(selectedFile.toURI().toString()));       //View changes itself, not based on model
                }
            }
        });

    }

    void updateUserInfoTextFields() {
        firstNameTextField.setText(mainModel.getActiveUser().getFirstName());
        lastNameTextField.setText(mainModel.getActiveUser().getLastName());
        emailTextField.setText(mainModel.getActiveUser().getEmail());
        profilePicImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
    }

    /**
     * Calls upon a function in the parentView to return to the chatView.
     * Hides feedback labels so that they are not shown the next time the user enters.
     */
    @FXML
    public void backToChat(){
        passwordChangedLabel.setVisible(false);
        wrongPasswordLabel.setVisible(false);
        newPasswordField.clear();
        oldPasswordField.clear();
        parent.backToChat();
    }

    /**
     *Shows and enables the Change Picture button to enable the user to change picture and "switches" the Edit button to Save Changes
     */
    @FXML
    private void editInfo(){
        enableInfoTextFields(true);
        changePictureButton.setVisible(true);
        changePictureButton.setDisable(false);

        saveChangesButton.setVisible(true);
        saveChangesButton.setDisable(false);
    }

    @FXML
    private void logOut(){
        parent.logout();
        newPasswordField.clear();
        oldPasswordField.clear();
    }


    /**
     * Enables firstName-, lastName- and emailTextField to be edited and changes their style to show the user that they can be edited.
     * @param enableEdit
     * true to enable edit
     * false to disable
     */
    private void enableInfoTextFields(boolean enableEdit){
        firstNameTextField.setEditable(enableEdit);
        lastNameTextField.setEditable(enableEdit);
        emailTextField.setEditable(enableEdit);

        firstNameTextField.setFocusTraversable(enableEdit);
        lastNameTextField.setFocusTraversable(enableEdit);
        emailTextField.setFocusTraversable(enableEdit);

        if(enableEdit){

            firstNameTextField.setStyle("-fx-border-insets: 2px");
            emailTextField.setStyle("-fx-border-insets: 2px");
            lastNameTextField.setStyle("-fx-border-insets: 2px");

            firstNameTextField.setStyle("-fx-background-color: rgb(70,73,78)");
            emailTextField.setStyle("-fx-background-color: rgb(70,73,78)");
            lastNameTextField.setStyle("-fx-background-color: rgb(70,73,78)");

            firstNameTextField.requestFocus();
        }
        else{

            firstNameTextField.setStyle("-fx-border-insets: 0px");
            emailTextField.setStyle("-fx-border-insets: 0px");
            lastNameTextField.setStyle("-fx-border-insets: 0px");

            firstNameTextField.setStyle("-fx-background-color: transparent");
            emailTextField.setStyle("-fx-background-color: transparent");
            lastNameTextField.setStyle("-fx-background-color: transparent");
        }

    }
}
