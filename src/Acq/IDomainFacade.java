/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acq;

/**
 *
 * @author ulriksandberg
 */
public interface IDomainFacade {
    
    void injectPersistence(IPersistenceFacade persistenceFacade);
    IResponse createUser(String username, int accesRights);
    
}
