package controller;

import controller.participants.IParticipantsController;
import model.MainModel;
import sun.applet.Main;
import view.*;
import view.chat.AddParticipantsView;
import view.chat.IChatView;
import view.chat.IParticipantView;
import view.chat.RemoveParticipantsView;

public interface IControllerFactory {

    IChatController createChatController(IChatView chatView, MainModel mainModel);

    ILoginController createLoginController(ILoginView loginView, MainModel mainModel);

    ICreateConvoController createCreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel);

    IUserPageController createUserPageController(MainModel mainModel);

    ICreateUserViewController createCreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView);

    IUserToolbarController createUserToolBarController(MainModel mainModel, IMainView mainView);

    IParticipantsController createRemoveParticipantsController(IParticipantView removeParticipantsView, MainModel mainModel);

    IParticipantsController createAddParticipantsController(IParticipantView addParticipantsView, MainModel mainModel);

    IMainController createMainController(MainModel mainModel, IMainView mainView);

    IContactDetailViewController createContactDetailViewController(MainModel mainModel, IMainView mainView);

    IAddContactController createAddContactController(MainModel mainModel,IMainView mainView, IAddContactView addContactView);
}
