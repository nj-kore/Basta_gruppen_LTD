import model.FileHandler;
import model.Message;
import model.User;
import org.junit.Test;

import java.io.IOException;

public class FileHandlerTest {

    @Test
    public void read() {

    }

    @Test
    public void write() throws IOException {
        FileHandler fh = new FileHandler();
        String conversationName = "conversation1";

        User user1 = new User();
        user1.setId(1);

        User user2 = new User();
        user2.setId(2);

        Message message1 = new Message(user1, "Whats up?");

        fh.write(conversationName, message1);

        Message message2 = new Message(user2, "Nm, wbu?");

        fh.write(conversationName, message2);

        Message message3 = new Message(user1, "Not so much hehe");

        fh.write(conversationName, message3);




    }
}