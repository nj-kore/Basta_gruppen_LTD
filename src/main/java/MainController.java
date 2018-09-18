import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends AnchorPane implements Initializable{

    ChatController chatController;

    @FXML
    AnchorPane MainViewAnchorPane;

    @FXML
    FlowPane ContactsFlowPane;

    @FXML
    FlowPane ConversationsFlowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatController = new ChatController();
        MainViewAnchorPane.getChildren().add(chatController);
    }

}
