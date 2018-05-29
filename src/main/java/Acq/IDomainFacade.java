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

    /**
     * Injects a DTO object of the inquiry type, to the domain facade
     * @param inquiry, the inquiry which transfers to the domain facade
     */
    void injectInquiry(Inquiry inquiry);

    String getCurrentUserName();
    int getCurrentUserAccessRights();

    List<Inquiry> downloadCurrentUserInquiries();

    List<Inquiry> getInquriesByInquiryId(UUID id);

    List<Inquiry> getInquiresByCPR(String cpr);

    List<Inquiry> getInquiresByCitizenName(String name);

    void logout();

    /**
     * Checks wether a String is a certain length, and only contains numbers
     * @param lenght The allowed lenght of the number being checked
     * @param value The String to be checked for validity
     * @return true if the String only contains numbers, and is as long as specified
     */
    boolean validateNumber(int lenght, String value);

    /**
     * Checks whether a string representing a email is valid, based on a regular expression
     * @param email The string to be checked
     * @return true if the email is valid, false if not
     */
    boolean validateEmail(String email);

    /**
     * Manually "Casts" a list of IPersistenseUsers to IUsers
     * @param IPUser is a user defined in the IPersistenceUser interface
     * @return a list of IUsers, whom are local to the domain layer
     */
    List<IUser> revertIPUserToIUser(List<IPersistanceUser> IPUser);

    IPersistenceFacade getPersistence();
    
}
