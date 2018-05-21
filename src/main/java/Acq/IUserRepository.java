package Acq;

import Persistence.ResponseMessage;

import java.util.Collection;
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
    Collection<IPersistanceUser> getAllUsers(int page , int pageSize);


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



    IPersistanceUser login(String userName, String password);


}

