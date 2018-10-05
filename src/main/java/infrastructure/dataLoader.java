package infrastructure;

import model.Conversation;
import model.User;

import java.util.List;

public interface dataLoader {
    List<User> loadUsers();
    List<Conversation> loadConversations();
}
