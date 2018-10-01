package model;

public class DataHandlerDummy implements IDataHandler {
    @Override
    public void saveMessage(int conversationId, Message m) {

    }

    @Override
    public void saveUser(User u) {

    }

    @Override
    public void saveConversation(Conversation c) {

    }


              //TODO this method currently returns null
    @Override
    public Conversation createConversation() {
        return null;
    }


    @Override
    public void updateUser(User u) {

    }

    @Override
    public void updateConversation(Conversation c) {

    }

    @Override
    public Conversation loadConversation(int conversationId) {
        return null;
    }

    @Override
    public User loadUser(int userId) {
        return null;
    }

    @Override
    public User loadUser(String username) {
        return new User(77, username, "123", "Greta");
    }
}
