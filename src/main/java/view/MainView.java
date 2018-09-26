package view;

import controller.IMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
<<<<<<< HEAD
=======
import model.IMainModel;
import model.MainModel;
import model.User;
>>>>>>> f4b5bff3c7ef5b5bde1337c5fbda274068f5ac77

import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends AnchorPane implements Initializable, IMainController{

    ChatView chatView = new ChatView();
    IMainController mainController;
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
    }

    public void getContacts(){
      for (User u :mainModel.getContacts()){
            //ConversationsFlowPane.getChildren().add(new ContactListItem u);
      }
    }

}