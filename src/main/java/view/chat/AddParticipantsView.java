package view.chat;

/**
 * @author Benjamin Vinnerholt
 * @author Jonathan Köre
 * @since 2018-10-15
 */

import model.MainModel;

public class AddParticipantsView extends AbstractParticipantView{
    AddParticipantsView(MainModel mainModel, IChatView chatView) {
        super(mainModel, chatView);
        setTitle("Add Participants");
        setButtonText("Add Participants");
        setNoMatchText("No matching non-participating contacts found");
    }

    @Override
    public void update() {
        super.update();
        showSearch(mainModel.getNonParticipants(conversation));
    }
}
