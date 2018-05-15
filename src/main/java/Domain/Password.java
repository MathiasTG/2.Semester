/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

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
public class Password {
    
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime experationDate;
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

        LocalDateTime currentDate = LocalDateTime.now();

        // Sets created dste to current date and time
        this.createdDate = currentDate;

        LocalDateTime experationDate = LocalDateTime.now();

        // sets expiration date to currentdate plus x days
        this.experationDate = experationDate.plusDays(1);
        
    }

    public String getPassword() {
        return password;
    }
    
    
}
