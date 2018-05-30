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
     * Validates Username in persistence
     * @param username String value representation
     * @return boolean value indicating if the username already exists.
     */
    boolean verifyUsername(String username);

    /**
     *
     * Saves and IUser in the database
     *
     * @param user IUser which should be saved in the database
     * @return IResponse indicating the status of the method createUser
     */
    IResponse createUser(IUser user);

    /**
     *
     * Download all inquiries related to the respective currentUserId
     *
     * @param currentUserId UUID representing the currentUserId
     * @param builder IUserBuilder
     * @return List<Inquiry></>
     */
    List<Inquiry> downloadCurrentUserInquiries(UUID currentUserId, IUserBuilder builder);

    /**
     *
     * Download all inquires respective to the unique inquiryId from the database.
     * If none exist and empty list is returned, else a list carrying on item is returned
     *
     * @param id UUID of the respective inquiry
     * @param builder IUserBuilder
     * @return List<Inquiry></>
     */
    List<Inquiry> getInquriesByInquiryId(UUID id, IUserBuilder builder);

    /**
     *
     * Download all inquiries that matches a citizens cpr-number in the database.
     *
     * @param cpr of the citizen to be found
     * @param builder IUserBuilder
     * @return List<Inquiry></>
     */
    List<Inquiry> getInquiresByCPR(String cpr, IUserBuilder builder);

    /**
     *
     * Download all inquiries in the database that matches a citizens name.
     *
     * @param name of the citizen to be found
     * @param builder IUserBuilder
     * @return List<Inquiry></>
     */
    List<Inquiry> getInquiresByCitizenName(String name, IUserBuilder builder);

    /**
     *
     * Verify that the login criteria has a match in the system. If such exists return the
     * user matching the criteria.
     *
     * @param userName String value representing username
     * @param password String value respresenting users password
     * @return IPersistanceUser if login was successfull or null if login failed
     */
    IPersistanceUser login(String userName , String password);

    /**
     *
     * Call to create an inquiry in the system
     *
     * @param inquiry to be saved in the system
     */
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

    /**
     *
     * 
     *
     * @param inquiry to be updated
     */
    void alterInquiry(Inquiry inquiry);
}
