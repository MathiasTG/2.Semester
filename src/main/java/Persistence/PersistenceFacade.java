/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Acq.*;
import DTO.Inquiry;
import Domain.Response;

import java.sql.SQLException;

/**
 *
 * @author ulriksandberg
 */
public class PersistenceFacade implements IPersistenceFacade {

    private PersistenceFile pFile;
    private IConfiguration configurations;
    private Inquiry inquiry;
    private IRepositoryUser repositoryUser;
    
    public PersistenceFacade()
    {
        pFile = new PersistenceFile();
        configurations = new Configuration();
        try
        {
            repositoryUser = new UserRepository();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getStackTrace());
        }

        System.out.println(ResponseCode.SUCCESS.toString());
    }
    
    /**
     * Validates Username in persistence.
     * @param username
     * @return True if Username is available else returns false
     */
    @Override
    public boolean verifyUsername(String username) {
        return repositoryUser.validateUsername(username);
    }
    

    @Override
    public IResponse createUser(IUser user) {

        ResponseMessage response  = repositoryUser.createUser(user);

        if(response.getResponseCode().equals(ResponseCode.SUCCESS))
        {
            return new Response(true);
        }

        return new Response(false, response.getResponseCode().toString());
    }

    public void injectInquiry(Inquiry inquiry){
        this.inquiry = inquiry;
    }
    
}
