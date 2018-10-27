package view.chat;


import model.Conversation;
import model.User;

import java.util.Iterator;

public interface IParticipantView {
    void showSearch(Iterator<User> usersToShow);
    String getSearchString();
    void closeView();
    void update();
    void moveUsers();
    Iterator<User> getParticipantsToAddOrRemove();
    Conversation getConversation();
    void showSearchResult(Iterator<User> iterator);
}
