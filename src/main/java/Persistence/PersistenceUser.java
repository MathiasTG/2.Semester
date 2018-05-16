package Persistence;



import Acq.IPersistanceUser;

import java.util.UUID;

public class PersistenceUser implements IPersistanceUser {



    protected UUID id;
    protected String username;
    int accessRight;


    public PersistenceUser(UUID id, String username, int accessRight) {
        this.id = id;
        this.username = username;
        this.accessRight = accessRight;
    }

    public UUID getId() {
        return id;
    }


    @Override
    public UUID getID() {
        return this.id;
    } s

    public String getUsername() {
        return this.username;
    }

    public int getAccessRight() {
        return this.accessRight;
    }

}
