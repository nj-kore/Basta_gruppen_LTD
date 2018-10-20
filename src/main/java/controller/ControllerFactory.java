package controller;

import model.MainModel;
import sun.applet.Main;
import view.*;

public class ControllerFactory implements IControllerFactory {




    public IChatController getChatController(IChatView chatView, MainModel mainModel){
        IChatController chatController = new ChatController(chatView, mainModel);
        return chatController;
    }

    public ILoginController getLoginController(ILoginView loginView, MainModel mainModel){
        ILoginController loginController = new LoginController(loginView, mainModel);
        return loginController;
    }

    public ICreateConvoController getCreateConvoController(IMainView mainView, ICreateConvoView createConvoView, MainModel mainModel){
        ICreateConvoController convoController = new CreateConvoController(mainView, createConvoView, mainModel);
        return convoController;
    }

    public IUserPageController getUserPageController(MainModel mainModel){
        IUserPageController userPageController=new UserPageController(mainModel);
        return userPageController;
    }

    public ICreateUserViewController getCreateUserViewController(MainModel mainModel, IMainView mainView, ICreateUserView createUserView){
     ICreateUserViewController createUserViewController = new CreateUserViewController(mainModel, mainView, createUserView);
     return createUserViewController;
    }

    public IUserToolbarController getUserToolBarController(MainModel mainModel, IMainView mainView){
        IUserToolbarController userToolbarController = new UserToolbarController(mainModel, mainView);
        return userToolbarController;
    }

    public IRemoveParticipantsController getRemoveParticipantsController(RemoveParticipantsView removeParticipantsView, MainModel mainModel){
        IRemoveParticipantsController removeParticipantsController = new RemoveParticipantsController(removeParticipantsView, mainModel);
        return removeParticipantsController;
    }

    public IAddParticipantsController getAddParticipantsController(AddParticipantsView addParticipantsView, MainModel mainModel){
        IAddParticipantsController addParticipantsController = new AddParticipantsController(addParticipantsView, mainModel);
                return addParticipantsController;
    }



}
