/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

/**
 *
 * @author ulriksandberg
 */
public class Configuration implements IConfiguration {

    String serverUrl = "jdbc:postgresql://horton.elephantsql.com:5432/kkkjzdmr";
    String username = "kkkjzdmr";
    String password ="hqspkZQtqwtMEw-tjfL1RFz8xQznjQQQ";
    

    public String getServerUrl() {
        return serverUrl;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
