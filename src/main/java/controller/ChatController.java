package controller;

import model.IMainModel;
import model.MainModel;

public class ChatController implements IChatController {


    public ChatController() {
    }

    @Override
    public void sendMessage(String text) {
        MainModel.getInstance().sendMessage(text);
    }
}
