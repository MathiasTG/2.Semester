package Persistence;

import Acq.IPersistanceUser;
import Acq.IPersistencePassword;
import Acq.IUserRepository;
import Acq.IUser;
import Persistence.PersistenceModels.PersistencePassword;
import Persistence.PersistenceModels.PersistenceUser;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserRepository extends AbstractRepository implements IUserRepository {

    @Override
    public ResponseMessage createUser(IUser iUser) {

        UUID passId = UUID.randomUUID();

        StringBuilder passBuilder = new StringBuilder();
        passBuilder.append("insert into password values ('");
        passBuilder.append(passId.toString() + "', '");
        passBuilder.append(iUser.getPassword() + "', ");
        passBuilder.append(true + ", '");
        passBuilder.append(LocalDateTime.now().toString() + "' );");

        StringBuilder userBuilder = new StringBuilder();
        userBuilder.append("insert into users values ('");
        userBuilder.append(iUser.getID().toString() + "', '");
        userBuilder.append(iUser.getUsername() + "', '");
        userBuilder.append(passId.toString() + "', ");
        userBuilder.append(iUser.getAccessRight() + ")");

        ResponseCode r = executeUpdate(passBuilder.toString(),userBuilder.toString());
        return new ResponseMessage(null,r);


    }

    /**
     * returns true if username is available,
     * false if username is taken or an unexpected error happens.
     * @param username
     * @return validity
     */
    @Override
    public boolean validateUsername(String username) {
        StringBuilder query = new StringBuilder();
        query.append("Select name from users;");
        ResponseMessage r = executeStm(query.toString());
        switch(r.getResponseCode()){
            case SERVER_UNREACHABLE:
                return false;
            case INVALID_SQL:
                return false;
            case REJECTED:
                return false;
            case EXECUTION_TIMEOUT:
                return false;
        }
        ResultSet set = r.getData();
        try{
            while(set.next())
                if(set.getString(1).equals(username))
                    return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Collection<IPersistanceUser> getAllUsers(int page, int pageSize) {
        List<IPersistanceUser> list = new ArrayList<>();
        int from = (pageSize*page)-(pageSize);
        int to = (pageSize*page)-1;

        StringBuilder query = new StringBuilder();
        query.append("Select u.id,u.name,u.accessright,p.passid,p.password,p.istemporary,p.expirationdate ")
                .append("from users u left join password p on u.passwordid = p.passid ")
                .append("order by u.name ")
                .append("limit "+to)
                .append(" offset "+from+";");

        ResponseMessage responseMessage = executeStm(query.toString());
        ResultSet resultSet = responseMessage.getData();

        try {
            while (resultSet.next()) {
                list.add(
                        new PersistenceUser(
                                UUID.fromString(resultSet.getString(1)),
                                resultSet.getString(2),
                                Integer.parseInt(resultSet.getString(3)),
                                new PersistencePassword(resultSet.getString(5),
                                        resultSet.getTimestamp(7).toLocalDateTime(),
                                        resultSet.getBoolean(6)))
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public IPersistanceUser getById(UUID uuid) {
        StringBuilder query = new StringBuilder();
        query.append("Select u.id,u.name,u.accessright,p.passid,p.password,p.istemporary,p.expirationdate ")
                .append("from users u left join password p on u.passwordid = p.passid ")
                .append("WHERE u.id = '" + uuid.toString()+"';");

        ResponseMessage responseMessage = executeStm(query.toString());
        ResultSet resultSet = responseMessage.getData();

        try {
            resultSet.next();
            return new PersistenceUser(
                    UUID.fromString(resultSet.getString(1)),
                    resultSet.getString(2),
                    Integer.parseInt(resultSet.getString(3)),
                    new PersistencePassword(resultSet.getString(5),
                            resultSet.getTimestamp(7).toLocalDateTime(),
                            resultSet.getBoolean(6)));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return  null;
        }
    }

    @Override
    public void deleteById(UUID uuid) {
        StringBuilder query = new StringBuilder();
        query.append("Select p.passid ")
                .append("from users u left join password p on u.passwordid = p.passid ")
                .append("WHERE u.id = '" + uuid.toString()+"';");
        try {
            ResultSet res= executeStm(query.toString()).getData();
            res.next();
            String passid=res.getString(1);
            String passdelete="delete from password where passid='"+passid+"';";
            String userdelete ="delete from users where id='"+uuid+"';";
            executeUpdate(userdelete,passdelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public IPersistanceUser login(String userName, String password) {


        StringBuilder loginQuery = new StringBuilder();

        //Select u.id,u.name,u.accessright,p.passid,p.password,p.istemporary,p.expirationdate
        // from users u left join password p on u.passwordid = p.passid
        // WHERE u.name = 'Adam' AND p.password = '15da66cfac';

        loginQuery.append("Select u.id,u.name,u.accessright,p.passid,p.password,p.istemporary,p.expirationdate ");
        loginQuery.append("from users u left join password p on u.passwordid = p.passid ");
        loginQuery.append("WHERE u.name = " + "'" + userName + "'" + " AND p.Password = " + "'" + password + "';");

        try {
            ResultSet result = executeStm(loginQuery.toString()).getData();
            IPersistencePassword pass;
            IPersistanceUser user;
            result.next();
            pass = new PersistencePassword(
                    result.getString(5),
                    result.getTimestamp(7).toLocalDateTime(),
                    result.getBoolean(6));

            user = new PersistenceUser(
                    UUID.fromString(result.getString(1)),
                    result.getString(2),
                    Integer.parseInt(result.getString(3)),
                    pass);
            return user;

        } catch (SQLException ex) {

            ex.printStackTrace();
            return null;
        }
    }
}
