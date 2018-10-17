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


import java.time.LocalDateTime;
import java.util.*;


/**
 * The façade for the model package.
 */
public class MainModel extends Observable {
    private User activeUser;
    private Conversation activeConversation;

    public enum UpdateTypes {
        ACTIVE_CONVERSATION, CONTACTS, CONVERSATIONS, INIT, USER_INFO
    }

    public enum StatusType {
        Available, Busy, Do_not_disturb;

        @Override
        public String toString() {
            switch (this){
                case Busy:              return "Busy";
                case Do_not_disturb:    return "Do not disturb";
                default:                return "Available";
            }
        }
    }

    private Map<Integer, Conversation> conversations;
    private Map<Integer, User> users = new HashMap<>();
    List<User> newConvoUsers = new ArrayList();


    public MainModel(Map<Integer, User> users, Map<Integer, Conversation> conversations) {
        this.users = users;
        this.conversations = conversations;
        activeConversation = new Conversation(-1, "", null);
    }


    public void initFillers() {
        User admin = new User(1, "admin", "123", "Admin", "Boy", StatusType.Available, Boolean.TRUE);
        User contactUser = new User(2, "contact", "222", "olle", "innebandysson", StatusType.Available, Boolean.FALSE);
        User contactUser2 = new User(3, "contact2", "222", "kalle", "kuling", StatusType.Available, Boolean.FALSE);
        setActiveUser(admin);
        admin.addContact(contactUser.getId());
        admin.addContact(contactUser2.getId());
        createUser(admin);
        createUser(contactUser);
        createUser(contactUser2);
        contactUser.setProfileImagePath("pics/lukasmaly.jpg");
        contactUser.setStatus(StatusType.Busy);
        //users.put(admin.getId(), admin);
        //users.put(contactUser.getId(), contactUser);
        //users.put(contactUser2.getId(), contactUser2);
        update(UpdateTypes.INIT);
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
            update(UpdateTypes.ACTIVE_CONVERSATION);
        }

    }

    public void setUserInfo(String firstName, String lastName, String email) {
        activeUser.setFirstName(firstName);
        activeUser.setLastName(lastName);
        activeUser.setEmail(email);
        update(UpdateTypes.USER_INFO);
    }


    /**
     * @param u the type of "update" that the observers should do
     *          <p>
     *          Notifies the observers with the String update as an argument
     */
    private void update(UpdateTypes u) {
        setChanged();
        notifyObservers(u);
    }

    public Conversation loadConversation(int conversationId) {
        return conversations.get(conversationId);
    }

    public Iterator<Message> loadMessagesInConversation() {
        return activeConversation.getMessages().values().iterator();
    }

    public void addContact(int userId) {
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

    public void setActiveConversation(int conversationId) {
        this.activeConversation = conversations.get(conversationId);
        update(UpdateTypes.ACTIVE_CONVERSATION);
    }

    public void createConversation(List<User> users, String name) {
        int newConversationId = 0;
        if (!conversations.keySet().isEmpty()) {
            newConversationId = Collections.max(conversations.keySet()) + 1;
        }

        Conversation conversation = new Conversation(newConversationId, name, users);
        conversations.put(conversation.getId(), conversation);
        setActiveConversation(conversation.getId());
        update(UpdateTypes.ACTIVE_CONVERSATION);
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

            update(UpdateTypes.ACTIVE_CONVERSATION);
            update(UpdateTypes.CONVERSATIONS);


    }

    private final static int MAX_LENGTH = 30;
    private final static int MIN_LENGTH = 0;

    private boolean validateConversationName(String name) {
        return name.length() > MIN_LENGTH && name.length() < MAX_LENGTH;
    }

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

    public Map<Integer, Conversation> getConversations() {
        return conversations;
    }


    public void setStatus(StatusType s) {
        activeUser.setStatus(s);
        update(UpdateTypes.USER_INFO);
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void createUser(User u) {
        users.put(u.getId(), u);
    }

    public void createUserForController(String u, String pw, String fn, String ln, Boolean a){
        int id = getNewUserId();
        User user = new User(id, u, pw, fn, ln, StatusType.Available, a);
        //Adds as a contact to the active user, might not be usefull later on
        getActiveUser().addContact(id);
        createUser(user);
        update(UpdateTypes.CONTACTS);
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
     * @return if the login was successfull or not
     * <p>
     * Checks if a User was found with the corresponding username and password
     */
    public boolean login(String username, String password) {

        for (User u : users.values()) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                    setActiveUser(u);
                    update(UpdateTypes.INIT);
                    return true;
                }
            }
        }
        return false;
    }

    public Iterator<User> searchContacts(String input) {
        Iterator<User> iterator = getContacts();
        ArrayList<User> contactsToShow = new ArrayList<User>();
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
                if (u.getFullName().contains(conversationSearchString)) {
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
     * @return a user id that is one higher then the previously highest
     * Will give higher and higher user id as we add more users even if we remove users in between
     */
    public int getNewUserId(){
        int highest =0;
        for (User u : users.values()) {
            if (u.getId()>highest) {
            }
                highest=u.getId();
            }
            return highest+1;
    }

}
