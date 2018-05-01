/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.IDomainFacade;
import Acq.IPersistenceFacade;
import Acq.IUser;

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
        
        if(persistenceFacade.verifyUsername(username))
        {
            
            IUser user = new Secretary(username, accesRights, new Password());
            persistenceFacade.createUser(user);

        }
        
        return "";
    }
    

    public static void main(String[] args) {
        
        Password pass = new Password();
        
    }


}
