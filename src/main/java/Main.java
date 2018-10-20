import infrastructure.IDataLoader;
import infrastructure.JsonSaver;
import infrastructure.JsonLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MainModel;
import model.observerpattern.ModelObserver;
import view.IMainView;
import view.MainView;

import java.io.IOException;

/**
 * The Shat App is a messaging desktop application for business. The app allows you to chat with contacts at work
 *
 * @author Filip Andréasson
 * @author Gustav Häger
 * @author Jonathan Köre
 * @author Gustaf Spjut
 * @author Benjamin Vinnerholt
 *
 * @version 0.5
 * @since 2018-09-09
 */


public class Main extends Application {


    /**
     * @param stage
     * @throws Exception
     *
     * Starts the program and sets the scene to MainView.fxml
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Creates an instance of dataloader that can be used to load data
        IDataLoader dataLoader = new JsonLoader("json/users.json","json/conversations.json");
        //Creates an instance of mainmodel that uses data loaded in by the jsonLoader
        MainModel mainModel =  new MainModel(dataLoader.loadUsers(),dataLoader.loadConversations());

        //Creates an instance of datasaver which can be used to save data
        ModelObserver dataSaver = new JsonSaver(mainModel, "json/users.json","json/conversations.json");
        //tries to log in as user with username admin and password 123
        IMainView mainView = new MainView(mainModel);

        //adds datasaver to mainmodels observers
        mainModel.addObserver(dataSaver);
        //adds mainView to mainmodels observers
        mainModel.addObserver((MainView)mainView);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        fxmlLoader.setController(mainView);

        try {
            fxmlLoader.setRoot(mainView);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1280, 720);

            stage.setTitle("Shat app");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.show();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }



    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}