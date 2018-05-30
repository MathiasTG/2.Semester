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
public interface IUI {

    /**
     *  This method injects the domain layer to the UI layer
     * @param domainFacade The domain facade to inject
     */
    void injectDomain(IDomainFacade domainFacade);

    /**
     *
     * Starting the application
     * @param args Commandline arguments
     */
    void startApplication(String[] args);
}
