package view;

import controller.IControllerFactory;
import controller.IUserToolbarController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.MainModel;
import model.StatusType;

import java.io.IOException;

class UserToolbar extends AnchorPane {

    private MainModel mainModel;
    private IMainView mainView;
    private IUserToolbarController controller;

    @FXML
    private
    ImageView currentUserImageView;

    @FXML
    private
    ImageView statusImageView;

    @FXML
    MenuButton statusMenu;

    @FXML
    private
    ImageView optionsImage;

    @FXML
    private
    Label currentUserNameLabel;

    UserToolbar(IMainView mainView, MainModel mainModel, IUserToolbarController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UserToolbar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainModel = mainModel;
        this.mainView = mainView;
        this.controller=controller;
    }

    void init(){
        addPremadeStatuses(controller);
        currentUserNameLabel.setText(mainModel.getActiveUser().getUsername());
        optionsImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.onOptionsButtonClicked();
            }
        });
        updateCurrentUserInfo();

    }

    private void addPremadeStatuses(IUserToolbarController u){
        for (StatusType status : StatusType.values()){
            MenuItem menuItem;
            if(status == StatusType.Available){
                ImageView imageView = new ImageView("pics/statusGreen.png");
                menuItem=new MenuItem(status.toString(), imageView);
            }else if(status == StatusType.Busy){
                ImageView imageView = new ImageView("pics/statusOrange.png");
                menuItem=new MenuItem(status.toString(), imageView);
            }else{
                ImageView imageView = new ImageView("pics/statusRed.png");
                menuItem=new MenuItem(status.toString(), imageView);
            }
            statusMenu.getItems().add(menuItem);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    u.onMenuButtonItemClicked(menuItem.getText());
                }
            });
        }
    }

    void updateCurrentUserInfo(){
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
        statusImageView.setImage(new Image(mainModel.getActiveUser().getStatusImagePath()));
        statusMenu.setText(mainModel.getActiveUser().getStatus().toString());
    }

    void setCurrentUserImageView(){
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
    }

}
