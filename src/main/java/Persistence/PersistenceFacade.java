/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Acq.*;
import DTO.Inquiry;
import Domain.Response;
import Domain.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ulriksandberg
 */
public class PersistenceFacade implements IPersistenceFacade {

    private PersistenceFile pFile;
    private IConfiguration configurations;
    private Inquiry inquiry;
    private IUserRepository userRepository;
    private IInquiryRepository inquiryRepository;
    
    public PersistenceFacade()
    {
        pFile = new PersistenceFile();
        configurations = new Configuration();

        userRepository = new UserRepository();
        inquiryRepository = new InquiryRepository();


    }
    
    /**
     * Validates Username in persistence.
     * @param username
     * @return True if Username is available else returns false
     */
    @Override
    public boolean verifyUsername(String username) {
        return userRepository.validateUsername(username);
    }
    

    @Override
    public IResponse createUser(IUser user) {

        ResponseMessage response  = userRepository.createUser(user);

        if(response.getResponseCode().equals(ResponseCode.SUCCESS))
        {
            return new Response(true);
        }

        return new Response(false, response.getResponseCode().toString());
    }

    @Override
    public List<Inquiry> downloadCurrentUserInquiries(UUID currentUserId) {
       return inquiryRepository.getAllInquiriesByUserId(currentUserId);
    }

    @Override
    public List<Inquiry> getInquriesByInquiryId(UUID id) {
        return inquiryRepository.getInquriesByInquiryId(id);
    }

    @Override
    public List<Inquiry> getInquiresByCPR(String cpr) {
        return inquiryRepository.getInquiresByCPR(cpr);
    }

    @Override
    public List<Inquiry> getInquiresByCitizenName(String name) {
        return inquiryRepository.getInquiresByCitizenName(name);
    }


    public IPersistanceUser login(String userNamer , String password) {
        
        IPersistanceUser user = userRepository.login(userNamer , password);

        return user;
    }


    public void injectInquiry(Inquiry inquiry){

        inquiryRepository.create(inquiry);
    }
}
