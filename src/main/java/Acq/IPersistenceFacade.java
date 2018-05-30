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

    /**
     * Validates Username in persistence.
     * @param username
     * @return true value if Username is available else returns false
     */
    boolean verifyUsername(String username);
    IResponse createUser(IUser user);

    List<Inquiry> downloadCurrentUserInquiries(UUID currentUserId, IUserBuilder builder);

    List<Inquiry> getInquriesByInquiryId(UUID id, IUserBuilder builder);

    List<Inquiry> getInquiresByCPR(String cpr, IUserBuilder builder);

    List<Inquiry> getInquiresByCitizenName(String name, IUserBuilder builder);

    IPersistanceUser login(String userName , String password);

    void injectInquiry(Inquiry inquiry);

    List<IPersistanceUser> getAllUsers(int page, int pageSize);

    /**
     * Deletes a specified user from the database
     * @param uuid, the id for the user to be deleted
     */
    void deleteById(UUID uuid);

    /**
     * Changes a users userName to a new specified one
     * @param user The users whos name should be changed
     * @param name the new name for the user
     */
    void changeUserName(IUser user, String name);

    /**
     * Changes a users accessright to a new specified one
     * @param user The users whos accessright is to be changed
     * @param accessright the new accessright for the user
     */
    void changeAccessRight(IUser user, int accessright);

    /**
     * Changes a specified users password and wether is should be classified as temporary
     * @param user the user whos password is to be changed
     * @param password The new password the user shall have
     * @param isTemporary Wether or not the new password should be specified as temporary
     */
    void changePassword(IUser user, String password, boolean isTemporary);

    void alterInquiry(Inquiry inquiry);
}
