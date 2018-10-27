package controller.participants;

/**
 * @author Benjamin Vinnerholt
 */
import model.MainModel;
import model.User;
import view.chat.IParticipantView;

import java.util.Iterator;

public class AddParticipantsController extends AbstractParticipantsController {

    public AddParticipantsController(IParticipantView participantView, MainModel mainModel) {
        super(participantView, mainModel);
    }

    @Override
    public void search() {
        Iterator<User> usersToShow = mainModel.searchNonParticipants(participantsView.getSearchString(), participantsView.getConversation());
        participantsView.showSearch(usersToShow);
    }

    @Override
    public void changeParticipants() {
        mainModel.addParticipants(participantsView.getParticipantsToAddOrRemove(), participantsView.getConversation());
        participantsView.closeView();
    }

}
