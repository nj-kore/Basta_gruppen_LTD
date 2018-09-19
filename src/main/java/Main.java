import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/MainView.fxml"));

        Scene scene = new Scene(root, 1280, 720);

        stage.setTitle("Shat app");
        stage.setScene(scene);
        stage.setMaximized(true);
        //stage.setResizable(false);
        stage.show();



    }



    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}