package controller;

import controller.participants.AddParticipantsController;
import controller.participants.IParticipantsController;
import controller.participants.RemoveParticipantsController;
import model.MainModel;
import view.*;
import view.chat.AddParticipantsView;
import view.chat.IChatView;
import view.chat.RemoveParticipantsView;

public class ControllerFactory implements IControllerFactory {

    public IChatController createChatController(IChatView chatView, MainModel mainModel){
        return new ChatController(chatView, mainModel);
    }

    public ILoginController createLoginController(ILoginView loginView, MainModel mainModel){
        return new LoginController(loginView, mainModel);
    }

    public ICreateConvoController createCreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel){
        return new CreateConvoController(mainView, createConvoView, mainModel);
    }

    public IUserPageController createUserPageController(MainModel mainModel){
        return new UserPageController(mainModel);
    }

    public ICreateUserViewController createCreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView){
     return new CreateUserViewController(mainModel, mainView, createUserView);
    }

    public IUserToolbarController createUserToolBarController(MainModel mainModel, IMainView mainView){
        return new UserToolbarController(mainModel, mainView);
    }

    public IParticipantsController createRemoveParticipantsController(RemoveParticipantsView removeParticipantsView, MainModel mainModel){
        return new RemoveParticipantsController(removeParticipantsView, mainModel);
    }

    public IParticipantsController createAddParticipantsController(AddParticipantsView addParticipantsView, MainModel mainModel){
        return new AddParticipantsController(addParticipantsView, mainModel);
    }
    public IMainController createMainController(MainModel mainModel, IMainView mainView){
        IMainController mainController=new MainController(mainView, mainModel);
        return mainController;
    }

    public IContactDetailViewController createContactDetailViewController(MainModel mainModel, IMainView mainView) {
        return new ContactDetailViewController(mainModel, mainView);
    }

}
