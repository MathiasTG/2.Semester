/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ulriksandberg
 */
public class MainPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle_CreateInquiry(ActionEvent event) throws IOException {
        
        navigateNextPage(event, "HenvendelsesPage.fxml");
        
        
    }

    @FXML
    private void handle_logout(ActionEvent event) throws IOException {
        
        navigateNextPage(event, "LoginPage.fxml");
    }

    private void navigateNextPage(ActionEvent sender, String pageName) throws IOException
    {
        
        Parent adminScene = FXMLLoader.load(getClass().getResource(pageName));
                
        Scene newScene = new Scene(adminScene);
        Stage appStage = (Stage) ((Node) sender.getSource()).getScene().getWindow();
        appStage.setScene(newScene);
        appStage.show();
    }
}
