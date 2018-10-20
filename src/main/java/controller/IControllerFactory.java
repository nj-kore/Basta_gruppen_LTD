package controller;

import model.MainModel;
import view.*;

public interface IControllerFactory {

    public IChatController getChatController(IChatView chatView, MainModel mainModel);

    public ILoginController getLoginController(ILoginView loginView, MainModel mainModel);

    public ICreateConvoController getCreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel);

    public IUserPageController getUserPageController(MainModel mainModel);

    public ICreateUserViewController getCreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView);

    public IUserToolbarController getUserToolBarController(MainModel mainModel, IMainView mainView);

    public IRemoveParticipantsController getRemoveParticipantsController(RemoveParticipantsView removeParticipantsView, MainModel mainModel);

    public IAddParticipantsController getAddParticipantsController(AddParticipantsView addParticipantsView, MainModel mainModel);
}
