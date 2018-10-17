package controller;

import model.MainModel;
import view.ParticipantsView;

public class ParticipantsController implements IParticipantsController{
    ParticipantsView participantsView;
    MainModel mainModel;

    public ParticipantsController(ParticipantsView participantsView, MainModel mainModel) {
        this.participantsView = participantsView;
        this.mainModel = mainModel;
    }


}