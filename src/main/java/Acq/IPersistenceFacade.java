/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acq;

import DTO.Inquiry;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ulriksandberg
 */
public interface IPersistenceFacade {
    
    boolean verifyUsername(String username);
    IResponse createUser(IUser user);

    List<Inquiry> downloadCurrentUserInquiries(UUID currentUserId);

    List<Inquiry> getInquriesByInquiryId(UUID id);

    List<Inquiry> getInquiresByCPR(String cpr);

    List<Inquiry> getInquiresByCitizenName(String name);

    IPersistanceUser login(String userName , String password);

    void injectInquiry(Inquiry inquiry);

    List<IPersistanceUser> getAllUsers(int page, int pageSize);
    
}
