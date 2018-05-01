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
public interface IUser {
    UUID getID();
    String getUsername();
    String getPassword();
    int getAccessRight();
    
    
}
