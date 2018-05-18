/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import DTO.Inquiry;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

    @FXML
    public ToggleGroup togGrSearchCriteria;
    @FXML
    public RadioButton togSearchCriteriaID;
    @FXML
    public RadioButton togSearchCriteriaCPR;
    @FXML
    public RadioButton togSearchCriteriaNAME;
    @FXML
    public TextField txtCitizenName;
    @FXML
    public TextField txtCPR;
    @FXML
    public TextField txtInquiryId;
    @FXML
    public Label errorLabel;


    private ObservableList<Inquiry> currentUserInquries;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SetCurrentUserInfo();

        //Download all inquiries related to the current user.
        downloadCurrentUserInquiries();
    }    

    private void downloadCurrentUserInquiries()
    {

        List<Inquiry> result = UI.getDomain().downloadCurrentUserInquiries();

        currentUserInquries = FXCollections.observableList(result);


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

    public void handle_SearchCriteriaSelected(ActionEvent actionEvent) {

        disableTextFields();

        if(togSearchCriteriaID.isSelected())
            txtInquiryId.setDisable(false);

        if(togSearchCriteriaCPR.isSelected())
            txtCPR.setDisable(false);

        if(togSearchCriteriaNAME.isSelected())
            txtCitizenName.setDisable(false);
    }


    private void disableTextFields()
    {
        txtInquiryId.setDisable(true);
        txtCitizenName.setDisable(true);
        txtCPR.setDisable(true);
    }

    public void handle_BeginSearchOnCriteria(ActionEvent actionEvent) {

        if(togSearchCriteriaID.isSelected()) {

            if(!txtInquiryId.getText().isEmpty())
            {
                System.out.println("Search for id: " + txtInquiryId.getText());


            }
            errorLabel.setText("Please enter id");
        }
        if(togSearchCriteriaCPR.isSelected())
        {
            if(!txtCPR.getText().isEmpty())
            {
                System.out.println("Search for cpr: " + txtCPR.getText());
            }
            errorLabel.setText("Please enter cpr");
        }

        if(togSearchCriteriaNAME.isSelected())
        {
            if(!txtCitizenName.getText().isEmpty())
            {
                System.out.println("Search for name: " + txtCitizenName.getText());
            }
            errorLabel.setText("Please enter name");
        }

    }
}
