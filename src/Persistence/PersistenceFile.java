/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Acq.IUser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Markb
 */
public class PersistenceFile {

    private File file = new File("Users.txt");

    /**
     * Validates a username.
     *
     * @param username
     * @return True if Username is available else returns false
     */
    public boolean validateUsername(String username) {
        String[] tokens;

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNext()) {
                String line = sc.nextLine();
                tokens = line.split("\t");
                if (tokens[1].equalsIgnoreCase(username)) {
                    return false;
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersistenceFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void createUser(IUser user) {

        UUID userID = user.getID();
        String userName = user.getUsername();
        String password = user.getPassword();
        int accessRight = user.getAccessRight();
        
        
        try (FileWriter fWriter = new FileWriter(file)) {
            fWriter.append(userID + "\t" + userName + "\t" + password + "\t" + accessRight + "\r\n");
        } catch (IOException ex) {
            Logger.getLogger(PersistenceFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
