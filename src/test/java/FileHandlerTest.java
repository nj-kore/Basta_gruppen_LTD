import model.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHandlerTest {

    @Test
    public void pathTest() {
        System.out.println(System.getProperty("user.dir"));
    }
    @Test
    public void read() {
        MainModel model = new MainModel();
        Conversation c = model.loadConversation(5555);
        ArrayList<Message> messages = c.getMessages();

        assertEquals(12, messages.get(0).getSender().getId());
        assertEquals(19, messages.get(1).getSender().getId());
        assertEquals(12, messages.get(2).getSender().getId());

        assertEquals("ABC", messages.get(0).getText());
        assertEquals("DFG", messages.get(1).getText());
        assertEquals("HIJ", messages.get(2).getText());


    }

    @Test
    public void write() throws IOException {
        int conversationId = 2222;
        MainModel model = new MainModel();

        User user1 = new User(1);

        User user2 = new User(2);

        Message message1 = new Message(user1, "Message number one");
        model.sendMessage(conversationId, message1);

        Message message2 = new Message(user2, "Message number two");
        model.sendMessage(conversationId, message2);

        Message message3 = new Message(user1, "Message number three");
        model.sendMessage(conversationId, message3);

        Conversation c = model.loadConversation(conversationId);
        ArrayList<Message> messages = c.getMessages();

        assertEquals(message1.getSender().getId(), messages.get(0).getSender().getId());
        assertEquals(message2.getSender().getId(), messages.get(1).getSender().getId());
        assertEquals(message3.getSender().getId(), messages.get(2).getSender().getId());

        assertEquals(message1.getText(), messages.get(0).getText());
        assertEquals(message2.getText(), messages.get(1).getText());
        assertEquals(message3.getText(), messages.get(2).getText());

    }
}