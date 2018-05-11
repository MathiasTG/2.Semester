/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.IDomainFacade;
import Acq.IPersistenceFacade;
import Acq.IResponse;
import Acq.IUser;
import DTO.Inquiry;

/**
 *
 * @author ulriksandberg
 */
public class DomainFacade implements IDomainFacade {

    private IPersistenceFacade persistenceFacade;
    private static DomainFacade domain;
    private DomainController domainController;
    private Inquiry inquiry;

    public DomainFacade() {
        domain = this;
        domainController = new DomainController();
    }

    public static DomainFacade getInstance() {
        return domain;
    }

    public IPersistenceFacade getPersistenceFacade() {
        return persistenceFacade;
    }

    @Override
    public void injectPersistence(IPersistenceFacade persistenceFacade) {
         this.persistenceFacade = persistenceFacade;
    }

    @Override
    public IResponse createUser(String username, int accesRights) {
        

        
        return domainController.createUser(username,accesRights);
    }
    public void injectInquiry(Inquiry inquiry){
        this.inquiry = inquiry;
    }

    public static void main(String[] args) {
        
        Password pass = new Password();
        
    }


}
