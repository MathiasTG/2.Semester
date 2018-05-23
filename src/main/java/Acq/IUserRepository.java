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
     *
     * @param uuid
     */
    void deleteById(UUID uuid);


    /**
     *
     * @param userName
     * @param password
     * @return
     */

    void changePassword(IUser user, String password, boolean isTemporary);

    IPersistanceUser login(String userName, String password);


    void changeUserName(IUser user, String name);

    void changeAccessRight(IUser user, int accessright);
}

