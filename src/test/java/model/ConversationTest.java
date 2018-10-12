package model;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}
