package Domain;

import Acq.IPersistanceUser;
import Acq.IUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataManager {

    private List<IUser> users;

    public DataManager(){
        this.users = new ArrayList<>();
    }

    /*
    Takes a list of IPersistenUsers and outputs a list of IUsers. The two types has the same
    data, but slightly different methods, so a direct casting is not possible
     */
    public List<IUser> revertIPUserToIUser(List<IPersistanceUser> IPUser) {
        for (int i = 0; i < IPUser.size(); i++) {
            switch (IPUser.get(i).getAccessRight()) {  //If the user is a secretary, accessright = 1, if case worker, accessright = 2, if admin, accesright = 3
                case 1:
                    this.users.add(new Secretary(IPUser.get(i).getID(), IPUser.get(i).getUsername(), IPUser.get(i).getAccessRight()));
                    break;
                case 2:
                    this.users.add(new Caseworker(IPUser.get(i).getID(), IPUser.get(i).getUsername(), IPUser.get(i).getAccessRight()));
                    break;
                case 3:
                    this.users.add(new Admin(IPUser.get(i).getID(), IPUser.get(i).getUsername()));
                    break;
                default:
                    this.users.add(null); //If it somehow has a different accessright than the allowed, a null value is added
                    break;
            }
        }
        return this.users;
    }
}