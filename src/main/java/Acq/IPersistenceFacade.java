/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acq;

import DTO.Inquiry;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author ulriksandberg
 */
public interface IPersistenceFacade {
    
    boolean verifyUsername(String username);
    IResponse createUser(IUser user);

    List<Inquiry> downloadCurrentUserInquiries(UUID currentUserId, IUserBuilder builder);

    List<Inquiry> getInquriesByInquiryId(UUID id, IUserBuilder builder);

    List<Inquiry> getInquiresByCPR(String cpr, IUserBuilder builder);

    List<Inquiry> getInquiresByCitizenName(String name, IUserBuilder builder);

    IPersistanceUser login(String userName , String password);

    void injectInquiry(Inquiry inquiry);

    List<IPersistanceUser> getAllUsers(int page, int pageSize);

    void deleteById(UUID uuid);

    void changeUserName(IUser user, String name);

    void changeAccessRight(IUser user, int accessright);

    void changePassword(IUser user, String password, boolean isTemporary);
    
}
