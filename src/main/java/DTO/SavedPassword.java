package DTO;

import Acq.ISaveablePassword;

import java.time.LocalDateTime;

public class SavedPassword implements ISaveablePassword {

    private String password;
    private LocalDateTime experationDate;
    private boolean isTemporary;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getExpirationDate() {
        return experationDate;
    }

    @Override
    public boolean getIsTemporary() {
        return isTemporary;
    }
}
