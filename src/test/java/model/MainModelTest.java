package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainModelTest {
    @Test
    public void sendMessage() {
        Conversation c = new Conversation(1);
        User user1 = new User(1,"hej","123");
        User user2 = new User(2,"hej","123");
        c.addParticipant(user1);
        c.addParticipant(user2);

        IMainModel model = MainModel.getInstance();
        ((MainModel) model).addConversation(c);
        ((MainModel) model).setActiveUser(user1);
        ((MainModel) model).setActiveConversation(1);

        model.sendMessage("hejsan");
    }

    @Test
    public void readMessages() {
        Conversation c = new Conversation(1);
        User user1 = new User(1,"hej","123");
        User user2 = new User(2,"hej","123");
        c.addParticipant(user1);
        c.addParticipant(user2);

        IMainModel model = MainModel.getInstance();
        ((MainModel) model).addConversation(c);
        ((MainModel) model).setActiveUser(user1);
        ((MainModel) model).setActiveConversation(1);

        model.sendMessage("hejsan");

        Conversation c1 = model.loadConversation(1);
        assertEquals(c1.getMessages().size(), 1);
        assertEquals(c1.getMessages().get(0).getText(), "hejsan");
    }

    //The test should try to load conversation number 2 from the dataHandlerDummy, which only returns null
    @Test
    public void loadConversation() {
        Conversation c = new Conversation(1);

        IMainModel model = MainModel.getInstance();
        ((MainModel) model).addConversation(c);

        assertNotNull(model.loadConversation(1));
        assertNull(model.loadConversation(2));
    }
}