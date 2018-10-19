package infrastructure;

import model.Conversation;
import model.MainModel;
import model.StatusType;
import model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class JsonSaverTest {
    private MainModel createDummyModel(){
        //Create users
        User user1 = new User(1, "ett", "123", "bengt", "testsson", StatusType.Available, false);
        //Add users to list for writing to JSON
        List<User> users = new ArrayList<User>();
        users.add(user1);
        //Add users to Map for easier checking of keySet later
        HashMap<Integer,User> usersMap = new HashMap<Integer, User>();
        usersMap.put(user1.getId(),user1);
        //Create conversations
        Conversation conversation1 = new Conversation(1,"Convo1", users);
        conversation1.setName("business_convo");
        //Add conversations to Map
        HashMap<Integer,Conversation> conversationsMap = new HashMap<Integer, Conversation>();
        conversationsMap.put(conversation1.getId(),conversation1);
        return new MainModel(usersMap ,conversationsMap);
    }
    @Test
    public void update() throws Exception {
        MainModel mainModel = createDummyModel();
        JsonSaver jsonSaver = new JsonSaver(mainModel, "src/test/java/infrastructure/users.json","src/test/java/infrastructure/conversations.json");
        jsonSaver.update(null);
    }

    @Test
    public void saveUsers() throws Exception {
        MainModel mainModel = createDummyModel();
        JsonSaver jsonSaver = new JsonSaver(mainModel, "src/test/java/infrastructure/users.json","src/test/java/infrastructure/conversations.json");
        jsonSaver.saveUsers();
    }

    @Test
    public void saveConversations() throws Exception {
        MainModel mainModel = createDummyModel();
        JsonSaver jsonSaver = new JsonSaver(mainModel, "src/test/java/infrastructure/users.json","src/test/java/infrastructure/conversations.json");
        jsonSaver.saveConversations();
    }

}