/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Acq.IPersistenceFacade;
import Acq.IUser;

/**
 *
 * @author ulriksandberg
 */
public class PersistenceFacade implements IPersistenceFacade {

    
    private IConfiguration configurations;
    
    public PersistenceFacade()
    {
        configurations = new Configuration();
    }
    
    @Override
    public boolean verifyUsername(String username) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createUser(IUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
