package Acq;

import java.time.LocalDateTime;

public interface IPersistencePassword {

    String getPassword();
    LocalDateTime getExpirationDate();
    boolean getIsTemporary();
}
