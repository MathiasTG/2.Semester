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
public class Caseworker extends User {

    public Caseworker(String username, int accessRight, Password password) {
        super(username, accessRight, password);
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

    @Override
    public int getAccessRight() {
        return super.accessRight;
    }
    
}
