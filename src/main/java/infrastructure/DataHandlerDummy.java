package infrastructure;

import javafx.scene.image.Image;
import model.data.Conversation;
import model.data.Message;
import model.data.User;

import java.util.List;

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

    @Override
    public List<Conversation> loadConversations(){
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
        User u = new User(77, username, "123", "Greta", "Garbo");
        //u.setProfileImagePath((getClass().getClassLoader().getResourceAsStream("pics/greta.jpg")));
        return u;
    }
}
