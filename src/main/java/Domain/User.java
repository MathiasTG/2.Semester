/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;


import Acq.IUser;
import java.util.UUID;

/**
 *
 * @author ulriksandberg
 */
public abstract class User implements IUser {
    
    protected UUID id;
    protected String username;
    protected Password password;
    int accessRight;
    
    
    
    public User(String username, int accessRight, Password password)
    {
        this.id = UUID.randomUUID();
        this.username = username;
        this.accessRight = accessRight;
        this.password = password;
        
    }
    
}
