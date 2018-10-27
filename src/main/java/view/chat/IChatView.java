package view.chat;

public interface IChatView {
    String getInputText();
    String getChatNameText();
    void createNewLine();
    void clearInputField();
    void editChatName();
    void cancelEditChatName();
    void finishEditChatName();
    void setDefaultConversation();
    void closeParticipantsView();
}
