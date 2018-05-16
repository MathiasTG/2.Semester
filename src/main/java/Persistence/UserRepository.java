package Persistence;

import Acq.IPersistanceUser;
import Acq.IPersistencePassword;
import Acq.IUserRepository;
import Acq.IUser;
import Persistence.PersistenceModels.PersistencePassword;
import Persistence.PersistenceModels.PersistenceUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class UserRepository extends AbstractRepository implements IUserRepository {


    private ArrayList<PersistenceUser> users = null;


    public UserRepository() {
        super();
        populateUsers();
    }

    private void populateUsers()
    {
        users = new ArrayList<>();

        IPersistencePassword pas1 = new PersistencePassword("1234" ,  LocalDateTime.now(), LocalDateTime.now().plusDays(1) , true );


        IPersistencePassword pas2 = new PersistencePassword("4567" ,  LocalDateTime.now(), LocalDateTime.now().plusDays(1) , true );
        PersistenceUser user1 = new PersistenceUser(UUID.randomUUID(), "Casper", 2 , pas1);
        PersistenceUser user2 = new PersistenceUser(UUID.randomUUID(), "Ulrik", 2, pas2) ;
        PersistenceUser user3 = new PersistenceUser(UUID.randomUUID(), "Gitte", 1, pas1);

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @Override
    public ResponseMessage createUser(IUser iUser) {

        int myInt = 0;
       /*
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
        */
        return super.executeStm("Our statement");

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




        for(PersistenceUser user : users)
        {
            if(user.getUsername().equals(userName) &&  user.getPersistencePassword().getPassword().equals(password))
            {
                IPersistanceUser user1 = new PersistenceUser(user.getID() , user.getUsername() , user.getAccessRight() , user.getPersistencePassword());

                return user1;
            }
        }

        return null;

    }
}
