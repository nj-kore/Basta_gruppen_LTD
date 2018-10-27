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

    public IChatController getChatController(IChatView chatView, MainModel mainModel){
        return new ChatController(chatView, mainModel);
    }

    public ILoginController getLoginController(ILoginView loginView, MainModel mainModel){
        return new LoginController(loginView, mainModel);
    }

    public ICreateConvoController getCreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel){
        return new CreateConvoController(mainView, createConvoView, mainModel);
    }

    public IUserPageController getUserPageController(MainModel mainModel){
        return new UserPageController(mainModel);
    }

    public ICreateUserViewController getCreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView){
     return new CreateUserViewController(mainModel, mainView, createUserView);
    }

    public IUserToolbarController getUserToolBarController(MainModel mainModel, IMainView mainView){
        return new UserToolbarController(mainModel, mainView);
    }

    public IParticipantsController getRemoveParticipantsController(RemoveParticipantsView removeParticipantsView, MainModel mainModel){
        return new RemoveParticipantsController(removeParticipantsView, mainModel);
    }

    public IParticipantsController getAddParticipantsController(AddParticipantsView addParticipantsView, MainModel mainModel){
        return new AddParticipantsController(addParticipantsView, mainModel);
    }
    public IMainController getMainController(MainModel mainModel, IMainView mainView){
        IMainController mainController=new MainController(mainView, mainModel);
        return mainController;
    }


}
