package view;

import controller.IUserToolbarController;
import controller.UserToolbarController;
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

public class UserToolbar extends AnchorPane {

    private MainModel mainModel;
    private IMainView mainView;

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

    public UserToolbar(IMainView mainView, MainModel mainModel) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/UserToolbar.fxml"));
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
        IUserToolbarController u = new UserToolbarController(mainModel, mainView);
        addPremadeStatuses(u);
        currentUserNameLabel.setText(mainModel.getActiveUser().getUsername());
        optionsImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                u.onOptionsButtonClicked();
            }
        });
        updateCurrentUserInfo();

    }

    private void addPremadeStatuses(IUserToolbarController u){
        for (MainModel.StatusType status : MainModel.StatusType.values()){
            MenuItem m;
            if(status == MainModel.StatusType.Available){
                ImageView imageView = new ImageView("pics/statusGreen.png");
                m=new MenuItem(status.toString(), imageView);
            }else if(status == MainModel.StatusType.Busy){
                ImageView imageView = new ImageView("pics/statusOrange.png");
                m=new MenuItem(status.toString(), imageView);
            }else{
                ImageView imageView = new ImageView("pics/statusRed.png");
                m=new MenuItem(status.toString(), imageView);
            }
            statusMenu.getItems().add(m);
            m.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    u.onMenuButtonItemClicked(m);
                }
            });
        }
    }


    public void updateCurrentUserInfo(){
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
        statusImageView.setImage(new Image(mainModel.getActiveUser().getStatusImagePath()));
        statusMenu.setText(mainModel.getActiveUser().getStatus().toString());
    }

    public void setCurrentUserImageView(){
        currentUserImageView.setImage(new Image(mainModel.getActiveUser().getProfileImagePath()));
    }

}
