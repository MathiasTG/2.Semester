package Domain;

import Acq.IPersistanceUser;
import Acq.IPersistenceFacade;
import Acq.IUser;

public class UserManager {


    private static IUser currentUser = null;
    private IPersistenceFacade persistenceFacade;


    public UserManager() {

    }


    public void login(String userName , String password) {



        IPersistanceUser puser =  persistenceFacade.login(userName , password);

        if (puser == null) {
            return;
        }

        // if succesfull do this

        switch (puser.getAccessRight()) {
            case 1:
                currentUser = new Secretary(puser.getUsername() , puser.getAccessRight() , new Password());
                break;
            case 2:
                currentUser = new Caseworker(puser.getUsername() , puser.getAccessRight() , new Password());
                break;

            default:
                System.out.println("Cant create user with that accesright");

        }


        // if unsuccesful send error fucking response

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
