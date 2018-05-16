/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acq;

import DTO.Inquiry;

/**
 *
 * @author ulriksandberg
 */
public interface IPersistenceFacade {
    
    boolean verifyUsername(String username);
    IResponse createUser(IUser user);

    IPersistanceUser login(String userName , String password);

    void injectInquiry(Inquiry inquiry);
    
    
}
