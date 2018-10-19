package view;

import controller.AddParticipantsController;
import controller.IAddParticipantsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Conversation;
import model.MainModel;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AddParticipantsView extends AnchorPane implements IParticipantView {

    MainModel mainModel;
    private IChatView chatView;
    private Conversation conversation;

    @FXML
    private FlowPane nonParticipantsFlowPane;

    @FXML
    private TextField searchNonParticipantsTextField;

    @FXML
    private ImageView searchNonParticipantsImageView;

    @FXML
    private Label noMatchingNonParticipantsLabel;

    @FXML
    private FlowPane contactsToAddFlowPane;

    @FXML
    private Button addParticipantsButton;

    AddParticipantsView(MainModel mainModel, IChatView chatView, Conversation conversation) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/AddParticipantsView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainModel = mainModel;
        this.chatView = chatView;
        this.conversation = conversation;

        IAddParticipantsController controller = new AddParticipantsController(this, mainModel);

        searchNonParticipantsImageView.setOnMouseClicked(event -> controller.searchNonParticipants());
        searchNonParticipantsTextField.setOnKeyPressed(controller::isEnterPressed);
        addParticipantsButton.setOnAction(event -> controller.addParticipants());
    }


    @Override
    public void showSearch(Iterator<User> usersToShow) {
        nonParticipantsFlowPane.getChildren().clear();
        User userToShow;

        if(!usersToShow.hasNext()) nonParticipantsFlowPane.getChildren().add(noMatchingNonParticipantsLabel);

        while(usersToShow.hasNext()){
            userToShow = usersToShow.next();
            nonParticipantsFlowPane.getChildren().add(new ParticipantItem(userToShow, this));
        }
    }

    @Override
    public String getSearchString(){
        return searchNonParticipantsTextField.getText();
    }

    @Override
    public void addOrRemoveFromConversation() {

    }

    @Override
    @FXML
    public void closeView() {
        chatView.closeAddParticipants();
    }

    @Override
    public void update() {
        this.conversation = mainModel.getActiveConversation();
        Iterator<User> usersToShow = mainModel.getNonParticipants(conversation);
        nonParticipantsFlowPane.getChildren().clear();
        contactsToAddFlowPane.getChildren().clear();
        User nonParticipant;

        while (usersToShow.hasNext()){
            nonParticipant = usersToShow.next();
            nonParticipantsFlowPane.getChildren().add(new ParticipantItem(nonParticipant, this));
        }
    }

    @Override
    public void select(ParticipantItem participantItem) {
        nonParticipantsFlowPane.getChildren().remove(participantItem);
        contactsToAddFlowPane.getChildren().add(participantItem);
    }

    @Override
    public Iterator<User> getParticipantsToAddOrRemove() {
        ArrayList<User> usersToAdd = new ArrayList<>();

        for(Node userNode : contactsToAddFlowPane.getChildren()){
            ParticipantItem userParticipantItem = (ParticipantItem) userNode;
            usersToAdd.add(userParticipantItem.getUser());
        }
        return usersToAdd.iterator();
    }

    @Override
    public Conversation getConversation() {
        return conversation;
    }


}
