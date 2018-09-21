package controller;

import model.IMainModel;

public class ChatController implements IChatController {

    private IMainController parent;
    private IMainModel mainModel;

    public ChatController(IMainController parent, IMainModel mainModel) {
        this.parent = parent;
    }

    @Override
    public void sendMessage(String text) {

    }
}
