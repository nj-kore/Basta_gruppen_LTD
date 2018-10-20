package view;

import model.Conversation;
import model.User;

import java.util.Iterator;

public interface IAddParticipantsView {
    public void showSearch(Iterator<User> usersToShow);
    public String getSearchString();
    public void addOrRemoveFromConversation();
    public void closeView();
    public void update();
    public void select(ParticipantItem participantItem);
    public Iterator<User> getParticipantsToAddOrRemove();
    public Conversation getConversation();
}
