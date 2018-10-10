/**
 *
 * A class that saves data to JSON.
 *
 * @author          Gustav Hager
 * responsibility:  To save data to "database" (json files).
 * used by:         MainModel
 * used for:        Saving data to the "database" (json files).
 */

package infrastructure;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import model.Conversation;
import model.MainModel;
import model.Message;
import model.User;

import java.io.*;
import java.util.*;

public class JsonSaver implements IDataSaver, Observer {

    private MainModel model;

    public JsonSaver(MainModel model) {
        this.model = model;
        saveModel();
    }



    @Override
    public void saveMessage(int conversationId, Message messageToSave) {
        List<Conversation> conversations = new ArrayList<Conversation>();
        if (!fileExists("src/main/java/infrastructure/conversations.json")){
            writeConversations(null);
        }else{
            Conversation conversation = model.loadConversation(conversationId);
            conversation.addMessage(messageToSave);
            writeConversations(conversations);
        }
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

        Map<Integer, User> userMap = model.getUsers();
        if(!userMap.containsKey(userToSave.getId())){
            userMap.put(userToSave.getId(),userToSave);
            users = new ArrayList<User>(userMap.values());
            writeUsers(users);
        }


        /*
        boolean add = false;


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
        }*/
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

        Map<Integer, Conversation> conversationMap = model.getConversations();
        if(!conversationMap.containsKey(conversationToSave.getId())){
            conversationMap.put(conversationToSave.getId(),conversationToSave);
            conversations = new ArrayList<Conversation>(conversationMap.values());
            writeConversations(conversations);
        }

        /*
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
        }*/

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

    @Override
    public void update(Observable o, Object arg) {
        saveModel();
    }

    private void saveModel() {
        /*
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
        */
        writeConversations(new ArrayList<Conversation>(model.getConversations().values()));
        writeUsers(new ArrayList<User>(model.getUsers().values()));
    }
}