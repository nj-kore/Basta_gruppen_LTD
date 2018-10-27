package controller.participants;

/**
 * @author Benjamin Vinnerholt
 * @author Jonathan Köre
 */

import model.MainModel;
import model.User;
import view.chat.IParticipantView;

import java.util.Iterator;

public class RemoveParticipantsController extends AbstractParticipantsController {

    public RemoveParticipantsController(IParticipantView participantView, MainModel mainModel) {
        super(participantView, mainModel);
    }

    @Override
    public void search() {
        Iterator<User> usersToShow = mainModel.searchParticipants(participantsView.getSearchString(), participantsView.getConversation());
        participantsView.showSearch(usersToShow);
    }

    @Override
    public void changeParticipants() {
        mainModel.removeParticipants(participantsView.getParticipantsToAddOrRemove(), participantsView.getConversation());
        participantsView.closeView();
    }
}
