package infrastructure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.Conversation;
import model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader {
    /**
     * @param userId the id of the user to be loaded
     * @return the user loaded from users.json
     */
    @Override
    public User loadUser(int userId) {
        List<User> users = loadUsers();
        if(users.equals(null)){
            return null;
        }
        for(User u: users){
            if (u.getId() == userId){
                return u;
            }
        }
        return null;
    }

    /**
     * @param username the username of the user to be loaded
     * @return the user loaded from users.json
     */
    @Override
    public User loadUser(String username) {
        List<User> users = loadUsers();
        if(users == null){
            return null;
        }
        for(User u: users){
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    /**
     * Loads all Users.
     * @return List of User or null
     */
    private List<User> loadUsers(){
        List<User> users = new ArrayList<User>();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>() {}.getType();

        if (fileExists("src/main/java/infrastructure/users.json")){
            try (JsonReader reader = new JsonReader(new FileReader("src/main/java/infrastructure/users.json"))){
                return gson.fromJson(reader, listType);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * Cannot run if there is no conversations.json
     * @return conversations
     */
    @Override
    public List<Conversation> loadConversations(){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Conversation>>() {}.getType();
        List<Conversation> conversations = new ArrayList<Conversation>();
        if (fileExists("src/main/java/infrastructure/conversations.json")){
            try (JsonReader reader = new JsonReader(new FileReader("src/main/java/infrastructure/conversations.json"))){
                return gson.fromJson(reader, listType);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;

    }
}
