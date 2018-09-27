package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.IMainModel;
import model.MainModel;
import model.User;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends AnchorPane implements Initializable, IMainController{

    ChatView chatView = new ChatView();
    IMainController mainController;
    //ContactListItem contactlist = new ContactListItem();
    private IMainModel mainModel = MainModel.getInstance();


    @FXML
    AnchorPane mainViewAnchorPane;

    @FXML
    FlowPane contactsFlowPane;

    @FXML
    FlowPane conversationsFlowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainViewAnchorPane.getChildren().add(chatView);
        loadContactItems();
    }

    public void loadContactItems(){
        for(User u: mainModel.getContacts()){
            contactsFlowPane.getChildren().add(new ContactListItem(u));
        }
    }


}