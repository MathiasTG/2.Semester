package DTO;

import Acq.ISaveablePassword;
import Acq.ISaveableUser;

import java.util.UUID;

public class SavedUser implements ISaveableUser {

    private UUID id;
    private String username;
    private ISaveablePassword password;
    private int accessRight;

    public SavedUser(UUID id, String username, ISaveablePassword password, int accessRight) {
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
    public ISaveablePassword getPassword() {
        return password;
    }

    @Override
    public int getAccessRight() {
        return accessRight;
    }

}
