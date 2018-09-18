import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;


import java.awt.*;
import java.io.IOException;

public class ChatController extends AnchorPane {

    @FXML
    FlowPane ChatFlowPane;

    @FXML
    Button SendButton;

    @FXML
    TextArea ChatTextArea;

    public ChatController(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/fxml/ChatView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
