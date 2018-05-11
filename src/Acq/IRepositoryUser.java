package Acq;

import java.util.Collection;
import java.util.UUID;

public interface IRepositoryUser {


    /**
     *
     * @param iUser
     */
    void createUser(IUser iUser);


    /**
     *
     * @param iUser
     * @return
     */
    boolean validateUser(IUser iUser);


    /**
     *
     * @param page
     * @param pageSize
     * @return
     */
    Collection<IUser> getAllUsers(int page , int pageSize);


    /**
     *
     * @param uuid
     * @return
     */
    IUser getById(UUID uuid);

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
    IUser login(String userName , String password);




}

