/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.IDomainFacade;
import Acq.IPersistenceFacade;
import Acq.IResponse;
import Acq.IUser;

/**
 * @author ulriksandberg
 */
public class DomainController {

    private DomainFacade domainFacade;

    public DomainController() {
        domainFacade = DomainFacade.getInstance();
    }

    public IResponse createUser(String username, int accessright) {

        if (domainFacade.getPersistenceFacade().verifyUsername(username)) {

            String type = null;
            IUser user = null;

            switch (accessright) {
                case 1:
                    user = new Secretary(username, accessright, new Password());
                    type = "Sekretær";
                    break;
                case 2:
                    user = new Caseworker(username,accessright, new Password());
                    type = "Sagsbehandler";
                    break;
                case 3:
                    user = new Admin(username, accessright, new Password());
                    type = "Admin";
                    break;
            }

            domainFacade.getPersistenceFacade().createUser(user);
            return new Response(true, user.getPassword());
        }
        return new Response(false, "Brugernavn er allerede i brug");
    }
}
