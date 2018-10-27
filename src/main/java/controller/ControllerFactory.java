package controller;

import model.MainModel;
import sun.applet.Main;
import view.*;

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

    public IRemoveParticipantsController createRemoveParticipantsController(IParticipantView removeParticipantsView, MainModel mainModel){
        return new RemoveParticipantsController(removeParticipantsView, mainModel);
    }

    public IAddParticipantsController createAddParticipantsController(IParticipantView addParticipantsView, MainModel mainModel){
        return new AddParticipantsController(addParticipantsView, mainModel);
    }
    public IMainController createMainController(MainModel mainModel, IMainView mainView){
        IMainController mainController=new MainController(mainView, mainModel);
        return mainController;
    }

    public IContactDetailViewController createContactDetailViewController(MainModel mainModel, IMainView mainView) {
        return new ContactDetailViewController(mainModel, mainView);
    }

    public IAddContactController createAddContactController(MainModel mainModel,IMainView mainView, IAddContactView addContactView) {
        return new AddContactController(mainView, mainModel, addContactView);
    }
}
