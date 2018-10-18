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
import model.observerpattern.ModelObserver;

import java.io.*;
import java.util.*;

public class JsonSaver implements ModelObserver {

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
     *  This method is called whenever the any ModelObservable object calls the method 'notifyObservers'
     *  because it will need to save the model whenever anything changes in the application
     *
     * @param   updateType     is the type of task the update method will perform
     */

    @Override
    public void update(MainModel.UpdateTypes updateType) {
        saveModel();
    }

    private void saveModel() {
        writeConversations(new ArrayList<Conversation>(model.getConversations().values()));
        writeUsers(new ArrayList<User>(model.getUsers().values()));
    }
}