/**
 * A class that saves MainModel data to JSON.
 *
 * @author          Gustav Häger
 * responsibility:  To save data to "database" (json files).
 * used by:         MainModel (as an observer)
 * used for:        Saving data to the "database" (json files).
 */

package infrastructure;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import model.Conversation;
import model.MainModel;
import model.User;
import model.observerpattern.ModelObserver;
import model.observerpattern.UpdateType;

import java.io.*;
import java.util.*;

public class JsonSaver implements IDataSaver, ModelObserver {

    private MainModel model;
    private String usersPath;
    private String conversationsPath;

    /**
     * A class that serializes Users and Conversations
     * @param model the MainModel that holds the information to be saved.
     * @param usersPath the path the the JSON-file in which the serialized Users are to be saved in
     * @param conversationsPath the path the the JSON-file in which the serialized Conversations are to be saved in
     */
    public JsonSaver(MainModel model, String usersPath, String conversationsPath) {
        this.model = model;
        this.usersPath = usersPath;
        this.conversationsPath = conversationsPath;
    }

    /**
     * Writes the inputted list of User to users.json.
     * Will overwrite any existing users; use fileExists before calling.
     * @param users
     */
    private void writeUsers(List<User> users) {
        write(users, usersPath);
    }

    /**
     * Writes the inputted list of User to conversations.json.
     * Will overwrite any existing users; use fileExists before calling.
     * @param conversations the conversations to write to file
     */
    private void writeConversations(List<Conversation> conversations) {
        write(conversations, conversationsPath);
    }

    /**
     * Writes the inputted list to inputted path in Json.
     * Serializes nulls and creates a new file at path if one doesn't already exist.
     * If a file already exists at specified path, it is overwritten.
     * Will overwrite any existing users; use fileExists before calling.
     * @param thingsToWrite List of Objects to serialize and write to file
     * @param pathToWrite path to file that is to be written to
     */
    private void write(List<?> thingsToWrite, String pathToWrite) {
        try (Writer writer = new FileWriter(pathToWrite)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            gson.toJson(thingsToWrite, writer);
            writer.close();
        } catch (IOException e) {
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
    public void update(UpdateType updateType) {
        saveModel();
    }

    /**
     * Saves the model
     */
    private void saveModel() {
        saveUsers();
        saveConversations();
    }

    /**
     * Saves the Users in the model by writing them to usersPath
     */
    public void saveUsers() {
        writeUsers(new ArrayList<>(model.getUsers().values()));
    }

    /**
     * Saves the Conversations in the model by writing them to conversationsPath
     */
    public void saveConversations() {
        writeConversations(new ArrayList<>(model.getConversations().values()));
    }
}