/**
 * @author Filip Andréasson
 * @author Gustav Häger
 * @author Jonathan Köre
 * @author Gustaf Spjut
 * @author Benjamin Vinnerholt
 * @version 0.5
 * @since 2018-09-09
 * The MainModel is responsible for the logic and state of the application. It creates and handles the data
 */
package model;


import model.observerpattern.ModelObservable;

import java.time.LocalDateTime;
import java.util.*;


/**
 * The façade for the model package.
 */
public class MainModel extends ModelObservable {
    private User activeUser;
    private Conversation activeConversation;

    public enum UpdateTypes {
        ACTIVE_CONVERSATION, CONTACTS, CONVERSATIONS, INIT, USER_INFO

    }

    private Map<Integer, Conversation> conversations;
    private Map<Integer, User> users;

    public MainModel(Map<Integer, User> users, Map<Integer, Conversation> conversations) {
        this.users = users;
        this.conversations = conversations;
        activeConversation = new Conversation(-1, "", null);
    }

    /**
     * @param text Sends a text to the active conversation from the active user
     *             <p>
     *             Tells the view to update itself
     */
    public void sendMessage(String text) {
        if (text.length() > 0) {
            //The new ID is going to be one more than the current highest message id in the conversation
            int newMessageId = 0;

            if (!activeConversation.getMessages().keySet().isEmpty()) {
                newMessageId = Collections.max(activeConversation.getMessages().keySet()) + 1;
            }

            Message m = new Message(newMessageId, activeUser.getId(), text, LocalDateTime.now());
            activeConversation.addMessage(m);
            notifyObservers(UpdateTypes.ACTIVE_CONVERSATION);
        }

    }

    public void setUserInfo(String firstName, String lastName, String email, String picURL) {
        activeUser.setFirstName(firstName);
        activeUser.setLastName(lastName);
        activeUser.setEmail(email);
        activeUser.setProfileImagePath(picURL);
        notifyObservers(UpdateTypes.USER_INFO);
    }

    public void changePassword(String newPassword){
        activeUser.setPassword(newPassword);
        notifyObservers(UpdateTypes.USER_INFO);
    }

    Conversation loadConversation(int conversationId) {
        return conversations.get(conversationId);
    }

    public Iterator<Message> loadMessagesInConversation() {
        return activeConversation.getMessages().values().iterator();
    }

    void addContact(int userId) {
        activeUser.addContact(userId);
    }

    public Iterator<User> getContacts() {
        List<User> list = new ArrayList<>();
        for (int id : activeUser.getContacts()) {
            list.add(getUser(id));
        }
        return list.iterator();
    }


    public User getUser(int userId) {
        return users.get(userId);
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    /**
     * Set the active user using its id
     * @param id
     */
    public void setActiveUser(int id) {
        activeUser = users.get(id);
    }

    public void setActiveConversation(int conversationId) {
        this.activeConversation = conversations.get(conversationId);
        notifyObservers(UpdateTypes.ACTIVE_CONVERSATION);
    }

    public void createConversation(List<User> users, String name) {
        int newConversationId = 0;
        if (!conversations.keySet().isEmpty()) {
            newConversationId = Collections.max(conversations.keySet()) + 1;
        }

        Conversation conversation = new Conversation(newConversationId, name, users);
        conversations.put(conversation.getId(), conversation);
        setActiveConversation(conversation.getId());
        notifyObservers(UpdateTypes.ACTIVE_CONVERSATION);
    }

    //Exists for testing purposes
    public void addConversation(Conversation c) {
        conversations.put(c.getId(), c);
    }

    public void setConversationName(String name) {
            if (validateConversationName(name)){
                activeConversation.setName(name);
            }
            else{
                activeConversation.setName(""); //This forces the placeholder to be enforced when loading the conversation
            }

            notifyObservers(UpdateTypes.ACTIVE_CONVERSATION);
            notifyObservers(UpdateTypes.CONVERSATIONS);


    }
    private final static int MAX_LENGTH = 30;

    private final static int MIN_LENGTH = 0;

    private boolean validateConversationName(String name) {
        return name.length() > MIN_LENGTH && name.length() < MAX_LENGTH;
    }
    /**
     * Constructs a placeholder name to a conversation, consisting of the names of participants in the conversation
     */
    public String generatePlaceholderName(Conversation c) {
        StringBuilder placeholderName = new StringBuilder();
        Stack<User> userStack = new Stack<>();
        // TODO: 14/10/2018
        //Remove this when the app starts with a valid conversation
        if (c.getParticipants() == null){
            return "Placeholder";
        }

        userStack.addAll(c.getParticipants());
        //If it is 1, then it is the active user that is the participant, in which case we
        //might aswell display the current users name, instead of a blank one.
        if (userStack.size() != 1) {
            userStack.remove(activeUser);
        }

        placeholderName.append(userStack.pop().getFirstName());

        while (!userStack.isEmpty()) {
            User u = userStack.pop();
            if (placeholderName.length() + u.getFirstName().length() < MAX_LENGTH) {
                placeholderName.append(", ");
                placeholderName.append(u.getFirstName());
            } else {
                placeholderName.append(" + " + userStack.size() + " more.");
                break;
            }
        }
        return placeholderName.toString();
    }

    /**
     *
     * @return all conversations currently held by the model
     */
    public Map<Integer, Conversation> getConversations() {
        return conversations;
    }

    /**
     *
     * @return all conversations in which the user is a participant
     */
    public Iterator<Conversation> getUsersConversations() {
        List<Conversation> userConversations = new ArrayList<>();
        for(Conversation c : conversations.values()) {
            if(c.getParticipants().contains(activeUser)) {
                userConversations.add(c);
            }
        }
        return userConversations.iterator();
    }


    public void setStatus(StatusType s) {
        activeUser.setStatus(s);
        notifyObservers(UpdateTypes.USER_INFO);
    }

    public Map<Integer, User> getUsers() {
        return users;
    }


    public void createUser(String u, String pw, String fn, String ln, Boolean a){
        int id = getNewUserId();
        User user = new User(id, u, pw, fn, ln, StatusType.Available, a);
        notifyObservers(UpdateTypes.CONTACTS);
        users.put(user.getId(), user);
        if (getActiveUser()==null){
            setActiveUser(user.getId());
        }
        if(activeUser.getId()!=id){
            getActiveUser().addContact(id);
        }

        notifyObservers(UpdateTypes.CONTACTS);
    }

    public User getActiveUser() {
        return activeUser;
    }

    public Conversation getActiveConversation() {
        return activeConversation;
    }


    /**
     * @param username
     * @param password
     * @return if the login was successful or not
     * <p>
     * Checks if a User was found with the corresponding username and password
     */
    public boolean login(String username, String password) {

        for (User u : users.values()) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                    setActiveUser(u);
                    notifyObservers(UpdateTypes.INIT);
                    return true;
                }
            }
        }
        return false;
    }

    public Iterator<User> searchContacts(String input) {
        Iterator<User> iterator = getContacts();
        ArrayList<User> contactsToShow = new ArrayList<>();
        User next;

        while (iterator.hasNext()) {
            next = iterator.next();
            if (next.getFullName().toLowerCase(new Locale("sv-SE")).contains(input.toLowerCase(new Locale("sv-SE")))) {
                contactsToShow.add(next);
            }
        }
        return contactsToShow.iterator();
    }

    public Iterator<Conversation> searchConversations(String conversationSearchString) {
        Iterator<Conversation> iterator = getConversations().values().iterator();
        ArrayList<Conversation> conversationsToShow = new ArrayList<>();
        Conversation next;
        boolean conversationFound;

        while (iterator.hasNext()) {
            conversationFound = false;
            next = iterator.next();
            for (User u : next.getParticipants()) {
                if (u.getFullName().toLowerCase().contains(conversationSearchString.toLowerCase())) {
                    conversationsToShow.add(next);
                    conversationFound = true;
                    break;
                }
            }

            if (!conversationFound) {
                if (next.getName().toLowerCase(new Locale("sv-SE")).contains(conversationSearchString.toLowerCase(new Locale("sv-SE")))) {
                    conversationsToShow.add(next);
                }
            }
        }
        return conversationsToShow.iterator();
    }

    /**
     * Will give higher and higher user id as we add more users even if we remove users in between
     * @return a user id that is one higher then the previously highest
     */
    public int getNewUserId(){
        int highest =0;
        if (users.isEmpty()){
            return 0;
        }
        for (User u : users.values()) {
            if (u.getId()>highest) {
                highest=u.getId();
            }
        }
        return highest + 1;
    }

    public Iterator<User> getNonParticipants(Conversation conversation) {
        ArrayList<User> usersNotInConversation = new ArrayList<>();
        Iterator<User> contacts = getContacts();
        User contact;

        while(contacts.hasNext()){
            contact = contacts.next();
            if(!conversation.getParticipants().contains(contact)){
                usersNotInConversation.add(contact);
            }
        }
        return usersNotInConversation.iterator();
    }

    /**
     * @return Participants of a conversation WITHOUT current activeUser.
     */
    public Iterator<User> getParticipants(Conversation conversation) {
        List<User> participants = new ArrayList<>((conversation.getParticipants()));    
        participants.remove(getActiveUser());
        return participants.iterator();
    }


    public Iterator<User> searchNonParticipants(String searchInput, Conversation conversation){
        Iterator<User> nonParticipants = getNonParticipants(conversation);
        ArrayList<User> matchingUsers = new ArrayList<>();
        User nonParticipant;

        while(nonParticipants.hasNext()){
            nonParticipant = nonParticipants.next();
            if(nonParticipant.getFullName().contains(searchInput)){
                matchingUsers.add(nonParticipant);
            }
        }
        return matchingUsers.iterator();
    }

    public void addParticipants(Iterator<User> participantsToAdd, Conversation conversation) {
        while (participantsToAdd.hasNext()){
            conversation.addParticipant(participantsToAdd.next());
        }
        setActiveConversation(conversation.getId());
    }

    public void removeParticipants(Iterator<User> participantsToRemove, Conversation conversation) {
        while (participantsToRemove.hasNext()){
            conversation.removeParticipant(participantsToRemove.next());
        }
        setActiveConversation(conversation.getId());
    }

}
