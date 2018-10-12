/**
 * @author          Gustav Hager
 */
package infrastructure;

import model.Conversation;
import model.Message;
import model.User;

public interface IDataSaver {
    void saveMessage(int conversationId, Message m);
    void saveUser(User u);
    void saveConversation(Conversation c);
}
