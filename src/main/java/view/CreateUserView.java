package view;

import controller.IControllerFactory;
import controller.ICreateUserViewController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.MainModel;

import java.io.IOException;

public class CreateUserView extends AnchorPane implements ICreateUserView {

    private IMainView mainView;
    MainModel mainModel;

    @FXML
    private TextField userNameText;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private CheckBox isAdminCheckBox;

    @FXML
    private Button createUser;

    @FXML
    private Button cancelCreateUser;

    @FXML
    private Label inputHintLabel;

    CreateUserView(IMainView mainView, MainModel mainModel, IControllerFactory factory) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CreateUserView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainModel = mainModel;
        this.mainView = mainView;

        ICreateUserViewController c = factory.getCreateUserViewController(mainModel, mainView, this);
        cancelCreateUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                c.cancelCreateUser();
            }
        });
        createUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (checkText(userNameText.getText(), userNameText.getText())) {
                    inputHintLabel.setVisible(true);
                }else if (checkText(firstNameText.getText(), firstNameText.getText())){
                    inputHintLabel.setVisible(true);
                }else if (checkText(lastNameText.getText(), lastNameText.getText())){
                    inputHintLabel.setVisible(true);
                }else if(checkText(passwordText.getText(), passwordText.getText())){
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

    private Boolean checkText(String isnull, String trim){
        return isnull == null || trim.trim().isEmpty();
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
