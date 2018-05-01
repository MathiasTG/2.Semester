/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.IDomainFacade;
import Acq.IPersistenceFacade;

/**
 *
 * @author ulriksandberg
 */
public class DomainFacade implements IDomainFacade {

    private IPersistenceFacade persistenceFacade;
    

    @Override
    public void injectPersistence(IPersistenceFacade persistenceFacade) {
         this.persistenceFacade = persistenceFacade;
    }

    @Override
    public String createUser(String username, int accesRights) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}