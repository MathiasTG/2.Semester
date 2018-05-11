/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Acq.IResponse;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ulriksandberg
 */
public class AdminPageController implements Initializable {

    @FXML
    private ToggleGroup RoleGroup;
    @FXML
    private ToggleGroup AccesRightsGroup;
    @FXML
    private Label StatusUsernameLabel;
    @FXML
    private Label StatusLabel;
    @FXML
    private Label StatusMessage;
    @FXML
    private TextField UsernameField;
    private final String SECRETARY = "Sekretær";
    private final String CASEWORKER = "Sagsbehandler";

    
    private UI ui;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui = UI.getInstance();
    }    

    @FXML
    private void handle_createUser(ActionEvent event) {
        
        String role = RoleGroup.getSelectedToggle().toString();
        
        IResponse response = null;
        
        if(role != null){
            if(role.contains(SECRETARY)){
                response = ui.getDomain().createUser(UsernameField.getText(), 1);

            }
            else if(role.contains(CASEWORKER)){
                response = ui.getDomain().createUser(UsernameField.getText(), 2);
            }
            
            if(response.isSuccessful()){

                StatusLabel.setText("Oprettet med adgangskode:");
            }else{

                StatusLabel.setText("Fejl!");
            }
            StatusUsernameLabel.setText("Bruger: " + UsernameField.getText());
            StatusMessage.setText(response.getMessage());
        }
        
    }

    @FXML
    private void handle_OnNavigateBack(ActionEvent event) throws IOException {
        
        navigateNextPage(event, "LoginPage.fxml");
        
    }
    
    
    private void navigateNextPage(ActionEvent sender, String pageName) throws IOException
    {
        Parent adminScene = FXMLLoader.load(getClass().getClassLoader().getResource(pageName));

                
        Scene newScene = new Scene(adminScene);
        Stage appStage = (Stage) ((Node) sender.getSource()).getScene().getWindow();
        appStage.setScene(newScene);
        appStage.show();
    }
    
}
