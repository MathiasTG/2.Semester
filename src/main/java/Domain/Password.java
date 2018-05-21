/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author ulriksandberg
 */
public class Password  {
    
    private String password;
    private LocalDateTime experationDate;
    private boolean isTemperary;
    
    public Password()
    {
        this.isTemperary = true;
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

        // Sets created dste to current date and time

        LocalDateTime experationDate = LocalDateTime.now();

        // sets expiration date to currentdate plus x days
        this.experationDate = experationDate.plusDays(1);
        
    }

    public String getPassword() {
        return password;
    }


    public LocalDateTime getExpirationDate() {
        return experationDate;
    }


    public boolean getIsTemporary() {
        return isTemperary;
    }


}
