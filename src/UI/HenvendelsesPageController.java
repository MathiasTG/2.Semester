/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ulriksandberg
 */
public class HenvendelsesPageController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane gridPane;
    @FXML
    private javafx.scene.control.TextField txtCitizenName;
    @FXML
    private javafx.scene.control.TextField txtCitizenCPR;
    @FXML
    private javafx.scene.control.TextArea txtAreaInqueryDescription;
    @FXML
    private javafx.scene.control.TextField txtCitizenAddress;
    @FXML
    private javafx.scene.control.TextField txtCitizenEmail;
    @FXML
    private javafx.scene.control.TextField txtCitizenPhone;
    @FXML
    private RadioButton togIntentIsClearYES;
    @FXML
    private ToggleGroup togGrIntentIsClear;
    @FXML
    private RadioButton togIntentIsClearNO;
    @FXML
    private RadioButton togIsCitizenAwareOfInquiryYES;
    @FXML
    private ToggleGroup togGrIsCitizenAwareOfInquiry;
    @FXML
    private RadioButton togIsCitizenAwareOfInquiryNO;
    @FXML
    private RadioButton togSubmittedByCITIZEN;
    @FXML
    private ToggleGroup togGrSubmittedBy;
    @FXML
    private RadioButton togSubmittedByRELATIVE;
    @FXML
    private RadioButton togSubmittedByDOCTOR;
    @FXML
    private RadioButton togSubmittedByHOSPITAL;
    @FXML
    private RadioButton togSubmittedByOTHERMANAGEMENT;
    @FXML
    private RadioButton togSubmittedByOTHERMUNICIPALITY;
    @FXML
    private RadioButton togSubmittedByMISCELLAEOUS;
    @FXML
    private RadioButton togSubmittedByONGOINGEFFORT;
    @FXML
    private RadioButton togRepresentativeLEGALGUARDIAN;
    @FXML
    private ToggleGroup togGrRepresentative;
    @FXML
    private RadioButton togRepresentativePOWEROFATTORNEY;
    @FXML
    private RadioButton togRepresentativeREPRESENTATIVE;
    @FXML
    private RadioButton togRightToByStanderAndRepresentative;
    @FXML
    private RadioButton togIsCitizenInformedOfOnlineSavingYES;
    @FXML
    private ToggleGroup togGrIsCitizenInformedOfOnlineSaving;
    @FXML
    private RadioButton togIsCitizenInformedOfOnlineSavingNO;
    @FXML
    private javafx.scene.control.TextArea txtAreaSubmitFurtherProgress;
    @FXML
    private RadioButton togIsConsentRelevantYES;
    @FXML
    private ToggleGroup togGrIsConsentRelevant;
    @FXML
    private RadioButton togIsConsentRelevantNO;
    @FXML
    private RadioButton togHowIsConsentGivenVERBAL;
    @FXML
    private ToggleGroup togGrHowIsConsentGiven;
    @FXML
    private RadioButton togConsentFromExternalOWNDOCTOR;
    @FXML
    private ToggleGroup togGrConsentForExternalInfo;
    @FXML
    private RadioButton togConsentFromExternalSPECIALDOCTOR;
    @FXML
    private RadioButton togConsentFromExternalHOSPITAL;
    @FXML
    private RadioButton togConsentFromExternalUNEMPLOYMENTFUND;
    @FXML
    private RadioButton togConsentFromExternalEmployer;
    @FXML
    private RadioButton togConsentFromExternalFORMERMUNICIPALITY;
    @FXML
    private RadioButton togConsentFromExternalOTHERMANAGEMENT;
    @FXML
    private RadioButton togConsentFromExternalOTHER;
    @FXML
    private javafx.scene.control.TextField txtSpecifyOtherConsentFromExternal;
    @FXML
    private javafx.scene.control.TextArea textAreaExtraOrdinaryConditions;
    @FXML
    private javafx.scene.control.TextField txtPaymentMunicipality;
    @FXML
    private javafx.scene.control.TextField txtActingMunicipality;
    @FXML
    private javafx.scene.control.TextArea textAreaRepresentativeContactInfo;
    @FXML
    private javafx.scene.control.TextArea textAreaSubmittedByCONTACTINFO;
    @FXML
    private RadioButton togHowIsConsentGivenWRITEN;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        togHowIsConsentGivenVERBAL.setDisable(true);
        togHowIsConsentGivenWRITEN.setDisable(true);
        textAreaRepresentativeContactInfo.setDisable(true);
        txtSpecifyOtherConsentFromExternal.setDisable(true);
        textAreaSubmittedByCONTACTINFO.setDisable(true);
    }    

    @FXML
    private void handle_NavigateBack(ActionEvent event) throws IOException {

        navigateNextPage(event, "MainPage.fxml");

    }
    
    private void navigateNextPage(ActionEvent sender, String pageName) throws IOException
    {
        
        Parent adminScene = FXMLLoader.load(getClass().getResource(pageName));
                
        Scene newScene = new Scene(adminScene);

        Stage appStage = (Stage) ((Node) sender.getSource()).getScene().getWindow();
        appStage.setScene(newScene);
        appStage.show();
    }

    @FXML
    private void handle_togGrRepresentativeSelected(ActionEvent event) {
        
        System.out.println("representative selected");
        
        if(togRepresentativeLEGALGUARDIAN.isSelected())
        {
            textAreaRepresentativeContactInfo.setDisable(false);
        }
        else
        {
            textAreaRepresentativeContactInfo.setDisable(true);
        }
        
    }

    @FXML
    private void handle_togGrExternalConsentSelected(ActionEvent event) {
        
        System.out.println("externalConsentSelected");
        
        if(togConsentFromExternalOTHER.isSelected())
        {
            txtSpecifyOtherConsentFromExternal.setDisable(false);
        }
        else
        {
            txtSpecifyOtherConsentFromExternal.setDisable(true);
        }
        
    }

    @FXML
    private void handle_togGrSubmittedBySelected(ActionEvent event) {
        
        System.out.println("submittedBySelected");
        if(togSubmittedByCITIZEN.isSelected())
        {
            textAreaSubmittedByCONTACTINFO.setDisable(true);
        }
        else
        {
            textAreaSubmittedByCONTACTINFO.setDisable(false);
        }
        
    }

    @FXML
    private void handle_createApplication(ActionEvent event) {
        
        System.out.println("Create inquiry");
        
        
    }

    @FXML
    private void handle_saveInquiry(ActionEvent event) {
        
        System.out.println("save inquiry");
        
    }

    @FXML
    private void handle_togGrIsConsentRelevant(ActionEvent event) {
    
        if(togIsConsentRelevantYES.isSelected())
        {
            togHowIsConsentGivenVERBAL.setDisable(false);
            togHowIsConsentGivenWRITEN.setDisable(false);
        }
        else
        {
            togHowIsConsentGivenVERBAL.setDisable(true);
            togHowIsConsentGivenWRITEN.setDisable(true);
        }
        
    }
    
    
}
