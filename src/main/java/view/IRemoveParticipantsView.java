package view;

import model.User;

import java.util.Iterator;

public interface IRemoveParticipantsView {
    void showSearchResult(Iterator<User> iterator);
}
