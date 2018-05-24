/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Acq.IResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ulriksandberg
 */
public class LoginPageController implements Initializable {

    @FXML
    public PasswordField txtPassword;
    @FXML
    public TextField txtUsername;
    @FXML
    public Label errorLabel;

    private Label label;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Handle_LoginClicked(ActionEvent event) throws IOException {


        if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty())
        {
            errorLabel.setText("Udfyld alle felter");
            return;
        }


        //login

       IResponse response = UI.getDomain().logIn(txtUsername.getText() , txtPassword.getText());

        if(response.isSuccessful() && UI.getDomain().getCurrentUserAccessRights() < 3) //If the user is a caseworker or secretary, go to mainpage
        {
            navigateNextPage(event, "MainPage.fxml");
        }
        else if(response.isSuccessful() && UI.getDomain().getCurrentUserAccessRights() == 3) //If the user is a admin, go to adminpage
        {
            navigateNextPage(event, "AdminPage.fxml");
        }
        else
        {
            errorLabel.setText(response.getMessage());
        }

        // if response is true navigate next page



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
