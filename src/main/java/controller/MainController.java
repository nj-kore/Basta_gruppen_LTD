package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import model.IMainModel;
import model.MainModel;

public class MainController implements IMainController {

    IMainModel mainModel = MainModel.getInstance();

    @FXML
    HBox displayBox;

    public MainController(){
    }
}
