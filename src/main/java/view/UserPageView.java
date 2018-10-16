package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public UserPageView(MainView parentView, MainModel mainModel) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/UserPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parent = parentView;
        this.mainModel = mainModel;
    }

    void updateUserInfoTextFields() {
        firstNameTextField.setText(mainModel.getActiveUser().getFirstName());
        lastNameTextField.setText(mainModel.getActiveUser().getLastName());
        emailTextField.setText(mainModel.getActiveUser().getEmail());
    }

    /**
     * Calls upon a function in the parentView to return to the chatView.
     * Hides feedback labels so that they are not shown the next time the user enters.
     */
    @FXML
    public void backToChat(){
        passwordChangedLabel.setVisible(false);
        wrongPasswordLabel.setVisible(false);
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
        mainModel.setActiveUser(null);
        parent.displayLoginPage();
    }


    /**
     * Updates the model with info inserted into firstName-, lastName- and emailTextFields.
     */
    @FXML
    private void saveInfoChange(){
        /*REWORKING
        mainModel.getActiveUser().setFirstName(firstNameTextField.getText());
        mainModel.getActiveUser().setLastName(lastNameTextField.getText());
        mainModel.getActiveUser().setEmail(emailTextField.getText());
        */
        mainModel.setUserInfo(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText()); //TODO
        enableInfoTextFields(false);

        changePictureButton.setVisible(false);
        changePictureButton.setDisable(true);
        saveChangesButton.setDisable(true);
        saveChangesButton.setVisible(false);
        parent.updateCurrentUserInfo();
    }

    /**
     * Enables firstName-, lastName- and emailTextField to be edited and changes their style to show the user that they can be edited.
     * @param enableEdit
     * true to enable edit
     * false to disable
     */
    private void enableInfoTextFields(boolean enableEdit){
        if(enableEdit){
            firstNameTextField.setEditable(true);
            lastNameTextField.setEditable(true);
            emailTextField.setEditable(true);

            firstNameTextField.setFocusTraversable(true);
            lastNameTextField.setFocusTraversable(true);
            emailTextField.setFocusTraversable(true);

            firstNameTextField.setStyle("-fx-border-insets: 2px");
            emailTextField.setStyle("-fx-border-insets: 2px");
            lastNameTextField.setStyle("-fx-border-insets: 2px");

            firstNameTextField.setStyle("-fx-background-color: rgb(70,73,78)");
            emailTextField.setStyle("-fx-background-color: rgb(70,73,78)");
            lastNameTextField.setStyle("-fx-background-color: rgb(70,73,78)");

            firstNameTextField.requestFocus();
        }
        else{
            firstNameTextField.setEditable(false);
            lastNameTextField.setEditable(false);
            emailTextField.setEditable(false);

            firstNameTextField.setFocusTraversable(false);
            lastNameTextField.setFocusTraversable(false);
            emailTextField.setFocusTraversable(false);

            firstNameTextField.setStyle("-fx-border-insets: 0px");
            emailTextField.setStyle("-fx-border-insets: 0px");
            lastNameTextField.setStyle("-fx-border-insets: 0px");

            firstNameTextField.setStyle("-fx-background-color: transparent");
            emailTextField.setStyle("-fx-background-color: transparent");
            lastNameTextField.setStyle("-fx-background-color: transparent");
        }

    }

    /**
     * Changes the users password.
     */
    @FXML
    private void changePassword(){
        if(oldPasswordField.getText().equals(mainModel.getActiveUser().getPassword())){
            wrongPasswordLabel.setVisible(false);
            if(!newPasswordField.getText().equals("")) {
                mainModel.getActiveUser().setPassword(newPasswordField.getText());
                passwordChangedLabel.setVisible(true);
            }
        } else {
            wrongPasswordLabel.setVisible(true);
        }
    }

    /**
     * Opens a FileChooser to allow the user to change profile picture.
     */
    @FXML
    private void changePicture(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null){
            mainModel.getActiveUser().setProfileImagePath(
                    selectedFile.toURI().toString());   //selectedFile.getPath();
            profilePicImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));       //"../../resources/" + selectedFile.getName())
        }
    }
}
