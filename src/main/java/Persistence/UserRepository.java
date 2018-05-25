package Persistence;

import Acq.IPersistanceUser;
import Acq.IPersistencePassword;
import Acq.IUserRepository;
import Acq.IUser;
import Persistence.PersistenceModels.PersistencePassword;
import Persistence.PersistenceModels.PersistenceUser;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository extends AbstractRepository implements IUserRepository {

    @Override
    public ResponseMessage createUser(IUser iUser) {

        UUID passId = UUID.randomUUID();

        String basePassStm="insert into password values (?,?,?,?);";

        String baseUser ="insert into users values (?,?,?,?);";

        try(Connection conn = startConnection();
                PreparedStatement pass=conn.prepareStatement(basePassStm);
                PreparedStatement user=conn.prepareStatement(baseUser))
        {
            pass.setString(1,passId.toString());
            pass.setString(2,iUser.getPassword());
            pass.setBoolean(3,true);
            pass.setString(4,LocalDateTime.now().toString());

            user.setString(1,iUser.getID().toString());
            user.setString(2,iUser.getUsername());
            user.setString(3,passId.toString());
            user.setInt(4,iUser.getAccessRight());

            return new ResponseMessage(null,executeUpdate(pass,user));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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

        try(Connection conn = startConnection()) {
            PreparedStatement statement =conn.prepareStatement(query.toString());
            ResponseMessage r = executeStm(statement);
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
            while(set.next())
                if(set.getString(1).equals(username))
                    return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public List<IPersistanceUser> getAllUsers(int page, int pageSize) {
        List<IPersistanceUser> list = new ArrayList<>();
        int from = (pageSize*page)-(pageSize);
        int to = (pageSize*page)-1;

        StringBuilder query = new StringBuilder();
        query.append("Select u.id,u.name,u.accessright,p.passid,p.password,p.istemporary,p.expirationdate ")
                .append("from users u left join password p on u.passwordid = p.passid ")
                .append("order by u.name ")
                .append("limit "+to)
                .append(" offset "+from+";");

        try(Connection conn = startConnection()) {
            PreparedStatement statement = conn.prepareStatement(query.toString());
            ResponseMessage responseMessage = executeStm(statement);
            ResultSet resultSet = responseMessage.getData();
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
                .append("WHERE u.id = ?;");

        try(Connection conn = startConnection();
            PreparedStatement statement = conn.prepareStatement(query.toString())){

            statement.setString(1,uuid.toString());

            try(ResultSet resultSet = executeStm(statement).getData()){
                resultSet.next();
                return new PersistenceUser(
                        UUID.fromString(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        new PersistencePassword(resultSet.getString(5),
                                resultSet.getTimestamp(7).toLocalDateTime(),
                                resultSet.getBoolean(6)));
            }
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
                .append("WHERE u.id = ?;");
        try(Connection conn = startConnection();
            PreparedStatement statement = conn.prepareStatement(query.toString())){
            statement.setString(1,uuid.toString());
            try(ResultSet res= executeStm(statement).getData()) {
                res.next();
                String passid = res.getString(1);
                String passdelete = "delete from password where passid=?;";
                String userdelete = "delete from users where id=?;";
                try (PreparedStatement passDel = conn.prepareStatement(passdelete);
                     PreparedStatement userDel = conn.prepareStatement(userdelete)) {
                    passDel.setString(1, passid);
                    userDel.setString(1, uuid.toString());
                    executeUpdate(passDel,userDel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeUserName(IUser user, String name){
        String updateName = "UPDATE users SET name=? WHERE name=?;";
        try(Connection conn = startConnection();
            PreparedStatement statement = conn.prepareStatement(updateName)) {
            statement.setString(1,name);
            statement.setString(2,user.getUsername());
            executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeAccessRight(IUser user, int accessright){

        String updateAccess = "UPDATE users SET accessright=? WHERE name=?;";
        try(Connection conn = startConnection();
            PreparedStatement statement = conn.prepareStatement(updateAccess)) {
            statement.setInt(1,accessright);
            statement.setString(2,user.getUsername());
            executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void changePassword(IUser user, String password, boolean isTemporary){
        StringBuilder query = new StringBuilder();
        query.append("Select p.passid ")
                .append("from users u left join password p on u.passwordid = p.passid ")
                .append("WHERE u.id =?;");
        try(Connection conn = startConnection();
            PreparedStatement statement = conn.prepareStatement(query.toString())){
            statement.setString(1, user.getID().toString());

            try(ResultSet resultSet = executeStm(statement).getData()) {
                resultSet.next();
                String passid = resultSet.getString(1);
                String updatePassword = "UPDATE password SET password =?, istemporary = WHERE passid = ?;";

                try(PreparedStatement upstatement = conn.prepareStatement(updatePassword)){
                    upstatement.setString(1,password);
                    upstatement.setBoolean(2,isTemporary);
                    upstatement.setString(3,passid);
                    executeUpdate(upstatement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public IPersistanceUser login(String userName, String password) {
        StringBuilder loginQuery = new StringBuilder();

        loginQuery.append("Select u.id,u.name,u.accessright,p.passid,p.password,p.istemporary,p.expirationdate ");
        loginQuery.append("from users u left join password p on u.passwordid = p.passid ");
        loginQuery.append("WHERE u.name = ? AND p.password = ?;");

        try(Connection conn = startConnection();
            PreparedStatement statement = conn.prepareStatement(loginQuery.toString())) {
            statement.setString(1,userName);
            statement.setString(2,password);

            try(ResultSet result = executeStm(statement).getData()){
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
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
