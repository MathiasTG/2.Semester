/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.*;
import DTO.Inquiry;

/**
 *
 * @author ulriksandberg
 */
public class DomainFacade implements IDomainFacade {

    private IPersistenceFacade persistenceFacade;
    private Inquiry inquiry;
    private UserManager userManager;

    public DomainFacade() {

        userManager = new UserManager();
    }

    public IPersistenceFacade getPersistenceFacade() {
        return persistenceFacade;
    }

    @Override
    public void injectPersistence(IPersistenceFacade persistenceFacade) {
         this.persistenceFacade = persistenceFacade;
         userManager.injectpersistenceFacade(persistenceFacade);

    }

    public void injectInquiry(Inquiry inquiry){
        this.inquiry = inquiry;
    }

    @Override
    public IResponse createUser(String username, int accessright) {

        if (persistenceFacade.verifyUsername(username)) {

            String type = null;
            IUser user = null;

            switch (accessright) {
                case 1:
                    user = new Secretary(username, accessright, new Password());

                    break;
                case 2:
                    user = new Caseworker(username,accessright, new Password());

                    break;
                case 3:
                    user = new Admin(username, accessright, new Password());

                    break;
            }

            persistenceFacade.createUser(user);
            return new Response(true, user.getPassword());
        }
        return new Response(false, "Brugernavn er allerede i brug");
    }


    public IResponse logIn(String userName , String password) {

      boolean IsSuccesfull = userManager.login(userName,password);

      if(IsSuccesfull)
      {
          return new Response(true);
      }

      return new Response(false, "Something went wrong, try again!");

    }

    public String getCurrentUserName()
    {
        return userManager.getUsername();
    }

    public int getCurrentUserAccessRights()
    {
        return userManager.getAuthenticationLevel();
    }

    public void logout()
    {
        userManager.logout();
    }

}
