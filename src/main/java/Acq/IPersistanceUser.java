package Acq;

import java.util.UUID;

public interface IPersistanceUser {


    /**
     *
     * The storage users id
     *
     * @return UUID
     */
    UUID getID();

    /**
     *
     * The storage users name
     *
     * @return String
     */
    String getUsername();

    /**
     *
     * The storage users accessRight
     *
     * @return int
     */
    int getAccessRight();
}
