package Acq;

import java.util.UUID;

public interface ISaveableUser {
    UUID getID();
    String getUsername();
    String getPassword();
    int getAccessRight();
}
