package controller;


import javafx.scene.input.KeyEvent;
import model.User;

import java.util.Iterator;

public interface IRemoveParticipantsController {
    void searchParticipants();

    void isEnterPressed(KeyEvent keyEvent);

    void removeParticipants();
}
