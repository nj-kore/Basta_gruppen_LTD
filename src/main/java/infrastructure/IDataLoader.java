package infrastructure;

import model.Conversation;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDataLoader {
    Map<Integer, User> loadUsers();
    Map<Integer, Conversation> loadConversations();
}
