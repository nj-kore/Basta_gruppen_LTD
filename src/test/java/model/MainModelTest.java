package model;

import model.data.Conversation;
import model.data.Message;
import model.data.User;
import org.junit.Test;
import view.MessageItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class MainModelTest {
    @Test
    public void sendMessage() {

        User user1 = new User(1, "hej", "123", "bengt", "testsson");
        User user2 = new User(2, "hej", "123", "bengt2", "testsson");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Conversation c = new Conversation(1, users);


        IMainModel model = new MainModel();
        ((MainModel) model).addConversation(c);
        ((MainModel) model).setActiveUser(user1);
        ((MainModel) model).setActiveConversation(1);

        model.sendMessage("hejsan");
    }

    @Test
    public void readMessages() {
        User user1 = new User(1, "hej", "123", "bengt", "testsson");
        User user2 = new User(2, "hej", "123", "bengt2", "testsson");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Conversation c = new Conversation(1, users);

        IMainModel model = new MainModel();
        ((MainModel) model).addConversation(c);
        ((MainModel) model).setActiveUser(user1);
        ((MainModel) model).setActiveConversation(1);

        int oldSize = model.loadConversation(1).getMessages().size();

        model.sendMessage("hejsan");

        Conversation c1 = model.loadConversation(1);
        assertEquals(c1.getMessages().size() - oldSize, 1);
        assertEquals(c1.getMessages().get(c1.getMessages().size() - 1).getText(), "hejsan");
    }

    //The test should try to load conversation number 2 from the dataHandlerDummy, which only returns null
    @Test
    public void loadConversation() {
        ArrayList<User> users = new ArrayList<>();
        Conversation c = new Conversation(1, users);

        IMainModel model = new MainModel();
        ((MainModel) model).addConversation(c);

        assertNotNull(model.loadConversation(1));
        assertNull(model.loadConversation(2));
    }

    //This test asserts that getContacts returns the users contacts
    @Test
    public void getContacts() {

        IMainModel model = new MainModel();
        User activeUser = new User(1, "admin", "123", "eva", "olsson");
        User contactUser = new User(2, "contact", "222", "olle", "innebandysson");
        ((MainModel) model).createUser(activeUser);
        ((MainModel) model).createUser(contactUser);
        ((MainModel) model).setActiveUser(activeUser);
        assertFalse(model.getContacts().hasNext());
        ((MainModel) model).addContact(contactUser.getId());
        assertNotNull(model.getContacts().next());

    }
    @Test
    public void loadMessageInConversation(){
        User user1 = new User(1, "hej", "123", "bengt", "testsson");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        Conversation c = new Conversation(1, users);
        IMainModel model = new MainModel();
        ((MainModel) model).addConversation(c);
        ((MainModel) model).setActiveConversation(1);
        ((MainModel) model).setActiveUser(user1);
        String testmessage = "If this works I'll have a beer";
        model.sendMessage(testmessage);
        Iterator<Message> itr = model.loadMessagesInConversation();
        String result;
        result=itr.next().getText();
        assertEquals(testmessage, result);
        testmessage="blip";
        assertNotEquals(testmessage, result);
    }
}