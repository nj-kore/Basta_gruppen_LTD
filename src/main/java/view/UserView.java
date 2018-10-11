package view;

import controller.IUserController;
import controller.UserController;
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

import java.io.IOException;

public class UserView extends AnchorPane {

    private MainModel mainModel;
    private MainView mainView;

    @FXML
    ImageView currentUserImageView;

    @FXML
    ImageView statusImageView;

    @FXML
    MenuButton statusMenu;

    @FXML
    ImageView optionsImage;

    @FXML
    Label currentUserNameLabel;

    public UserView(IMainView parentView, MainModel mainModel, MainView mainView) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/UserView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainModel = mainModel;
        this.mainView = mainView;


    }

    public void init(){
        IUserController u = new UserController(this, mainModel, mainView);
        addPremadeStatuses(u);
        currentUserNameLabel.setText(mainModel.getActiveUser().getUsername());
        optionsImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                u.onOoptionsButtonClicked();
            }
        });

    }

    private void addPremadeStatuses(IUserController u){
        int counter=0;
        for (String status : mainModel.getActiveUser().getPremadeStatuses()){
            MenuItem m;
            if(counter==0){
                ImageView imageView = new ImageView("pics/statusGreen.png");
                m=new MenuItem(status, imageView);
            }else if(counter==1){
                ImageView imageView = new ImageView("pics/statusOrange.png");
                m=new MenuItem(status, imageView);
            }else{
                ImageView imageView = new ImageView("pics/statusRed.png");
                m=new MenuItem(status, imageView);
            }
            statusMenu.getItems().add(m);
            m.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    u.onMenuButtonItemClicked(m);
                }
            });
            counter++;
        }
    }


    public void updateCurrentUserInfo(){
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
        statusImageView.setImage(new Image((mainModel.getActiveUser().getStatusImagePath())));
        statusMenu.setText(mainModel.getActiveUser().getStatus());
    }

    public void setCurrentUserImageView(){
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
    }

}
