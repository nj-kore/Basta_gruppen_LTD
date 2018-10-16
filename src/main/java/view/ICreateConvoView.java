package view;

import model.User;

import java.util.List;

public interface ICreateConvoView {
    void updateCreateConversationLists();
    void onMoveUsersButtonClicked();
    List<User> getSelectedUsers();
    String getSaveNameTextFieldText();
}
