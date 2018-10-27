package view.chat;

/**
 * @author Benjamin Vinnerholt
 * @since 2018-10-15
 */

import controller.IControllerFactory;
import model.MainModel;

public class AddParticipantsView extends AbstractParticipantView{
    AddParticipantsView(MainModel mainModel, IChatView chatView, IControllerFactory factory) {
        super(mainModel, chatView, factory);
        setTitle("Add Participants");
        setButtonText("Add Participants");
        setNoMatchText("No matching non-participating contacts found");
    }
}
