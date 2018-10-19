package view;

import controller.ParticipantsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.Conversation;
import model.MainModel;
import model.User;

import java.io.IOException;
import java.util.Iterator;

public class RemoveParticipantsView extends AnchorPane implements IParticipantView {

    MainModel mainModel;
    IChatView chatView;
    Conversation conversation;

    @FXML
    private FlowPane participantsFlowPane;

    @FXML
    private TextField searchParticipantsTextField;

    @FXML
    private ImageView searchParticipantsImageView;

    @FXML
    private Label noMatchingParticipantsLabel;

    @FXML
    private FlowPane participantsToRemoveFlowPane;

    public RemoveParticipantsView(MainModel mainModel, IChatView chatView, Conversation conversation) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ParticipantList.fxml"));
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

        ParticipantsController controller = new ParticipantsController(this, mainModel);

        searchParticipantsImageView.setOnMouseClicked(event -> controller.searchParticipants());
        searchParticipantsTextField.setOnKeyPressed(event -> controller.isEnterPressed(event));

    }




    public void showSearchResult(Iterator<User> iterator){
        participantsFlowPane.getChildren().clear();
        if(!iterator.hasNext()) participantsFlowPane.getChildren().add(noMatchingParticipantsLabel);
        while(iterator.hasNext()){
            User next = iterator.next();
            participantsFlowPane.getChildren().add(new ParticipantItem(next, this));
        }
    }

    @Override
    public void showSearch(Iterator<User> usersToShow) {

    }

    @Override
    public String getSearchString(){
        return searchParticipantsTextField.getText();
    }



    @Override
    public void addOrRemoveFromConversation() {

    }

    @Override
    @FXML
    public void closeView() {
        chatView.closeRemoveParticipants();
    }

    @Override
    public void update() {
        this.conversation = mainModel.getActiveConversation();
        participantsFlowPane.getChildren().clear();
        participantsToRemoveFlowPane.getChildren().clear();
        for(User u : mainModel.getActiveConversation().getParticipants()){
            participantsFlowPane.getChildren().add(new ParticipantItem(u, this));
        }
    }

    @Override
    public void select(ParticipantItem participantItem) {
        participantsFlowPane.getChildren().remove(participantItem);
        participantsToRemoveFlowPane.getChildren().add(participantItem);
    }

    @Override
    public Iterator<User> getParticipantsToAddOrRemove() {
        return null; //TODO
    }

    @Override
    public Conversation getConversation() {
        return conversation;
    }

}
