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

    String serverUrl = "jdbc:postgresql://myDatabase.db.elephantSQL.902379238yri23u.key=??10394923hi";
    
    

    public String getServerUrl() {
        return serverUrl;
    }
    
}
