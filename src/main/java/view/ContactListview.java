package view;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import model.IMainModel;
import model.MainModel;
import model.User;

public class ContactListview {

    private IMainModel mainModel = MainModel.getInstance();

    @FXML
    FlowPane contactFlowPane;

    public void loadContacts(){
        for (User u:mainModel.getActiveUser().getContacts()){
            //ContactListItem
        }
    }

}
