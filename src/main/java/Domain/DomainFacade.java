/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.*;
import DTO.ConsentEntity;
import DTO.Inquiry;

import java.util.UUID;

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
        try {
            prepareInquiry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void prepareInquiry() throws Exception {
        this.inquiry.setId(UUID.randomUUID());
        IUser currentUser = userManager.getCurrentUser();
        if(currentUser != null)
        {
            this.inquiry.setCreatedBy(userManager.getCurrentUser());
            //Create inquiry
            persistenceFacade.injectInquiry(this.inquiry);
        }
        else {
            //Reject
            throw new Exception("Can't create inquiry when no user is logged-in");
        }
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
