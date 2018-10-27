package view.chat;

/**
 * @author Benjamin Vinnerholt
 * @author Jonathan Köre
 * @since 2018-10-15
 */

import controller.IControllerFactory;
import model.MainModel;


public class RemoveParticipantsView extends AbstractParticipantView {


    RemoveParticipantsView(MainModel mainModel, IChatView chatView) {
        super(mainModel, chatView);
        setTitle("Remove Participants");
        setButtonText("Remove Participants");
        setNoMatchText("No matching participating contacts found");
    }

    @Override
    public void update() {
        super.update();
        showSearch(mainModel.getParticipants(conversation));
    }
}
