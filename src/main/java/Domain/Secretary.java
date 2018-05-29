/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.UUID;

/**
 *
 * @author ulriksandberg
 */
public class Secretary extends User {

    public Secretary(String username, int accessRight, Password password) {
        super(username, accessRight, password);
    }

    public Secretary(UUID id, String username, int accesRight) {
        super(id, username, accesRight);
    }

    @Override
    public UUID getID() {
        return super.id;
    }

    @Override
    public String getUsername() {
        return super.username;
    }

    @Override
    public String getPassword() {
        return password.getPassword();
    }

    public int getAccessRight() {
        return super.accessRight;
    }

    
    

    

        
}
