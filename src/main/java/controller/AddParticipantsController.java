package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import model.User;
import view.AddParticipantsView;

import java.util.Iterator;

public class AddParticipantsController implements IAddParticipantsController {
    private AddParticipantsView addParticipantsView;
    private MainModel mainModel;

    public AddParticipantsController(AddParticipantsView addParticipantsView, MainModel mainModel) {
        this.addParticipantsView = addParticipantsView;
        this.mainModel = mainModel;
    }


    @Override
    public void searchNonParticipants() {
        Iterator<User> usersToShow = mainModel.searchNonParticipants(addParticipantsView.getSearchString(), addParticipantsView.getConversation());
        addParticipantsView.showSearch(usersToShow);
    }


    @Override
    public void isEnterPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            searchNonParticipants();
            event.consume();
        }
    }

    @Override
    public void addParticipants() {
        mainModel.addParticipants(addParticipantsView.getParticipantsToAddOrRemove(), addParticipantsView.getConversation());
        addParticipantsView.closeView();
    }

}
