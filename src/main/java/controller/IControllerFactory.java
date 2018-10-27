package controller;

import model.MainModel;
import sun.applet.Main;
import view.*;

public interface IControllerFactory {

    IChatController createChatController(IChatView chatView, MainModel mainModel);

    ILoginController createLoginController(ILoginView loginView, MainModel mainModel);

    ICreateConvoController createCreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel);

    IUserPageController createUserPageController(MainModel mainModel);

    ICreateUserViewController createCreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView);

    IUserToolbarController createUserToolBarController(MainModel mainModel, IMainView mainView);

    IRemoveParticipantsController createRemoveParticipantsController(IParticipantView removeParticipantsView, MainModel mainModel);

    IAddParticipantsController createAddParticipantsController(IParticipantView addParticipantsView, MainModel mainModel);

    IMainController createMainController(MainModel mainModel, IMainView mainView);

    IContactDetailViewController createContactDetailViewController(MainModel mainModel, IMainView mainView);

    IAddContactController createAddContactController(MainModel mainModel,IMainView mainView, IAddContactView addContactView);
}
