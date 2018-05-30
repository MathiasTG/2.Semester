package Acq;

import java.util.UUID;

public interface IUserBuilder extends IBuilder<IUser>{

    /**
     *
     * @param uuid the uuid to set
     * @return an builded IUser with the setted ID
     */
    IUserBuilder setID(UUID uuid);

    /**
     *
     * @param username the username to set
     * @return a builded user with the username
     */
    IUserBuilder setUsername(String username);

    /**
     *
     * @param password the passord to set
     * @return the builded user with the password
     */
    IUserBuilder setPassword(String password);

    /**
     *
     * @param right the accesright to set
     * @return the builded user with the accesright
     */
    IUserBuilder setAccessRight(int right);

}
