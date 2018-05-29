package Acq;

import java.util.UUID;

public interface IPersistanceUser {


    UUID getID();
    String getUsername();

    int getAccessRight();
}
