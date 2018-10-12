package view;

public interface ILoginView {
    String getUsername();
    String getPassword();
    void showWrongInputNotification();
    void clearTextFields();
}
