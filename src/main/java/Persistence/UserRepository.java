package Persistence;

import Acq.IRepositoryUser;
import Acq.IUser;

import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

public class UserRepository extends AbstractRepository implements IRepositoryUser {
    public UserRepository() throws SQLException {
        super();
    }

    @Override
    public ResponseMessage createUser(IUser iUser) {

        return super.executeStm("Our statement");

    }

    @Override
    public boolean validateUsername(String username) {
        return false;
    }

    @Override
    public Collection<IUser> getAllUsers(int page, int pageSize) {
        return null;
    }

    @Override
    public IUser getById(UUID uuid) {
        return null;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public IUser login(String userName, String password) {
        return null;
    }
}
