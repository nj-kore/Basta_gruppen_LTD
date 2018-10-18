package view;

import controller.ParticipantsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import model.MainModel;
import model.User;

import java.io.IOException;
import java.util.Iterator;

public class ParticipantsView extends AnchorPane {

    MainModel mainModel;
    IChatView chatView;

    @FXML
    FlowPane participantsFlowPane;

    @FXML
    TextField searchParticipantsTextField;

    @FXML
    ImageView searchParticipantsImageView;

    @FXML
    Label noMatchingParticipantsLabel;

    public ParticipantsView(MainModel mainModel, IChatView chatView) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ParticipantList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainModel = mainModel;
        this.chatView = chatView;

        ParticipantsController controller = new ParticipantsController(this, mainModel);

        searchParticipantsImageView.setOnMouseClicked(event -> controller.searchParticipants());
        searchParticipantsTextField.setOnKeyPressed(event -> controller.isEnterPressed(event));
    }



    public void updateParticipants(){
        participantsFlowPane.getChildren().clear();
        for(User u : mainModel.getActiveConversation().getParticipants()){
            participantsFlowPane.getChildren().add(new ParticipantItem(u, this));
        }
    }

    public void showSearchResult(Iterator<User> iterator){
        participantsFlowPane.getChildren().clear();
        if(!iterator.hasNext()) participantsFlowPane.getChildren().add(noMatchingParticipantsLabel);
        while(iterator.hasNext()){
            User next = iterator.next();
            participantsFlowPane.getChildren().add(new ParticipantItem(next, this));
        }
    }

    public String getSearchString(){
        return searchParticipantsTextField.getText();
    }

    @FXML
    private void closeParticipants(){
        chatView.closeParticipants();
    }

    private class ParticipantItem extends AnchorPane {

        ParticipantsView parentView;        //TODO behövs denna om man ska ta bort items från participantsView?

        @FXML
        ImageView profilePictureImageView;
        @FXML
        Label nameLabel;
        @FXML
        ImageView statusImageView;
        @FXML
        ImageView removeParticipantImageView;
        @FXML
        Label statusLabel;

        public ParticipantItem(User user, ParticipantsView parentView) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/fxml/ParticipantListItem.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

            this.parentView = parentView;

            nameLabel.setText(user.getFullName());
            profilePictureImageView.setImage(new Image(user.getProfileImagePath()));
            statusImageView.setImage(new Image(user.getStatusImagePath()));
            statusLabel.setText(user.getStatus().toString());
        }
    }
}
