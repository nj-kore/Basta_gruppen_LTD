package model;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MainModelTest {
    @Test
    public void sendMessage() {

        User user1 = new User(1, "ett", "123", "bengt", "testsson", StatusType.Available, true);
        User user2 = new User(2, "två", "123", "bengt2", "testsson2", StatusType.Available, true);
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
        User user1 = new User(1, "ett", "123", "bengt", "testsson", StatusType.Available, true);
        User user2 = new User(2, "två", "123", "bengt2", "testsson2", StatusType.Available, true);
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

        //User activeUser = new User(1, "admin", "123", "eva", "olsson", StatusType.Available, true);
        //User contactUser = new User(2, "contact", "222", "olle", "innebandysson", StatusType.Available, true);

        model.createUser("admin", "123", "eva", "olsson", true);

        //model.setActiveUser(activeUser);
        assertFalse(model.getContacts().hasNext());
        model.createUser("contact", "222", "olle", "innebandysson", false);
        assertNotNull(model.getContacts().next());

    }
    @Test
    public void loadMessageInConversation(){
        User user1 = new User(1, "hej", "123", "bengt", "testsson", StatusType.Available, true);
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

    @Test
    public void getUsersConversations() {
        MainModel model = new MainModel(getFillerUsers(), new HashMap<>());
        List<User> participants = new ArrayList<>();
        participants.add(model.getUser(1));
        participants.add(model.getUser(2));
        participants.add(model.getUser(3));
        model.createConversation(participants, "chat1");

        List<User> participants2 = new ArrayList<>();
        participants2.add(model.getUser(1));
        participants2.add(model.getUser(4));
        participants2.add(model.getUser(5));
        model.createConversation(participants2, "chat2");

        List<User> participants3 = new ArrayList<>();
        participants3.add(model.getUser(1));
        participants3.add(model.getUser(2));
        participants3.add(model.getUser(3));
        model.createConversation(participants3, "chat3");

        model.setActiveUser(1);
        Iterator<Conversation> itr = model.getUsersConversations();

        StringBuilder s = new StringBuilder();
        while(itr.hasNext()) {
            s.append(itr.next().getName());
        }
        assertEquals("chat1chat2chat3", s.toString());

        model.setActiveUser(2);
        itr = model.getUsersConversations();
        StringBuilder s2 = new StringBuilder();
        while(itr.hasNext()) {
            s2.append(itr.next().getName());
        }
        assertEquals("chat1chat3", s2.toString());


        assertFalse(itr.hasNext());





    }

    @Test
    public void generatePlaceholderName() {

        HashMap<Integer, User> userMap = new HashMap<>();
        HashMap<Integer, Conversation> conversationMap = new HashMap<>();

        MainModel model = new MainModel(userMap, conversationMap);
        ArrayList<User> participants = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            model.createUser( "" + i, "123", "person" + i, "", false);
            participants.add(model.getUser(i));
        }
        model.createConversation(participants, "");
        //User admin = new User(500, "", "", "admin", "", StatusType.Busy, false);
        model.createUser("", "", "admin", "", false);
        model.setActiveUser(20);
        participants.add(model.getUser(20));

        //only 19 people, because it does not include yourself
        assertEquals(model.generatePlaceholderName(model.getActiveConversation()), "person19, person18, person17 + 16 more.");


        participants.clear();
        for(int i = 21; i < 23; i++) {
            //User u = new User(i, "" + i, "123", "person" + i, "", StatusType.Available, false);
            model.createUser("" + i, "123", "person" + i, "",false);
            participants.add(model.getUser(i));
        }
        model.createConversation(participants, "");
        assertEquals(model.generatePlaceholderName(model.getActiveConversation()), "person22, person21");
        participants.clear();

        participants.add(model.getUser(20));
        model.createConversation(participants, "");

        assertEquals(model.generatePlaceholderName(model.getActiveConversation()), model.getUser(20).getFirstName());
    }

    @Test
    public void setConversationName() {
        MainModel model = new MainModel(new HashMap<Integer, User>(), new HashMap<Integer, Conversation>());

        model.addConversation(new Conversation(0, "test", null));
        model.setActiveConversation(0);

        //30 characters
        model.setConversationName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        assertEquals(model.getActiveConversation().getName(), "");

        //29 characters
        String name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        model.setConversationName(name);

        assertEquals(model.getActiveConversation().getName(), name);

    }

    private Map<Integer, User> getFillerUsers() {

        Map<Integer, User> userMap = new HashMap();
        userMap.put(1, new User(1, "admin", "123", "Eva", "Dickinssonm", StatusType.Available, true));
        userMap.put(2, new User(2, "Big beast 12", "aj58dhjj", "Kalle", "Johnson", StatusType.Available, true));
        userMap.put(3, new User(3, "Mr cool", "kh9845jnd", "Johan", "Petterson", StatusType.Available, true));
        userMap.put(4, new User(4, "Dinkerwoltz", "kfg984jhgf", "Mustafa", "Köre", StatusType.Available, true));
        userMap.put(5, new User(5, "TheTaboToast", "jkg84jf", "Karin", "Lidman", StatusType.Available, true));
        userMap.put(6, new User(6, "Heyman12", "jf672jfnm", "Carline", "Mandala", StatusType.Available, true));
        userMap.put(7, new User(7, "Jamiecoo00l", "mbkmGGF", "Bango", "Rickson", StatusType.Available, true));
        userMap.put(8, new User(8, "Diddelydoo", "lhjie34", "Olof", "Klickson", StatusType.Available, true));

        return userMap;
    }
}