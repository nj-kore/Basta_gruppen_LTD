package model;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ConversationTest {
    @Test
    public void addMessage() {
        ArrayList<User> userList = new ArrayList<>();
        Conversation c = new Conversation(1, "", userList);

        assertTrue(c.getMessages().isEmpty());
        Message message = new Message(1, 1,"baby shark do-do-do", LocalDateTime.now());
        c.addMessage(message);
        assertFalse(c.getMessages().isEmpty());
    }

    @Test
    public void equalsTest(){
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<User> userList2 = new ArrayList<>();
        Conversation conversation = new Conversation(1,"foo", userList );
        Conversation conversation2 = new Conversation(1,"foo",userList2 );
        Conversation conversation3 = new Conversation(1,"bar", userList );
        Conversation conversation4 = new Conversation(2,"bar", userList );
        Conversation conversation5 = new Conversation(1,"foo", null );
        Conversation conversation6 = new Conversation(1,null, userList );
        Conversation conversation7 = new Conversation(1,"bar", userList );
        //assertEquals is not used since it is in fact equals that needs to be tested
        assertTrue(conversation.equals(conversation2));
        conversation2.addParticipant(new User(5, null, null, null, null, null, null));
        assertFalse(conversation.equals(conversation2));
        assertFalse(conversation.equals(conversation6));
        assertFalse(conversation.equals(conversation3));
        assertFalse(conversation.equals(conversation4));
        assertFalse(conversation.equals(conversation5));
        assertFalse(conversation.equals(conversation7));

        assertFalse(conversation.equals(null));
        assertFalse(conversation.equals("This is a string"));
    }

    @Test
    public void hashCodeTest(){
        ArrayList<User> userList = new ArrayList<>();
        Conversation conversation = new Conversation(1,"foo",userList );
        Conversation conversation2 = new Conversation(1,"foo",userList );
        Conversation conversation3 = new Conversation(1,"bar",userList );
        assertEquals(conversation.hashCode(), conversation2.hashCode());
        assertNotEquals(conversation.hashCode(), conversation3.hashCode());
    }
}
