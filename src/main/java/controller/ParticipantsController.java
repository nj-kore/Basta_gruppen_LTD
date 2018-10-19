package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MainModel;
import model.User;
import view.RemoveParticipantsView;

import java.util.ArrayList;
import java.util.List;

public class ParticipantsController implements IParticipantsController{
    RemoveParticipantsView removeParticipantsView;
    MainModel mainModel;

    public ParticipantsController(RemoveParticipantsView removeParticipantsView, MainModel mainModel) {
        this.removeParticipantsView = removeParticipantsView;
        this.mainModel = mainModel;
    }


    @Override
    public void searchParticipants() {
        List<User> users = mainModel.getActiveConversation().getParticipants();
        List<User> usersToShow = new ArrayList<User>();
        String searchString = removeParticipantsView.getSearchString();

        for(User u : users){
            if(u.getFullName().contains(searchString)) usersToShow.add(u);
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
}
