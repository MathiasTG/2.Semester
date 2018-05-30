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

    /**
     * Injects the persistenceFacade to create a connection between the layers
     *
     * @param persistenceFacade an instance of the IPersistanceFacade
     */
    void injectPersistence(IPersistenceFacade persistenceFacade);

    /**
     *
     * createUser from parameters
     *
     * @param username the name of the new user, as a String value
     * @param accesRights as a range of int values determining the users access rights varying from 1-3 where 1 = Sekretær, 2 = Sagsbehandler, 3 = Admin
     * @return IResponse which indicates the status of the invocation
     */
    IResponse createUser(String username, int accesRights);

    /**
     *
     * log a currentuser into the system
     *
     * @param userName the name of the user
     * @param password the users password
     * @return IResponse to indicate the status of the invocation
     */
    IResponse logIn(String userName , String password);

    /**
     * Injects a DTO object of the inquiry type, to the domain facade
     * @param inquiry, the inquiry which transfers to the domain facade
     */
    void injectInquiry(Inquiry inquiry);


    /**
     *
     * @return the username of respective currentUser
     */
    String getCurrentUserName();

    /**
     *
     * @return the accessRight of the respective currentuser
     */
    int getCurrentUserAccessRights();

    /**
     *
     * Request all inquires with respective to the currentUser of the system.
     *
     * @return List<Inquiry></>.
     */
    List<Inquiry> downloadCurrentUserInquiries();

    /**
     *
     * Request all inquires with respect to the given parameter. The list is returned with one item if
     * such exists or as an empty list if none exists.
     *
     * @param id UUID of a given inquiry in the system
     * @return List<Inquiry></>.
     */
    List<Inquiry> getInquriesByInquiryId(UUID id);

    /**
     *
     * Request all inquiries with respect to the given parameter.
     *
     * @param cpr of a citizen in the system
     * @return
     */
    List<Inquiry> getInquiresByCPR(String cpr);

    /**
     *
     * Request all inquiries with respect to the given parameter.
     *
     * @param name of a citizen in the system
     * @return
     */
    List<Inquiry> getInquiresByCitizenName(String name);

    /**
     * set the value of the currentUser to null; Thus logging out the user
     */
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

    /**
     *
     * Returns the current instantiation of persistanceFacade, allowing the caller to exploit
     * functionality in the persistenceLayer.
     *
     * @return IPersistenceFacade
     */
    IPersistenceFacade getPersistence();
    
}
