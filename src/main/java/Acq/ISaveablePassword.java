package Acq;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ISaveablePassword {

    String getPassword();
    LocalDateTime getExpirationDate();
    boolean getIsTemporary();


}
