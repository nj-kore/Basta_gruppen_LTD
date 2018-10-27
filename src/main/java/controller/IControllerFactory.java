package controller;

import controller.participants.IParticipantsController;
import model.MainModel;
import view.*;
import view.chat.AddParticipantsView;
import view.chat.IChatView;
import view.chat.RemoveParticipantsView;

public interface IControllerFactory {

    IChatController createChatController(IChatView chatView, MainModel mainModel);

    ILoginController createLoginController(ILoginView loginView, MainModel mainModel);

    ICreateConvoController createCreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel);

    IUserPageController createUserPageController(MainModel mainModel);

    ICreateUserViewController createCreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView);

    IUserToolbarController createUserToolBarController(MainModel mainModel, IMainView mainView);

    IParticipantsController createRemoveParticipantsController(RemoveParticipantsView removeParticipantsView, MainModel mainModel);

    IParticipantsController createAddParticipantsController(AddParticipantsView addParticipantsView, MainModel mainModel);

    IMainController createMainController(MainModel mainModel, IMainView mainView);

    IContactDetailViewController createContactDetailViewController(MainModel mainModel, IMainView mainView);
}
