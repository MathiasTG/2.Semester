/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Acq.IPersistenceFacade;
import Acq.IRepositoryInquiry;
import Acq.IUser;
import DTO.Inquiry;

/**
 *
 * @author ulriksandberg
 */
public class PersistenceFacade implements IPersistenceFacade {

    private PersistenceFile pFile;
    private IConfiguration configurations;
    private Inquiry inquiry;
    
    public PersistenceFacade()
    {
        pFile = new PersistenceFile();
        configurations = new Configuration();
    }
    
    /**
     * Validates Username in persistence.
     * @param username
     * @return True if Username is available else returns false
     */
    @Override
    public boolean verifyUsername(String username) {
        return pFile.validateUsername(username);
    }
    

    @Override
    public void createUser(IUser user) {
        pFile.createUser(user);
    }

    public void injectInquiry(Inquiry inquiry){
        this.inquiry = inquiry;
    }
    
}
