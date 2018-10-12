package view;

public interface IChatView {
    String getInputText();

    String getChatNameText();

    void createNewLine();

    void clearInputField();

    boolean chatNameIsFocused();

    void setChatNameEditable(boolean editable);

    void setChatAreaFocused();
}
