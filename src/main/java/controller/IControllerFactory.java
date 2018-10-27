package controller;

import controller.participants.IParticipantsController;
import model.MainModel;
import view.*;
import view.chat.AddParticipantsView;
import view.chat.IChatView;
import view.chat.RemoveParticipantsView;

public interface IControllerFactory {

    IChatController getChatController(IChatView chatView, MainModel mainModel);

    ILoginController getLoginController(ILoginView loginView, MainModel mainModel);

    ICreateConvoController getCreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel);

    IUserPageController getUserPageController(MainModel mainModel);

    ICreateUserViewController getCreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView);

    IUserToolbarController getUserToolBarController(MainModel mainModel, IMainView mainView);

    IParticipantsController getRemoveParticipantsController(RemoveParticipantsView removeParticipantsView, MainModel mainModel);

    IParticipantsController getAddParticipantsController(AddParticipantsView addParticipantsView, MainModel mainModel);

    IMainController getMainController(MainModel mainModel, IMainView mainView);
}
