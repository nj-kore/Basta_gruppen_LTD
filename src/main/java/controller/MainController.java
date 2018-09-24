package controller;

import model.IMainModel;
import model.MainModel;

public class MainController implements IMainController {

    IMainModel mainModel = MainModel.getInstance();

    public MainController(){
    }
}
