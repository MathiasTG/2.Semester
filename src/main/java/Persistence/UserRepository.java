package Persistence;

import Acq.IPersistanceUser;
import Acq.IPersistencePassword;
import Acq.IUserRepository;
import Acq.IUser;
import Domain.Response;
import Persistence.PersistenceModels.PersistencePassword;
import Persistence.PersistenceModels.PersistenceUser;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public class UserRepository extends AbstractRepository implements IUserRepository {



    public UserRepository() {
        super();
    }


    @Override
    public ResponseMessage createUser(IUser iUser) {

        int myInt = 0;
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

        try
        {
            Statement p = conn.createStatement();

            int a = p.executeUpdate(passBuilder.toString());

            if(a == 0)
            {
                return new ResponseMessage(null, ResponseCode.EMPTY_REQUEST);
            }

            Statement p1 = conn.createStatement();

            int b = p1.executeUpdate(userBuilder.toString());


            return new ResponseMessage(null, ResponseCode.SUCCESS);

        } catch (SQLException ex) {
            ex.printStackTrace();

            return new ResponseMessage(null, ResponseCode.INVALID_SQL);
        }


    }

    @Override
    public boolean validateUsername(String username) {
        return true;
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


        StringBuilder loginQuery = new StringBuilder();


        //Select * From
        //(Select * from users, password WHERE users.PasswordId = password.passId) as b
        //WHERE b.name = 'Adam' AND b.Password = '15da66cfac'

        loginQuery.append("Select * From ");
        loginQuery.append("(Select * from users, password WHERE users.PasswordId = password.passId) as b ");
        loginQuery.append("WHERE b.name = " + "'" + userName + "'" + " AND b.Password = " + "'" + password + "'");

        try {

            Statement query = conn.createStatement();

            ResultSet result = query.executeQuery(loginQuery.toString());

            IPersistencePassword pass = null;
            IPersistanceUser user = null;

            while(result.next())
            {
                System.out.println(result.getString(1) + "\t");
                System.out.println(result.getString(2) + "\t");
                System.out.println(result.getString(3) + "\t");
                System.out.println(result.getString(4) + "\t");
                System.out.println(result.getString(5) + "\t");
                System.out.println(result.getString(6) + "\t");
                System.out.println(result.getString(7) + "\t");
                System.out.println(result.getString(8) + "\t");

                pass = new PersistencePassword(result.getString(6), LocalDateTime.now(), LocalDateTime.now(), true);
                user = new PersistenceUser(UUID.fromString(result.getString(1)), result.getString(2), Integer.parseInt(result.getString(4)), pass);

            }

            //pass = new PersistencePassword(result.getString(6), LocalDateTime.now(), LocalDateTime.now(), true);
            //user = new PersistenceUser(UUID.fromString(result.getString(1)), result.getString(2), Integer.parseInt(result.getString(4)), pass);

            return user;

        } catch (SQLException ex) {

            ex.printStackTrace();
            return null;
        }


    }
}
