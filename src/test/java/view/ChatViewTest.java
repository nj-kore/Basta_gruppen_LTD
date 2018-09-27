package view;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Conversation;
import model.MainModel;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ChatViewTest {

    //I don't know the right way to test this, since our ChatView tries to load
    ChatView chatViewTest = new ChatView();

    @Test
    public void keyPressed() {
        /*
        ChatView chatView = new ChatView();
        TextArea textArea = chatView.chatTextArea;
        String s = "Message";
        textArea.setText(s);

        KeyEvent ke = new KeyEvent(KeyEvent.KEY_TYPED,
                "", "",
                KeyCode.ENTER, false, false, false, false);

        chatView.chatTextArea.fireEvent(ke);

        assertEquals(textArea.getText(), s+"\n");
        */
    }

    /*@Test
    public void chatNameChanged(){
        String afterTest = "naskjdnASKJN";

        Conversation testConv = new Conversation(15);
        MainModel.getInstance().setActiveConversation(15);
        testConv.setName("beforeTest");
        chatViewTest.getChatNameTextField().setText(afterTest);
        chatNameChanged();
        assertEquals(testConv.getName(), afterTest);

    }*/



}