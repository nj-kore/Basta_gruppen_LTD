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

    private void createUserJson(){
        Gson gson = new Gson();
        List<User> users = new ArrayList<User>();
        try (Writer writer = new FileWriter("src/main/java/infrastructure/users.json")) {
            String u = gson.toJson(users);
            gson = new GsonBuilder().serializeNulls().create();
            gson.toJson(users, writer);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User u) {
        //createUserJson();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>() {}.getType();
        List<User> users = new ArrayList<User>();
        boolean add = true;

        try (JsonReader reader = new JsonReader(new FileReader("src/main/java/infrastructure/users.json"))){
            users = gson.fromJson(reader, listType);
            if (users != null){
                for(User user : users){
                    if(user.getId()==u.getId()){
                        add = false;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(add){
            users.add(u);
        }


        try (Writer writer = new FileWriter("src/main/java/infrastructure/users.json")) {
            gson = new GsonBuilder().serializeNulls().create();
            gson.toJson(users, writer);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveConversation(Conversation c) {
        try (Writer writer = new FileWriter("c" + c.getId() +".json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(c, writer);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User u) {

    }

    @Override
    public void updateConversation(Conversation c) {

    }

    @Override
    public Conversation loadConversation(int conversationId) {
        Gson gson = new Gson();
        Conversation conversation = gson.fromJson("c" + conversationId +".json", Conversation.class);
        return conversation;
    }

    /**
     * @param userId the id of the user to be loaded
     * @return the user loaded from users.json
     */
    @Override
    public User loadUser(int userId) {
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        Gson gson = new Gson();
        List<User> users = gson.fromJson("users.json", listType);
        for(User u: users){
            if (u.getId() == userId){
                return u;
            }
        }
        return null;
    }

    @Override
    public User loadUser(String username) {
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        Gson gson = new Gson();
        List<User> users = gson.fromJson("users.json", listType);
        for(User u: users){
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

}
