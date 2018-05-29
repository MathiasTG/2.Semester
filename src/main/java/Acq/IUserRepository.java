package Acq;

import Persistence.ResponseMessage;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IUserRepository {


    /**
     *
     * @param iUser
     */
    ResponseMessage createUser(IUser iUser);


    /**
     *
     * @param username
     * @return
     */
    boolean validateUsername(String username);


    /**
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<IPersistanceUser> getAllUsers(int page , int pageSize);


    /**
     *
     * @param uuid
     * @return
     */
    IPersistanceUser getById(UUID uuid);

    /**
     * Deletes the chosen user from the database
     * @param uuid, used to identify the user
     */
    void deleteById(UUID uuid);


    /**
     *
     * @param userName
     * @param password
     * @return
     */

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

