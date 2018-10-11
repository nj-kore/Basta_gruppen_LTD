package model;

import org.junit.Test;
import sun.applet.Main;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;

public class MainModelTest {
    @Test
    public void sendMessage() {

        User user1 = new User(1, "ett", "123", "bengt", "testsson");
        User user2 = new User(2, "två", "123", "bengt2", "testsson2");
        HashMap<Integer, User> userMap = new HashMap<>();
        userMap.put(1, user1);
        userMap.put(2, user2);
        ArrayList<User> userList = new ArrayList<>();
        userList.addAll(userMap.values());
        Conversation c = new Conversation(1, "", userList);
        HashMap<Integer, Conversation> conversationMap = new HashMap<>();


        MainModel model = new MainModel(userMap, conversationMap);
        model.addConversation(c);
        model.setActiveUser(user1);
        model.setActiveConversation(1);

        model.sendMessage("hejsan");
    }

    @Test
    public void readMessages() {
        User user1 = new User(1, "ett", "123", "bengt", "testsson");
        User user2 = new User(2, "två", "123", "bengt2", "testsson2");
        HashMap<Integer, User> userMap = new HashMap<>();
        userMap.put(1, user1);
        userMap.put(2, user2);
        ArrayList<User> userList = new ArrayList<>();
        userList.addAll(userMap.values());
        Conversation c = new Conversation(1, "", userList);
        HashMap<Integer, Conversation> conversationMap = new HashMap<>();


        MainModel model = new MainModel(userMap, conversationMap);
        model.addConversation(c);
        model.setActiveUser(user1);
        model.setActiveConversation(1);

        int oldSize = model.loadConversation(1).getMessages().size();

        model.sendMessage("hejsan");

        Conversation c1 = model.loadConversation(1);
        assertEquals(c1.getMessages().size() - oldSize, 1);
        assertEquals(c1.getMessages().get(c1.getMessages().size() - 1).getText(), "hejsan");
        assertTrue(model.login("ett","123"));
        assertFalse(model.login("Hackerman","gEt_HacKeD"));
    }

    //The test should try to load conversation number 2 from the dataHandlerDummy, which only returns null
    @Test
    public void loadConversation() {
        ArrayList<User> users = new ArrayList<>();
        Conversation c = new Conversation(1, "", users);

        HashMap<Integer, User> userMap = new HashMap<>();
        HashMap<Integer, Conversation> conversationMap = new HashMap<>();

        conversationMap.put(0,c);

        MainModel model = new MainModel(userMap,conversationMap);
        model.addConversation(c);

        assertNotNull(model.loadConversation(1));
        assertNull(model.loadConversation(76876));
    }

    //This test asserts that getContacts returns the users contacts
    @Test
    public void getContacts() {
        HashMap<Integer, User> userMap = new HashMap<>();
        HashMap<Integer, Conversation> conversationMap = new HashMap<>();

        MainModel model = new MainModel(userMap, conversationMap);

        User activeUser = new User(1, "admin", "123", "eva", "olsson");
        User contactUser = new User(2, "contact", "222", "olle", "innebandysson");

        model.createUser(activeUser);
        model.createUser(contactUser);
        model.setActiveUser(activeUser);
        assertFalse(model.getContacts().hasNext());
        model.addContact(contactUser.getId());
        assertNotNull(model.getContacts().next());

    }
    @Test
    public void loadMessageInConversation(){
        User user1 = new User(1, "hej", "123", "bengt", "testsson");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        Conversation c = new Conversation(1, "", users);

        HashMap<Integer, User> userMap = new HashMap<>();
        HashMap<Integer, Conversation> conversationMap = new HashMap<>();

        MainModel model = new MainModel(userMap, conversationMap);
        model.addConversation(c);
        model.setActiveConversation(1);
        model.setActiveUser(user1);
        String testmessage = "If this works I'll have a beer";
        model.sendMessage(testmessage);
        Iterator<Message> itr = model.loadMessagesInConversation();
        String result;
        result=itr.next().getText();
        assertEquals(testmessage, result);
        testmessage="blip";
        assertNotEquals(testmessage, result);
    }

    @Test
    public void createConversation() {
        HashMap<Integer, User> userMap = new HashMap<>();
        HashMap<Integer, Conversation> conversationMap = new HashMap<>();
        ArrayList<User> userList = new ArrayList<>();

        MainModel model = new MainModel(userMap, conversationMap);

        assertTrue(model.getConversations().isEmpty());
        model.createConversation(userList, "testNamn");
        assertFalse(model.getConversations().isEmpty());
    }
}