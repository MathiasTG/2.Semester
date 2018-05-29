package Acq;

import java.util.UUID;

public interface IUserBuilder extends IBuilder<IUser>{
    IUserBuilder setID(UUID uuid);
    IUserBuilder setUsername(String username);
    IUserBuilder setPassword(String password);
    IUserBuilder setAccessRight(int right);

}
