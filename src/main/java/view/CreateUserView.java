package view;

import controller.CreateUserViewController;
import controller.ICreateUserViewController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.MainModel;

import java.io.IOException;

public class CreateUserView extends AnchorPane {

    MainView mainView;
    MainModel mainModel;

    @FXML
    TextField userNameText;

    @FXML
    TextField firstNameText;

    @FXML
    TextField lastNameText;

    @FXML
    PasswordField passwordText;

    @FXML
    CheckBox isAdminCheckBox;

    @FXML
    Button createUser;

    @FXML
    Button cancelCreateUser;

    @FXML
    Label inputHintLabel;

    public CreateUserView(MainView mainView, MainModel mainModel) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/CreateUserView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainModel = mainModel;
        this.mainView = mainView;

        ICreateUserViewController c = new CreateUserViewController(mainModel, mainView, this) {

        };
        cancelCreateUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                c.cancelCreateUser();
            }
        });
        createUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (userNameText.getText()==null || userNameText.getText().trim().isEmpty()){
                    inputHintLabel.setVisible(true);
                }else if (firstNameText.getText()==null || firstNameText.getText().trim().isEmpty()){
                    inputHintLabel.setVisible(true);
                }else if (lastNameText.getText()== null || lastNameText.getText().trim().isEmpty()){
                    inputHintLabel.setVisible(true);
                }else if(passwordText.getText()==null || passwordText.getText().trim().isEmpty()){
                    inputHintLabel.setVisible(true);
                }else {
                    c.createUser();
                    inputHintLabel.setVisible(false);
                    userNameText.clear();
                    firstNameText.clear();
                    lastNameText.clear();
                    passwordText.clear();
                    isAdminCheckBox.setSelected(false);
                }
            }
        });

    }

    public String getUserName(){
        return userNameText.getText();
    }
    public String getFirstName(){
        return firstNameText.getText();
    }
    public String getlastName(){
        return lastNameText.getText();
    }
    public String getPassword(){
        return passwordText.getText();
    }

    public Boolean getAdminCheckBox(){
        return isAdminCheckBox.isSelected();
    }

    public void setButtonAction(EventHandler e) {
        createUser.setOnAction(e);
    }



}
