package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.IMainModel;
import model.MainModel;

import java.io.IOException;

public class UserPageView extends AnchorPane {

    MainView parent;
    IMainModel mainModel = new MainModel();

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

    public UserPageView(MainView parentView) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/UserPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parent = parentView;
    }

    @FXML
    public void backToMain(){
        passwordChangedLabel.setVisible(false);
        parent.displayMainView();
    }

    @FXML
    private void editInfo(){
        enableInfoTextFields(true);
        changePictureButton.setVisible(true);
        changePictureButton.setDisable(false);

        saveChangesButton.setVisible(true);
        saveChangesButton.setDisable(false);
    }

    @FXML
    private void saveInfoChange(){
        mainModel.getActiveUser().setFirstName(firstNameTextField.getText());
        mainModel.getActiveUser().setLastName(lastNameTextField.getText());
        mainModel.getActiveUser().setEmail(emailTextField.getText());
        enableInfoTextFields(false);
    }

    private void enableInfoTextFields(boolean enableEdit){
        if(enableEdit){
            firstNameTextField.setEditable(true);
            lastNameTextField.setEditable(true);
            emailTextField.setEditable(true);

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

            firstNameTextField.setStyle("-fx-border-insets: 0px");
            emailTextField.setStyle("-fx-border-insets: 0px");
            lastNameTextField.setStyle("-fx-border-insets: 0px");

            firstNameTextField.setStyle("-fx-background-color: transparent");
            emailTextField.setStyle("-fx-background-color: transparent");
            lastNameTextField.setStyle("-fx-background-color: transparent");
        }

    }

    @FXML
    private void changePassword(){
        if(oldPasswordField.getText().equals(mainModel.getActiveUser().getPassword())){
            wrongPasswordLabel.setVisible(false);
            if(!newPasswordField.getText().equals("")) {
                mainModel.getActiveUser().setPassword(newPasswordField.getText());
                passwordChangedLabel.setVisible(true);
            }
        } else wrongPasswordLabel.setVisible(true);
    }
}
