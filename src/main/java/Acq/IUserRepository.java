package Acq;

import Persistence.ResponseMessage;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IUserRepository {


    /**
     * Creates a user
     *
     * @param iUser representing a IUser object
     * @return returns a response message saying if the method was succesful or not
     */
    ResponseMessage createUser(IUser iUser);


    /**
     * Validates a user by the username
     * @param username String of username to validate
     * @return true if the user validates succesful or false if not
     */
    boolean validateUsername(String username);


    /**
     *
     * NOTE - all params must be over 0
     * @param page Starting page of the list of the user
     * @param pageSize Ending size of the userlist
     * @return A sepcified length of a list with IPersistanceusers
     */
    List<IPersistanceUser> getAllUsers(int page , int pageSize);


    /**
     *
     * @param uuid the desired ID to search for the user
     * @return a single user that match the UUID or null if nothing matches
     */
    IPersistanceUser getById(UUID uuid);

    /**
     * Deletes the chosen user from the database
     * @param uuid, used to identify the user
     */
    void deleteById(UUID uuid);



    /**
     * Changes the password of the user that is given as a parameter, in the database
     * @param user, the user who´s password will be changed
     * @param password, the new password for the user
     * @param isTemporary, whether or not this password should be temporary
     */
    void changePassword(IUser user, String password, boolean isTemporary);

    IPersistanceUser login(String userName, String password);

    /**
     * Changes the user name, of the user that is given as a parameter, in the database
     * @param user, the user who´s username will be changed
     * @param name, the new name that the users name will be changed to
     */
    void changeUserName(IUser user, String name);

    /**
     * Changes the accessright, og the user that is given as a parameter, in the database
     * @param user, the user who´s accessright will be changed
     * @param accessright, the new accessright that the users accessright will be changed to
     */
    void changeAccessRight(IUser user, int accessright);
}

