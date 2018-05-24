package Domain;

import Acq.IUser;
import Acq.IUserBuilder;

import java.util.UUID;

public class UserBuilder implements IUserBuilder {

    private UUID id;
    private String username;
    private Password password;
    private int accesssRight;

    @Override
    public IUserBuilder setID(UUID uuid) {
        this.id = uuid;
        return this;
    }

    @Override
    public IUserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public IUserBuilder setPassword(String password) {
        this.password = new Password(password);
        return this;
    }

    @Override
    public IUserBuilder setAccessRight(int right) {
        this.accesssRight = right;
        return this;
    }

    @Override
    public IUser build() {
        IUser user = null;

        switch (accesssRight){
            case 1:
                user = new Secretary(id, username, accesssRight);
                break;
            case 2:
                user = new Caseworker(id, username, accesssRight);
                break;
            case 3:
                user = new Admin(username, password);
        }


        return user;
    }
}
