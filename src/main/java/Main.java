import infrastructure.IDataHandler;
import infrastructure.JsonHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.data.Conversation;
import model.MainModel;
import model.data.User;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/MainView.fxml"));

        Scene scene = new Scene(root, 1280, 720);

        stage.setTitle("Shat app");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();


        IDataHandler dh = new JsonHandler();
        dh.saveUser(new User(1,"hej","hej","hej","hejsson"));
        dh.saveUser(new User(2,"Bert","hej","hej","hejsson"));
        dh.saveUser(new User(3,"Bert","hej","hej","hejsson"));


    }



    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}