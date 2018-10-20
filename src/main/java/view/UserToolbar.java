package view;

import controller.IControllerFactory;
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
import model.StatusType;

import java.io.IOException;

public class UserToolbar extends AnchorPane {

    private MainModel mainModel;
    private IMainView mainView;
    private IControllerFactory factory;

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

    public UserToolbar(IMainView mainView, MainModel mainModel, IControllerFactory factory) {
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
        this.factory=factory;
    }

    public void init(){
        IUserToolbarController u =factory.getUserToolBarController(mainModel, mainView);
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
        for (StatusType status : StatusType.values()){
            MenuItem m;
            if(status == StatusType.Available){
                ImageView imageView = new ImageView("pics/statusGreen.png");
                m=new MenuItem(status.toString(), imageView);
            }else if(status == StatusType.Busy){
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
                    u.onMenuButtonItemClicked(m.getText());
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
