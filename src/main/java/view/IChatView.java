package view;

public interface IChatView {
    void closeParticipants();

    String getInputText();
    String getChatNameText();
    void createNewLine();
    void clearInputField();
    void editChatName();
    void cancelEditChatName();
    void finishEditChatName();
}
