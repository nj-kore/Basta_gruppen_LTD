package view.chat;

import controller.participants.IParticipantsController;
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

/**
 * @author Benjamin Vinnerholt
 * @author Jonathan Köre
 */

abstract class AbstractParticipantView extends AnchorPane implements IParticipantView {

    @FXML
    private FlowPane selectFlowPane;
    @FXML
    private FlowPane selectedFlowPane;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView searchImageView;
    @FXML
    private Label noMatchLabel;
    @FXML
    private Button changeParticipantsButton;
    @FXML
    private Label titleLabel;


    MainModel mainModel;
    private IChatView chatView;
    Conversation conversation;


    AbstractParticipantView(MainModel mainModel, IChatView chatView) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ParticipantsView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainModel = mainModel;
        this.chatView = chatView;
        this.conversation = mainModel.getActiveConversation();
    }

    protected void bindController(IParticipantsController controller) {
        searchImageView.setOnMouseClicked(event -> controller.search());
        searchField.setOnKeyPressed(controller::isEnterPressed);
        changeParticipantsButton.setOnAction(event -> controller.changeParticipants());
    }

    @Override
    public void showSearch(Iterator<User> usersToShow) {
        selectFlowPane.getChildren().clear();
        User userToShow;

        if(!usersToShow.hasNext()) {
            selectFlowPane.getChildren().add(noMatchLabel);
        }

        while(usersToShow.hasNext()){
            userToShow = usersToShow.next();
            selectFlowPane.getChildren().add(new ParticipantItem(userToShow));
        }
    }

    @Override
    public String getSearchString() {
        return searchField.getText();
    }

    @Override
    public void closeView() {
        chatView.closeParticipantsView();
    }

    @Override
    public void update() {
        this.conversation = mainModel.getActiveConversation();
        selectFlowPane.getChildren().clear();
        selectedFlowPane.getChildren().clear();
    }

    @Override
    public void moveUsers() {
        ArrayList<Node> paneList = new ArrayList<>();
        paneList.addAll(selectFlowPane.getChildren());
        paneList.addAll(selectedFlowPane.getChildren());

        for (Node node : paneList) {

            ParticipantItem participantItem = (ParticipantItem) node;

            if (participantItem.getIsClicked()) {

                if (selectFlowPane.getChildren().contains(node)) {

                    selectFlowPane.getChildren().remove(node);
                    selectedFlowPane.getChildren().add(node);
                } else if(selectedFlowPane.getChildren().contains(node)){

                    selectFlowPane.getChildren().add(node);
                    selectedFlowPane.getChildren().remove(node);
                }
                participantItem.setClicked(false);
                participantItem.setClickedColour();
            }
        }
    }

    @Override
    public Iterator<User> getParticipantsToAddOrRemove() {
        ArrayList<User> usersToAdd = new ArrayList<>();

        for(Node userNode : selectedFlowPane.getChildren()){
            ParticipantItem userParticipantItem = (ParticipantItem) userNode;
            usersToAdd.add(userParticipantItem.getUser());
        }
        return usersToAdd.iterator();
    }

    @Override
    public Conversation getConversation() {
        return conversation;
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
    public void setButtonText(String text) {
        changeParticipantsButton.setText(text);
    }
    public void setNoMatchText(String text) {
        noMatchLabel.setText(text);
    }
}
