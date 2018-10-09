package infrastructure;

import model.Conversation;
import model.User;
import java.util.Map;

public interface IDataLoader {
    Map<Integer, User> loadUsers();
    Map<Integer, Conversation> loadConversations();
}
