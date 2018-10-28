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
import model.observerpattern.UpdateType;
import java.time.LocalDateTime;
import java.util.*;


/**
 * The façade for the model package.
 */
public class MainModel extends ModelObservable {
    private User activeUser;
    private Conversation activeConversation;

    /**
     * defaultConversation acting as the active conversation when the User is currently not in any conversation
     */
    private Conversation defaultConversation = new Conversation(-1, "", null);



    /**
     * Maps of all the conversations that the model currently knows of. They are stored in a Map to be
     * more easily searchable, since the Integer Key corresponds to the ID of the stored Conversation.
     */
    private Map<Integer, Conversation> conversations;
    /**
     * Map of all Users that the model currently knows of
     */
    private Map<Integer, User> users;

    /**
     * Instantiates the MainModel with pre-loaded users and conversations
     * @param users The set of loaded Users
     * @param conversations The set of loaded Conversations
     */
    public MainModel(Map<Integer, User> users, Map<Integer, Conversation> conversations) {
        this.users = users;
        this.conversations = conversations;
        activeConversation = defaultConversation;
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

            Message messageToSend = new Message(newMessageId, activeUser.getId(), text, LocalDateTime.now());
            activeConversation.addMessage(messageToSend);
            notifyObservers(UpdateType.ACTIVE_CONVERSATION);
        }

    }

    /**
     * Updates the info of the activeUser and notifies observers to update themselves.
     * @param firstName The new first name
     * @param lastName The new last name
     * @param email The new Email address
     * @param picURL The new Local URL to the Profile picture
     */
    public void setUserInfo(String firstName, String lastName, String email, String picURL) {
        activeUser.setFirstName(firstName);
        activeUser.setLastName(lastName);
        activeUser.setEmail(email);
        if(picURL != null){
            activeUser.setProfileImagePath(picURL);
        }
        notifyObservers(UpdateType.USER_INFO);
    }

    /**
     * Updates the current password of the activeUser and notifies the observers to update themselves
     * @param newPassword The new password
     */
    public void changePassword(String newPassword){
        activeUser.setPassword(newPassword);
        notifyObservers(UpdateType.USER_INFO);
    }

    /**
     * Gets the desired conversation from the conversations map.
     * @param conversationId The id of the conversation which is the be returned.
     * @return The found conversation
     */
    Conversation loadConversation(int conversationId) {
        return conversations.get(conversationId);
    }

    /**
     * @return An iterator with the messages from the activeConversation
     */
    public Iterator<Message> loadMessagesInConversation() {
        return activeConversation.getMessages().values().iterator();
    }

    /**
     * Adds a contact to the activeUsers contact list
     * @param userId The id of the user which is to be added
     */
    public void addContact(int userId) {
        activeUser.addContact(userId);
        notifyObservers(UpdateType.CONTACTS);
    }

    /**
     * Removes a contact from the activeUsers contact list
     * @param userId The id of the user which is to be removed
     */
    public void removeContact(int userId) {
        activeUser.removeContact(userId);
        notifyObservers(UpdateType.CONTACTS);
    }

    /**
     *
     * @return An iterator of the activeUsers contacts
     */
    public Iterator<User> getContacts() {
        List<User> list = new ArrayList<>();
        for (int id : activeUser.getContacts()) {
            list.add(getUser(id));
        }
        return list.iterator();
    }

    /**
     * Checks if a user exists in the active users contact list
     * @param user the user that gets checked if it exists
     * @return a boolean representing the result
     */
    public boolean userIsContact(User user) {
        if (activeUser.getContacts().contains(user.getId())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @param userId the Id of the desired user
     * @return The user with the matching userId
     */
    public User getUser(int userId) {
        return users.get(userId);
    }

    /**
     * @param activeUser The User which is to be set as activeUser
     */
    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    /**
     * Set the active user using an id
     * @param id The id of the user which is to be set as activeUser
     */
    public void setActiveUser(int id) {
        activeUser = users.get(id);
    }

    /**
     * Sets the activeConversation using an id and notifies the observers to update themselves
     * @param conversationId The id of the conversation which is the be set as activeConversation
     */
    public void setActiveConversation(int conversationId) {
        this.activeConversation = conversations.get(conversationId);
        notifyObservers(UpdateType.ACTIVE_CONVERSATION);
    }

    private void setActiveConversation(Conversation conversation) {
        activeConversation = conversation;
        notifyObservers(UpdateType.ACTIVE_CONVERSATION);
    }

    public void setDefaultConversation() {
        Iterator<Conversation> itr = getUsersConversations();
        if(itr.hasNext()) {
            setActiveConversation(itr.next().getId());
        } else {
            setActiveConversation(defaultConversation);
        }
    }

    /**
     * Creates a new conversation and sets this as the activeConversation.
     * Also notifies the observers the update themselves.
     * @param users The List of participants to the new conversation
     * @param name The name of the new conversation
     */
    public void createConversation(List<User> users, String name) {
        int newConversationId = 0;
        if (!conversations.keySet().isEmpty()) {
            newConversationId = Collections.max(conversations.keySet()) + 1;
        }

        Conversation conversation = new Conversation(newConversationId, name, users);
        conversations.put(conversation.getId(), conversation);
        setActiveConversation(conversation.getId());
        notifyObservers(UpdateType.ACTIVE_CONVERSATION);
    }

    //Exists for testing purposes
    public void addConversation(Conversation c) {
        conversations.put(c.getId(), c);
    }

    /**
     * Sets the name of the activeConversation.
     * If the name does not pass the validation, it sets the name to "" instead.
     * @param name The desired name
     */
    public void setConversationName(String name) {
            if (validateConversationName(name)){
                activeConversation.setName(name);
            }
            else{
                activeConversation.setName(""); //This forces the placeholder to be enforced when loading the conversation
            }

            notifyObservers(UpdateType.ACTIVE_CONVERSATION);
            notifyObservers(UpdateType.CONVERSATIONS);


    }

    private final static int MAX_LENGTH = 30;
    private final static int MIN_LENGTH = 0;

    /**
     * Checks if a name is within the MIN_LENGTH or MAX_LENGTH limits of he length of a conversation name.
     * @param name The name that is to be checked
     * @return True if the name is deemed ok
     */
    private boolean validateConversationName(String name) {
        return name.length() > MIN_LENGTH && name.length() < MAX_LENGTH;
    }

    /**
     * Constructs a placeholder name to a conversation
     * <p>The placeholder name consists of participants in the conversation
     * and follows the form: <b>Person3, Person2, Person1</b>.</p>
     * <p>If the name were to be too long, the form is instead: <b>Person19, Person18, Person17 + 16 more</b>.</p>
     * @param c The conversation which the placeholder name is generated for
     * @return The placeholder name
     */
    public String generatePlaceholderName(Conversation c) {
        //Return Placeholder if a conversation slips through that has no participants
        if (c.getParticipants() == null || c.getParticipants().isEmpty()){
            return "";
        }
        StringBuilder placeholderName = new StringBuilder();
        Stack<User> userStack = new Stack<>();

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
     * @return All conversations currently stored by the model
     */
    public Map<Integer, Conversation> getConversations() {
        return conversations;
    }

    /**
     *
     * @return All conversations in which the user is a participant
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


    /**
     * Sets the status of the activeUser
     * @param s The desired Status
     */
    public void setStatus(StatusType s) {
        activeUser.setStatus(s);
        notifyObservers(UpdateType.USER_INFO);
    }

    /**
     * @return All Users stored by the model
     */
    public Map<Integer, User> getUsers() {
        return users;
    }


    public void createUser(String u, String pw, String fn, String ln, Boolean a){
        int id = getNewUserId();
        User user = new User(id, u, pw, fn, ln, StatusType.Available, a);
        notifyObservers(UpdateType.CONTACTS);
        users.put(user.getId(), user);
        if (getActiveUser()==null){
            setActiveUser(user.getId());
        }
        if(activeUser.getId()!=id){
            getActiveUser().addContact(id);
        }

        notifyObservers(UpdateType.CONTACTS);
    }

    public User getActiveUser() {
        return activeUser;
    }

    public Conversation getActiveConversation() {
        return activeConversation;
    }


    /**
     * Checks whether or not the username or password matches an existing users login credentials.
     * <p>If a user is found, the activeUser is set to this user,
     * and the observers are told to update themselves</p>
     * @param username The username which is to be checked with an existing users username
     * @param password The password which is to be checked with an existing users password
     * @return The success of the login
     */
    public boolean login(String username, String password) {

        for (User u : users.values()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                setActiveUser(u);
                notifyObservers(UpdateType.INIT);
                return true;
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

    public Iterator<User> searchUsers(String input) {
        Iterator<User> iterator = getUsers().values().iterator();
        ArrayList<User> usersToShow = new ArrayList<>();
        User next;

        while (iterator.hasNext()) {
            next = iterator.next();
            if (next.getFullName().toLowerCase(new Locale("sv-SE")).contains(input.toLowerCase(new Locale("sv-SE")))) {
                usersToShow.add(next);
            }
        }
        return usersToShow.iterator();
    }

    /**
     * Removes the active user and users that are contacts to the active user from a collection
     * @param userIterator specifies what collection that should be cleansed from the active users contacts
     * @return a collection free from contacts to the active user
     */
    public Iterator<User> checkIteratorOfContacts(Iterator<User> userIterator) {
        List<User> users = new ArrayList<>();

        while (userIterator.hasNext()) {
            User next = userIterator.next();
            if ((!userIsContact(next)) && (next.getId() != activeUser.getId())) {
                users.add(next);
            }
        }

        return users.iterator();
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

            if (!conversationFound & next.getName().toLowerCase(new Locale("sv-SE")).contains(conversationSearchString.toLowerCase(new Locale("sv-SE")))) {
                conversationsToShow.add(next);
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
        return getNonParticipantsAsList(conversation).iterator();
    }

    private List<User> getNonParticipantsAsList(Conversation conversation) {
        ArrayList<User> usersNotInConversation = new ArrayList<>();
        Iterator<User> contacts = getContacts();
        User contact;

        while(contacts.hasNext()){
            contact = contacts.next();
            if(!conversation.getParticipants().contains(contact)){
                usersNotInConversation.add(contact);
            }
        }
        return usersNotInConversation;
    }

    /**
     * @param conversation The conversations
     * @return Participants of a conversation WITHOUT current activeUser.
     */
    public Iterator<User> getParticipants(Conversation conversation) {
        return getParticipantsAsList(conversation).iterator();
    }

    private List<User> getParticipantsAsList(Conversation conversation) {
        List<User> participants = new ArrayList<>(conversation.getParticipants());
        participants.remove(getActiveUser());
        return participants;
    }


    public Iterator<User> searchNonParticipants(String searchInput, Conversation conversation){
        List<User> matchingUsers = searchUserList(getNonParticipantsAsList(conversation), searchInput);

        return matchingUsers.iterator();
    }

    public Iterator<User> searchParticipants(String searchInput, Conversation conversation){
        List<User> matchingUsers = searchUserList(getParticipantsAsList(conversation), searchInput);
        return matchingUsers.iterator();
    }

    private List<User> searchUserList(List<User> users, String searchInput) {
        List<User> matchingUsers = new ArrayList<>();
        for (User user : users) {
            if(user.getFullName().contains(searchInput)){
                matchingUsers.add(user);
            }
        }
        return matchingUsers;
    }

    /**
     * Adds participants to the conversation
     * @param participantsToAdd specifies what participants will be added
     * @param conversation specifies what conversation the participants shall be added to
     */
    public void addParticipants(Iterator<User> participantsToAdd, Conversation conversation) {
        while (participantsToAdd.hasNext()){
            conversation.addParticipant(participantsToAdd.next());
        }
        notifyObservers(UpdateType.ACTIVE_CONVERSATION);
        notifyObservers(UpdateType.CONVERSATIONS);
    }

    /**
     * Removes participants from the conversation
     * @param participantsToRemove specifies what participants will be removed
     * @param conversation specifies what conversation the participants shall be removed from
     */
    public void removeParticipants(Iterator<User> participantsToRemove, Conversation conversation) {
        while (participantsToRemove.hasNext()){
            conversation.removeParticipant(participantsToRemove.next());
        }
        notifyObservers(UpdateType.ACTIVE_CONVERSATION);
        notifyObservers(UpdateType.CONVERSATIONS);
    }

}
