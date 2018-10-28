package view;

import model.Conversation;
import model.User;

import java.util.Iterator;

public interface IMainView {
    void displayChat();
    void displayUserPage();
    void displayMainView();
    void displayLoginPage();
    void displayCreateConvoPage();
    void displayContactDetailView(User user);
    void displayCurrentUser();
    void displayCreateUserView();
    void displayAddContactView();
    void createConvoViewToBack();
    void updateConversationsList();
    void updateConversationsList(Iterator<Conversation> conversationsToShow);
    String getContactSearchString();
    void updateContactsList();
    void updateContactLists(Iterator<User> contactsToShow);
    String getConversationSearchString();
    void setActiveConversation(int id);
    void logout();
}