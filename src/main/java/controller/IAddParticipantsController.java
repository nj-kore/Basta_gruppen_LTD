package controller;

import javafx.scene.input.KeyEvent;

public interface IAddParticipantsController {
    void searchNonParticipants();

    void isEnterPressed(KeyEvent event);

    void addParticipants();
}
