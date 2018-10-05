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
import model.Conversation;
import model.Message;
import model.User;
import model.MainModel;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class JsonHandler implements  IDataHandler, Observer {

    private MainModel model;
    public JsonHandler(MainModel model) {
        this.model = model;
        loadModel();
    }



    @Override
    public void saveMessage(int conversationId, Message messageToSave) {
        List<Conversation> conversations = new ArrayList<Conversation>();
        if (!fileExists("src/main/java/infrastructure/conversations.json")){
            writeConversations(null);
        }else{
            conversations = loadConversations();
            for (Conversation conversation: conversations){
                if (conversation.getId() == conversationId){
                    conversation.addMessage(messageToSave);
                    writeConversations(conversations);
                }
            }
        }


        /*
        boolean add = false;

        conversations = loadConversations();
        if (conversations != null){
            for(Conversation conversation : conversations){
                if (conversation.getId() == conversationId){
                    for (Message message: conversation.getMessages()){
                        if(message.getId()==messageToSave.getId()){
                            add = false;
                            break;
                        }else{
                            add = true;
                        }
                    }
                    if(add){
                        conversation.addMessage(messageToSave);
                        writeConversations(conversations);
                    }
                }
            }
        }*/
    }


    /**
     * Saves User u to users.json using Gson
     * @param userToSave the User to be saved
     */
    @Override
    public void saveUser(User userToSave) {
        List<User> users = new ArrayList<User>();
        if (!fileExists("src/main/java/infrastructure/users.json")){
            users.add(userToSave);
            writeUsers(users);
        }

        boolean add = false;

        users = loadUsers();
        if (users != null){
            for(User user : users){
                if(user.getId()==userToSave.getId()){
                    add = false;
                    users.set(users.indexOf(user), userToSave);
                    writeUsers(users);
                    break;
                }else{
                    add = true;
                }
            }
        }
        if(add){
            users.add(userToSave);
            writeUsers(users);
        }
    }

    /**
     * Writes the inputted list of User to users.json.
     * Will overwrite any existing users; use fileExists before calling.
     * @param users
     */
    private void writeUsers(List<User> users){
        try (Writer writer = new FileWriter("src/main/java/infrastructure/users.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(users, writer);
            writer.close();
        } catch (IOException e){
            throw new RuntimeException(e);
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

    /**
     * Writes the inputted list of User to conversations.json.
     * Will overwrite any existing users; use fileExists before calling.
     * @param conversations the conversations to write to file
     */
    private void writeConversations(List<Conversation> conversations){
        try (Writer writer = new FileWriter("src/main/java/infrastructure/conversations.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(conversations, writer);
            writer.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveConversation(Conversation conversationToSave) {
        List<Conversation> conversations = new ArrayList<Conversation>();
        if (!fileExists("src/main/java/infrastructure/conversations.json")){
            conversations.add(conversationToSave);
            writeConversations(conversations);
        }

        boolean add = false;

        conversations = loadConversations();
        if (conversations != null){
            for(Conversation conversation : conversations){
                if(conversation.getId()==conversationToSave.getId()){
                    add = false;
                    break;
                }else{
                    add = true;
                }
            }
        }
        if(add){
            conversations.add(conversationToSave);
            writeConversations(conversations);
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
        long t = System.currentTimeMillis();
        List<Conversation> conversations = loadConversations();

        if (conversations!=null) {
            for(Conversation conversation: conversations){
                if (conversation.getId() == conversationId){
                    System.out.println(System.currentTimeMillis() - t);
                    return conversation;
                }
            }
        }
        return null;
    }

    /**
     * Checks to see if a file exists at the inputted path
     * @param path the path to the file in question
     * @return boolean
     */
    private boolean fileExists(String path){
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        saveModel();
    }

    private void saveModel() {
        Iterator<Conversation> conversationIterator = model.getConversations();
        List<Conversation> convoList = new ArrayList<>();
        while(conversationIterator.hasNext()) {
            convoList.add(conversationIterator.next());
        }
        writeConversations(convoList);

        Iterator<User> userIterator = model.getUsers();
        List<User> userList = new ArrayList<>();
        while(userIterator.hasNext()) {
            userList.add(userIterator.next());
        }
        writeUsers(userList);
    }

    private void loadModel() {
        List<User> users = loadUsers();
        List<Conversation> conversations = loadConversations();
        model.initData(users, conversations);
    }
}
