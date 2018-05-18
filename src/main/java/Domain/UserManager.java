package Domain;

import Acq.IPersistanceUser;
import Acq.IPersistenceFacade;
import Acq.IUser;

public class UserManager {


    private static IUser currentUser = null;
    private IPersistenceFacade persistenceFacade;


    public UserManager() {

    }


    public boolean login(String userName , String password) {

        IPersistanceUser puser =  persistenceFacade.login(userName , password);

        if (puser == null) {
            return false;
        }

        // if succesfull do this
        switch (puser.getAccessRight()) {
            case 1:
                currentUser = new Secretary(puser.getID(), puser.getUsername(), puser.getAccessRight());
                return true;
            case 2:
                currentUser = new Caseworker(puser.getID(), puser.getUsername(), puser.getAccessRight());
                return true;
            default:
                System.out.println("Cant login user with that accesright");
                return false;

        }
    }


    public IUser getCurrentUser()
    {
        return currentUser;
    }

    public int getAuthenticationLevel() {
        if (currentUser!= null) {
           return currentUser.getAccessRight();
        }
        else return -1;
    }

    public String getUsername() {
        if (currentUser != null) {

            return this.currentUser.getUsername();
        }

        return null;
    }


    public void logout() {
        currentUser = null;
    }

    public void injectpersistenceFacade(IPersistenceFacade persistenceFacade) {

    this.persistenceFacade = persistenceFacade;

    }



}
