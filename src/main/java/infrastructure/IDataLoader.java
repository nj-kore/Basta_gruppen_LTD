package infrastructure;

import model.Conversation;
import model.User;

import java.util.HashMap;
import java.util.List;

public interface IDataLoader {
    HashMap<Integer, User> loadUsers();
    HashMap<Integer, Conversation> loadConversations();
}
