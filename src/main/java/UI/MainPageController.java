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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ulriksandberg
 */
public class MainPageController implements Initializable {

    @FXML
    public Label CurrentUserName;

    @FXML
    public Label CurrentUserTitle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SetCurrentUserInfo();
    }    

    @FXML
    private void handle_CreateInquiry(ActionEvent event) throws IOException {
        
        navigateNextPage(event, "HenvendelsesPage.fxml");
        
        
    }

    @FXML
    private void handle_logout(ActionEvent event) throws IOException {

        UI.getDomain().logout();

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

    private void SetCurrentUserInfo()
    {
        CurrentUserName.setText(UI.getDomain().getCurrentUserName());
        if(UI.getDomain().getCurrentUserAccessRights() == 1)
        {
            CurrentUserTitle.setText("Sekretær");
        }
        else
        {
            CurrentUserTitle.setText("Sagsbehandler");
        }
    }
}
