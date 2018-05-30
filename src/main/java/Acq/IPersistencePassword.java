package Acq;

import java.time.LocalDateTime;

public interface IPersistencePassword {

    /**
     *
     * @return the password value of the password object
     */
    String getPassword();

    /**
     *
     * @return the expiration date of the password
     */

    LocalDateTime getExpirationDate();

    /**
     *
     * @return a boolean if the password is still temporary
     * true if it is still temporary
     * false if the password is not still a temporary
     */
    boolean getIsTemporary();
}
