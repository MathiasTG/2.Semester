/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Acq.IDomainFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ulriksandberg
 */
public class MainPageController implements Initializable {


    /**
     * Initializes the controller class.
     */
    
    
    private IDomainFacade domain;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        domain = UI.getInstance().getDomain();
        
    }    

    @FXML
    private void handle_CreateUser(ActionEvent event) {
        
        domain.createUser("Casper Hansen", 4);
        
    }
    
}
