package Persistence.PersistenceModels;



import Acq.*;

import java.util.UUID;

public class PersistenceUser implements IPersistanceUser {



    private UUID id;
    private String username;
    private int accessRight;
    private IPersistencePassword persistencePassword;


    public PersistenceUser(UUID id, String username, int accessRight , IPersistencePassword persistencePassword) {
        this.id = id;
        this.username = username;
        this.accessRight = accessRight;
        this.persistencePassword = persistencePassword;
    }

    public UUID getId() {
        return id;
    }


    @Override
    public UUID getID() {
        return this.id;
    } 

    public String getUsername() {
        return this.username;
    }

    public int getAccessRight() {
        return this.accessRight;
    }

    public IPersistencePassword getPersistencePassword() {
        return persistencePassword;
    }
}
