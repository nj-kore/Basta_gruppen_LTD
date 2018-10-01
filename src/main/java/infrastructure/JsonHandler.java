/***
 * @author          Gustav Hager
 * responsibility:  To load and save data from "database" (json files).
 * used by:         MainModel
 * used for:        Loading data that has been stored in the "database" (json files).
 */

package infrastructure;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.data.Conversation;
import model.data.Message;
import model.data.User;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JsonHandler implements  IDataHandler {

    @Override
    public void saveMessage(int conversationId, Message m) {
        try (Writer writer = new FileWriter("c" + conversationId +".json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(m, writer);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes the inputted list of User to users.json.
     * Will overwrite any existing users; use filExists before calling.
     * @param users
     */
    private void writeUsers(List<User> users){
        try (Writer writer = new FileWriter("src/main/java/infrastructure/users.json")) {
            Gson gson = new GsonBuilder().serializeNulls().create();
            gson.toJson(users, writer);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User u) {
        List<User> users = new ArrayList<User>();
        if (!fileExists("src/main/java/infrastructure/users.json")){
            users.add(u);
            writeUsers(users);
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>() {}.getType();
        boolean add = false;

        users = loadUsers();
        if (users != null){
            for(User user : users){
                if(user.getId()==u.getId()){
                    add = false;
                    break;
                }else{
                    add = true;
                }
            }
        }
        if(add){
            users.add(u);
            writeUsers(users);
        }
    }

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

    @Override
    public User loadUser(String username) {
        List<User> users = loadUsers();
        if(users.equals(null)){
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
                return users = gson.fromJson(reader, listType);

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
    private List<Conversation> loadConversations(){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Conversation>>() {}.getType();
        List<Conversation> conversations = new ArrayList<Conversation>();

        try (JsonReader reader = new JsonReader(new FileReader("src/main/java/infrastructure/conversations.json"))){
            conversations = gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conversations;
    }

    private void writeConversation(List<Conversation> conversations){
        Gson gson = new Gson();
        try (Writer writer = new FileWriter("src/main/java/infrastructure/conversations.json")) {
            gson = new GsonBuilder().serializeNulls().create();
            gson.toJson(conversations, writer);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveConversation(Conversation conversationToSave) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Conversation>>() {}.getType();
        List<Conversation> conversations = new ArrayList<Conversation>();
        boolean add = true;
        //if conversations.json exists: add conversation to the list of conversations
        if(fileExists("src/main/java/infrastructure/conversations.json")) {
            conversations = loadConversations();
            for(Conversation conversation : conversations){
                if(conversation.getId()==conversationToSave.getId()){
                    add = false;
                }
            }
        }

        if(add){
            conversations.add(conversationToSave);
        }

        writeConversation(conversations);

    }

    @Override
    public void updateUser(User u) {

    }

    @Override
    public void updateConversation(Conversation c) {

    }

    @Override
    public Conversation loadConversation(int conversationId) {
        return null;
    }

    private boolean fileExists(String path){
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }else{
            return false;
        }
    }

}
