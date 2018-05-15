package Acq;

import java.util.UUID;

public interface ISaveableUser {
    UUID getID();
    String getUsername();
    ISaveablePassword getPassword();
    int getAccessRight();
}
