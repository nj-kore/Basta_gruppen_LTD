package view;

/**
 * An interface designed for a view where the user can log into a program.
 * <p>The interface only covers two potential fields, the username and password field, and has methods
 * for fetching data from these fields, as well as clearing them and showing an error message</p>
 */
public interface ILoginView {
    String getUsername();
    String getPassword();
    void showWrongInputNotification();
    void clearTextFields();
}
