package view;

import model.User;

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
    void loadDetailView(User user);
    void logout();
}