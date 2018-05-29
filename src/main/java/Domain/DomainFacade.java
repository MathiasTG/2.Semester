/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.*;
import DTO.Inquiry;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author ulriksandberg
 */
public class DomainFacade implements IDomainFacade {

    private IPersistenceFacade persistenceFacade;
    private Inquiry inquiry;
    private UserManager userManager;
    private Validate validate;
    private DataManager dataMan;

    public DomainFacade() {
        this.validate = new Validate();
        userManager = new UserManager();
        dataMan = new DataManager();
    }

    public IPersistenceFacade getPersistenceFacade() {
        return persistenceFacade;
    }

    @Override
    public void injectPersistence(IPersistenceFacade persistenceFacade) {
         this.persistenceFacade = persistenceFacade;
         userManager.injectpersistenceFacade(persistenceFacade);

    }

    public IPersistenceFacade getPersistence(){ return this.persistenceFacade;}

    public void injectInquiry(Inquiry inquiry){
        this.inquiry = inquiry;
        try {
            prepareInquiry();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void prepareInquiry() throws Exception {
        IUser currentUser = userManager.getCurrentUser();

        if(currentUser != null)
        {
            this.inquiry.setCreatedBy(userManager.getCurrentUser());
        }
        else {
            //Reject
            throw new Exception("Can't create inquiry when no user is logged-in");
        }
        if (inquiry.getId() == null) {
            this.inquiry.setId(UUID.randomUUID());
            persistenceFacade.injectInquiry(this.inquiry);
        }else{
            persistenceFacade.alterInquiry(this.inquiry);
        }

    }

    @Override
    public IResponse createUser(String username, int accessright) {

        if (persistenceFacade.verifyUsername(username)) {

            IUser user = null;

            switch (accessright) {
                case 1:
                    user = new Secretary(username, accessright, new Password());
                    break;
                case 2:
                    user = new Caseworker(username,accessright, new Password());
                    break;
                case 3:
                    user = new Admin(username, new Password());
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

    @Override
    public List<Inquiry> downloadCurrentUserInquiries() {

        return persistenceFacade.downloadCurrentUserInquiries(userManager.getCurrentUser().getID(), new UserBuilder());
    }

    @Override
    public List<Inquiry> getInquriesByInquiryId(UUID id) {

        return persistenceFacade.getInquriesByInquiryId(id, new UserBuilder());
    }

    @Override
    public List<Inquiry> getInquiresByCPR(String cpr) {

        return persistenceFacade.getInquiresByCPR(cpr, new UserBuilder());
    }

    @Override
    public List<Inquiry> getInquiresByCitizenName(String name) {

        return persistenceFacade.getInquiresByCitizenName(name, new UserBuilder());
    }

    public void logout()
    {
        userManager.logout();
    }

    public boolean validateNumber(int lenght, String value){
        return this.validate.validateNumber(lenght, value);
    }

    public boolean validateEmail(String email){
        return this.validate.validateEmail(email);
    }

    public List<IUser> revertIPUserToIUser(List<IPersistanceUser> IPUser) { return this.dataMan.revertIPUserToIUser(IPUser);}

}
