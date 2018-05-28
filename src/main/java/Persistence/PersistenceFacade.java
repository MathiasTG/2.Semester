/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Acq.*;
import DTO.Inquiry;
import Domain.Response;

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
    public List<Inquiry> downloadCurrentUserInquiries(UUID currentUserId, IUserBuilder builder) {
       return inquiryRepository.getAllInquiriesByUserId(currentUserId, builder);
    }

    @Override
    public List<Inquiry> getInquriesByInquiryId(UUID id, IUserBuilder builder) {
        return inquiryRepository.getInquriesByInquiryId(id, builder);
    }

    @Override
    public List<Inquiry> getInquiresByCPR(String cpr, IUserBuilder builder) {
        return inquiryRepository.getInquiresByCPR(cpr, builder);
    }

    @Override
    public List<Inquiry> getInquiresByCitizenName(String name, IUserBuilder builder) {
        return inquiryRepository.getInquiresByCitizenName(name, builder);
    }

    @Override
    public List<IPersistanceUser> getAllUsers(int page, int pageSize) {
        return this.userRepository.getAllUsers(page, pageSize);
    }

    public IPersistanceUser login(String userNamer , String password) {
        
        IPersistanceUser user = userRepository.login(userNamer , password);

        return user;
    }


    public void injectInquiry(Inquiry inquiry){

        inquiryRepository.create(inquiry);
    }

    public void deleteById(UUID uuid){
        this.userRepository.deleteById(uuid);
    }

    public void changeUserName(IUser user, String name){
        this.userRepository.changeUserName(user, name);
    }

    public void changeAccessRight(IUser user, int accessright){
        this.userRepository.changeAccessRight(user, accessright);
    }

    public void changePassword(IUser user, String password, boolean isTemporary){
        userRepository.changePassword(user, password, isTemporary);
    }

    @Override
    public void alterInquiry(Inquiry inquiry) {
        inquiryRepository.alter(inquiry);
    }
}
