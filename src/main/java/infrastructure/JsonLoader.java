/**
 * A class that loads data from JSON.
 *
 * @author          Gustav Hager
 * responsibility:  To load data from "database" (json files).
 * used by:         main
 * used for:        Loading data from the "database" (json files).
 */
package infrastructure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.Conversation;
import model.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonLoader implements IDataLoader {

    private String usersPath;
    private String conversationsPath;

    /**
     * A class that deserializes Users and Conversations
     * @param usersPath the path the the JSON-file containing the serialized Users
     * @param conversationsPath the path the the json-file containing the serialized Conversations
     */
    public JsonLoader(String usersPath, String conversationsPath) {
        this.usersPath = usersPath;
        this.conversationsPath = conversationsPath;
    }

    /**
     * Loads all Users from JSON file at specified path
     * @return List of User or null
     */
    @Override
    public Map<Integer, User> loadUsers(){
        Gson gson = new Gson();
        List<User> users;
        Map<Integer,User> usersMap = new HashMap<Integer,User>();
        Type listType = new TypeToken<List<User>>() {}.getType();
        if (fileExists(usersPath)){
            try (JsonReader reader = new JsonReader(new FileReader(usersPath))){
                users = gson.fromJson(reader, listType);
                for(User u: users){
                    usersMap.put(u.getId(),u);
                }
                return usersMap;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new HashMap<Integer,User>();
    }

    /**
     * Loads all Conversations from JSON file at specified path
     * @return conversations
     */
    @Override
    public Map<Integer, Conversation> loadConversations(){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Conversation>>() {}.getType();
        Map<Integer, Conversation> conversationsMap = new HashMap<Integer, Conversation>();
        List<Conversation> conversations = new ArrayList<Conversation>();
        if (fileExists(conversationsPath)){
            try (JsonReader reader = new JsonReader(new FileReader(conversationsPath))){
                conversations = gson.fromJson(reader, listType);
                for(Conversation c: conversations){
                    conversationsMap.put(c.getId(),c);
                }
                return conversationsMap;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new HashMap<Integer,Conversation>();
    }

    /**
     * Checks to see if a file exists at the inputted path
     * @param path the path to the file in question
     * @return boolean
     */
    private boolean fileExists(String path){
        File f = new File(path);
        return f.exists() && !f.isDirectory();
    }
}
