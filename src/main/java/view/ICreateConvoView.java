package view;

import model.User;

import java.util.ArrayList;

public interface ICreateConvoView {
    void updateCreateConversationLists();
    void onMoveUsersButtonClicked();
    ArrayList<User> getSelectedUsers();
    void saveNameFailed();
    void setDisableCreateConvoButton(boolean bool);
    String getSaveNameTextFieldText();
}
