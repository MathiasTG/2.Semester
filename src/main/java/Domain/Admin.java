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
public class Admin extends User {

    private static final int ACCESRIGHT = 3;

    public Admin(String username, Password password) {
        super(username, ACCESRIGHT, password);
    }
    public Admin(UUID id, String username) {
        super(id, username, ACCESRIGHT);
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
        return super.password.toString();
    }

    @Override
    public int getAccessRight() {
        return super.accessRight;
    }
    
}
