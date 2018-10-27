package controller.participants;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import model.User;
import view.chat.IParticipantView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Benjamin Vinnerholt
 * @author Jonathan Köre
 *
 */

abstract class AbstractParticipantsController implements IParticipantsController{

    IParticipantView participantsView;
    MainModel mainModel;

    public AbstractParticipantsController(IParticipantView participantsView, MainModel mainModel) {
        this.participantsView = participantsView;
        this.mainModel = mainModel;
    }

    @Override
    public void isEnterPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            search();
            event.consume();
        }
    }
}
