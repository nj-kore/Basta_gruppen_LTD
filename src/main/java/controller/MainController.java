package controller;

import model.IMainModel;
import model.MainModel;

public class MainController implements IMainController {

    IMainModel mainModel;

    public MainController(IMainModel mainModel){
        this.mainModel = mainModel;
    }
}
