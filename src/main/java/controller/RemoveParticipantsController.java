package controller;

/**
 * @author Benjamin Vinnerholt
 */

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import model.User;
import view.IParticipantView;
import view.RemoveParticipantsView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RemoveParticipantsController implements IRemoveParticipantsController {
    private IParticipantView removeParticipantsView;
    private MainModel mainModel;

    public RemoveParticipantsController(IParticipantView removeParticipantsView, MainModel mainModel) {
        this.removeParticipantsView = removeParticipantsView;
        this.mainModel = mainModel;
    }


    @Override
    public void searchParticipants() {
        Iterator<User> users = mainModel.getParticipants(removeParticipantsView.getConversation());
        List<User> usersToShow = new ArrayList<>();
        String searchString = removeParticipantsView.getSearchString();
        User next;

        while (users.hasNext()){
            next = users.next();
            if(next.getFullName().contains(searchString)){
                usersToShow.add(next);
            }
        }
        removeParticipantsView.showSearchResult(usersToShow.iterator());
    }



    @Override
    public void isEnterPressed(KeyEvent event) {
            if(event.getCode().equals(KeyCode.ENTER)){
                searchParticipants();
                event.consume();
            }
        }



    @Override
    public void removeParticipants() {
        mainModel.removeParticipants(removeParticipantsView.getParticipantsToAddOrRemove(), removeParticipantsView.getConversation());
        removeParticipantsView.closeView();
    }
}
