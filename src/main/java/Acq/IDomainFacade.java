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
public interface IDomainFacade {
    
    void injectPersistence(IPersistenceFacade persistenceFacade);
    IResponse createUser(String username, int accesRights);

    IResponse logIn(String userName , String password);
    void injectInquiry(Inquiry inquiry);

    String getCurrentUserName();
    int getCurrentUserAccessRights();

    List<Inquiry> downloadCurrentUserInquiries();

    List<Inquiry> getInquriesByInquiryId(UUID id);

    List<Inquiry> getInquiresByCPR(String cpr);

    List<Inquiry> getInquiresByCitizenName(String name);

    void logout();

    boolean validateNumber(int lenght, String value);

    boolean validateEmail(String email);

    List<IUser> revertIPUserToIUser(List<IPersistanceUser> IPUser);

    IPersistenceFacade getPersistence();
    
}
