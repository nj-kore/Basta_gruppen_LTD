package view;

import model.User;

import java.util.Iterator;

public interface IMainView {
    void displayChat();
    void displayUserPage();
    void displayMainView();
    void setDefaultConversation();
    void displayLoginPage();
    void displayCreateConvoPage();
    void displayCurrentUser();
    void displayCreateUserView();
    void createConvoViewToBack();
    void updateConversationsList();
    void backToChat();
    String getContactSearchString();
    void updateContactList(Iterator<User> iterator);
    String getConversationSearchString();
    void setActiveConversation(int id);

    void loadDetailView(User user);
    void logout();
}