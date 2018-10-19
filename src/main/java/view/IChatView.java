package view;

public interface IChatView {
    void closeRemoveParticipants();

    String getInputText();
    String getChatNameText();
    void createNewLine();
    void clearInputField();
    void editChatName();
    void cancelEditChatName();
    void finishEditChatName();


    void setDefaultConversation();

    void closeAddParticipants();
}
