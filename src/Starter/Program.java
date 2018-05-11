/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Starter;
import Acq.*;
import DTO.Citizen;
import DTO.Inquiry;
import Domain.DomainFacade;
import Persistence.PersistenceFacade;
import UI.UI;
/**
 *
 * @author ulriksandberg
 */
public class Program {
    
    
    public static void main(String[] args) {
        
        IUI uiFacade = new UI();
        IDomainFacade domainFacade = new DomainFacade();
        IPersistenceFacade persistenceFacade = new PersistenceFacade();
        
        domainFacade.injectPersistence(persistenceFacade);
        uiFacade.injectDomain(domainFacade);
        
        System.out.println("System ready to launch, beeeb boob, beeeeeeb, boob bub");
        uiFacade.startApplication(args);

    }
    
    
}
