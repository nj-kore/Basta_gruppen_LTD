package model;

import java.util.Observable;

public class MainModel implements IDatabaseHandler {
    private FileHandler fh = new FileHandler();
    private User activeUser = new User(0);

    IMainModel imodel = new IMainModel() {
        public void sendMessage(int conversationId, Message message) {
            String text = message.getSender().getId()+";"+message.getText()+"\n";
            fh.write(Integer.toString(conversationId), text);
        }
        public Conversation loadConversation(int conversationId) {
            return fh.loadConversation(conversationId);
        }

        public User getActiveUser() {
            return activeUser;
        }
        public void update(Observable o, Object arg) {

        }
    };





}
