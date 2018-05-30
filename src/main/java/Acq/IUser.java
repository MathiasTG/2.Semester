/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acq;

import java.util.UUID;

/**
 *
 * @author ulriksandberg
 */
public interface IUser{
    /**
     *
     * @return The UUID from the user
     */
    UUID getID();

    /**
     *
     * @return The username from the user
     */
    String getUsername();

    /**
     *
     * @return the password from the user
     */
    String getPassword();

    /**
     *
     * @return the accesright from the user
     */
    int getAccessRight();

    /**
     *
     * @return the UUID's tostring
     */
    String getStringID();

    /**
     *
     * @param name the name to set the user
     */
    void setName(String name);

    /**
     *
     * @param accessRight the accesright to set the user
     */
    void setAccessRight(int accessRight);

    
}
