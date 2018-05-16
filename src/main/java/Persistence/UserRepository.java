package Persistence;

import Acq.IPersistanceUser;
import Acq.IRepositoryUser;
import Acq.ISaveableUser;
import Acq.IUser;

import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

public class UserRepository extends AbstractRepository implements IRepositoryUser {





    public UserRepository() throws SQLException {
        super();
    }

    @Override
    public ResponseMessage createUser(ISaveableUser iUser) {
        StringBuilder stmB = new StringBuilder();

        stmB.append("insert into password values ")
                .append(iUser.getPassword().getPassword().toString()+", ")
                .append(iUser.getPassword().getIsTemporary()+", ")
                .append(iUser.getPassword().getExpirationDate().toString()+";");
        String pswd=stmB.toString();
        stmB.delete(0,stmB.length()-1);

        stmB.append("insert into user values ")
                .append(iUser.getID().toString()+", ")
                .append(iUser.getUsername()+", ")
                .append(iUser.getPassword().getPassword()+", ")
                .append(iUser.getAccessRight()+";");
        String userStm=stmB.toString();

        executeUpdate(pswd,userStm);
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
    public IPersistanceUser login(String userName, String password) {
        return null;
    }
}
