/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Acq.ISaveablePassword;

import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.round;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.security.SecureRandom;
import java.util.UUID;

/**
 *
 * @author ulriksandberg
 */
public class Password implements ISaveablePassword {
    
    private String password;
    private Date createdDate;
    private Date experationDate;
    private boolean isTemperary;
    
    public Password()
    {
        this.isTemperary = isTemperary;
        generateTempPassword();
        createPasswordDates();    
    }
    
    public Password(String password)
    {
        this.password = password;
        createPasswordDates();
    }
    
    private void generateTempPassword()
    {
        UUID uuIdPassword = UUID.randomUUID();
        String passString = uuIdPassword.toString();
        passString = passString.replace("-", "");
        
        password = passString.substring(0, 10);
        System.out.println(password);
    }
    
    private void createPasswordDates()
    {
        
        
        
    }

    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getExpirationDate() {
        return experationDate;
    }

    @Override
    public boolean getIsTemporary() {
        return false;
    }


}
