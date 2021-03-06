package Persistence.PersistenceModels;

import Acq.IPersistencePassword;

import java.time.LocalDateTime;

public class PersistencePassword implements IPersistencePassword {



    private String password;
    private LocalDateTime experationDate;
    private boolean isTemperary;


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getExpirationDate() {
        return this.experationDate;
    }

    @Override
    public boolean getIsTemporary() {
        return this.isTemperary;
    }



    public PersistencePassword(String password, LocalDateTime experationDate, boolean isTemperary) {

        this.password = password;
        this.experationDate = experationDate;
        this.isTemperary = isTemperary;
    }
}
