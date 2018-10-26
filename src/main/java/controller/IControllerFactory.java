package controller;

import model.MainModel;
import view.*;

public interface IControllerFactory {

    IChatController getChatController(IChatView chatView, MainModel mainModel);

    ILoginController getLoginController(ILoginView loginView, MainModel mainModel);

    ICreateConvoController getCreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel);

    IUserPageController getUserPageController(MainModel mainModel);

    ICreateUserViewController getCreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView);

    IUserToolbarController getUserToolBarController(MainModel mainModel, IMainView mainView);

    IRemoveParticipantsController getRemoveParticipantsController(RemoveParticipantsView removeParticipantsView, MainModel mainModel);

    IAddParticipantsController getAddParticipantsController(AddParticipantsView addParticipantsView, MainModel mainModel);

    IMainController getMainController(MainModel mainModel, IMainView mainView);

    IContactDetailViewController getContactDetailViewController(MainModel mainModel, MainView mainView);
}
