package view;


import model.Conversation;
import model.User;

import java.util.Iterator;

public interface IParticipantView {
    void showSearch(Iterator<User> usersToShow);

    String getSearchString();

    void addOrRemoveFromConversation();
    void closeView();
    void update();

    void select(ParticipantItem participantItem);

    Iterator<User> getParticipantsToAddOrRemove();

    Conversation getConversation();
}
