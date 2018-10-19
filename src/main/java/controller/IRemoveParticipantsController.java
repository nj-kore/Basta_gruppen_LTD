package controller;


import javafx.scene.input.KeyEvent;


public interface IRemoveParticipantsController {
    void searchParticipants();

    void isEnterPressed(KeyEvent keyEvent);

    void removeParticipants();
}
