/**
 *
 * A class that saves MainModel data to JSON.
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
import model.User;
import java.io.*;
import java.util.*;

public class JsonSaver implements Observer {

    private MainModel model;

    public JsonSaver(MainModel model) {
        this.model = model;
        saveModel();
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
        ArrayList<Conversation> conversationArrayList = new ArrayList<>();

        while (model.getConversations().hasNext()) {
            conversationArrayList.add(model.getConversations().next());
            System.out.println(conversationArrayList);
        }
        writeConversations(new ArrayList<Conversation>(model.getConversations());
        ArrayList<Conversation> arrayList = new ArrayList<>();
        arrayList.addAll(model.getConversations());
        writeUsers(new ArrayList<User>(model.getUsers().values()));
    }
}