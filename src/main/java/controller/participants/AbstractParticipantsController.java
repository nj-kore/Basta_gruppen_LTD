package controller.participants;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import view.chat.IParticipantView;


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
