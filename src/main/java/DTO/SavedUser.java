package DTO;

import Acq.ISaveableUser;

import java.util.UUID;

public class SavedUser implements ISaveableUser {

    private UUID id;
    private String username;
    private String password;
    private int accessRight;

    public SavedUser(UUID id, String username, String password, int accessRight) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accessRight = accessRight;
    }

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getAccessRight() {
        return accessRight;
    }

}
