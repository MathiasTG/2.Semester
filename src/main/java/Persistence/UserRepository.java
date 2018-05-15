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
        StringBuilder stmB = new StringBuilder()
                .append("insert into user values ")
                .append(iUser.getID().toString()+", ")
                .append(iUser.getUsername()+", ")
                .append(iUser.getAccessRight()+";");
        String userStm=stmB.toString();
        stmB.delete(0,stmB.length()-1);
        stmB.append("insert into password values ")
                .append(iUser.getPassword().getPassword().toString()+", ")
                .append(iUser.getPassword().isTemporary().toString()+", ")
                .append(iUser.getPassword().getExpirationDate().toString(";"));
        String pswd=stmB.toString();
        stmB.delete(0,stmB.length()-1);
        
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
