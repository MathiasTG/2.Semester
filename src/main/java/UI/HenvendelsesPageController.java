/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import Acq.IUser;
import Domain.Caseworker;
import Domain.Password;
import Domain.User;

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
import DTO.*;
import javafx.scene.control.TextArea;

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
    private RadioButton togConsentFromExternalOTHER;
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


    IUser user;
    @FXML
    private TextArea textAreaConsentFromOWNDOCTOR;
    @FXML
    private TextArea textAreaConsentFromSPECIALDOCTOR;
    @FXML
    private TextArea textAreaConsentFromHOSPITAL;
    @FXML
    private TextArea textAreaConsentFromUNEMPLOYMENTFUND;
    @FXML
    private TextArea textAreaConsentFromEMPLOYER;
    @FXML
    private TextArea textAreaConsentFromFORMERMUNICIPALITY;
    @FXML
    private TextArea textAreaConsentFromOTHERMANAGEMENT;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        togHowIsConsentGivenVERBAL.setDisable(true);
        togHowIsConsentGivenWRITEN.setDisable(true);
        textAreaRepresentativeContactInfo.setDisable(true);
        //txtSpecifyOtherConsentFromExternal.setDisable(true);
        textAreaSubmittedByCONTACTINFO.setDisable(true);

        //******* NOT THE ACTUAL USER, DELETE WHEN LOGIN AND CREATE USER IS IMPLEMENTED!!!!!!!!!!!!! *******************
        this.user = new Caseworker("Ulrik", 10, new Password());
    }    

    @FXML
    private void handle_NavigateBack(ActionEvent event) throws IOException {

        navigateNextPage(event, "MainPage.fxml");

    }
    
    private void navigateNextPage(ActionEvent sender, String pageName) throws IOException
    {
        
        Parent adminScene = FXMLLoader.load(getClass().getClassLoader().getResource(pageName));
                
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
        
        
        if(togConsentFromExternalOWNDOCTOR.isSelected())
        {
            textAreaConsentFromOWNDOCTOR.setDisable(false);
        }
        else
        {
            textAreaConsentFromSPECIALDOCTOR.setDisable(true);
        }
        
        if(togConsentFromExternalSPECIALDOCTOR.isSelected())
        {
            textAreaConsentFromSPECIALDOCTOR.setDisable(false);
        }
        else
        {
            textAreaConsentFromSPECIALDOCTOR.setDisable(true);
        }
        
        if(togConsentFromExternalHOSPITAL.isSelected())
        {
            textAreaConsentFromHOSPITAL.setDisable(false);
        }
        else
        {
            textAreaConsentFromHOSPITAL.setDisable(true);
        }
        
        if(togConsentFromExternalUNEMPLOYMENTFUND.isSelected())
        {
            textAreaConsentFromUNEMPLOYMENTFUND.setDisable(false);
        }
        else
        {
            textAreaConsentFromUNEMPLOYMENTFUND.setDisable(true);
        }
        
        if(togConsentFromExternalEmployer.isSelected())
        {
            textAreaConsentFromEMPLOYER.setDisable(false);
        }
        else
        {
            textAreaConsentFromEMPLOYER.setDisable(true);
        }
        
        if(togConsentFromExternalFORMERMUNICIPALITY.isSelected())
        {
            textAreaConsentFromFORMERMUNICIPALITY.setDisable(false);
        }
        else
        {
            textAreaConsentFromFORMERMUNICIPALITY.setDisable(true);
        }
        
        if(togConsentFromExternalOTHERMANAGEMENT.isSelected())
        {
            textAreaConsentFromOTHERMANAGEMENT.setDisable(false);
        }
        else
        {
            textAreaConsentFromOTHERMANAGEMENT.setDisable(true);
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


    public Representative getRepresentative(){
        String contactInfo = this.textAreaRepresentativeContactInfo.getText();
        TypeOfRepresentative type;

        if(togRepresentativeLEGALGUARDIAN.isSelected()){
            type = TypeOfRepresentative.LEGAL_GUARDIAN;
        }
        else if(togRepresentativePOWEROFATTORNEY.isSelected()){
            type = TypeOfRepresentative.POWER_OF_ATTORNEY;
        }
        else if (togRepresentativeREPRESENTATIVE.isSelected()){
            type = TypeOfRepresentative.PART_REPRESENTATIVE;
        }
        else
            type = null;

        return new Representative.Builder(contactInfo, type).build();
    }

    public Citizen getCitizen(Representative representative){

        String cpr = this.txtCitizenCPR.getText();
        String name = this.txtCitizenName.getText();
        String address = this.txtCitizenAddress.getText();
        String email = this.txtCitizenEmail.getText();
        int phoneNumber = Integer.parseInt(this.txtCitizenPhone.getText());



        return new Citizen.Builder(cpr, name, address).setEmail(email).setPhoneNumber(phoneNumber).
                setRepresentative(representative).build();
    }

    /*public List<GatheredConsent> getGatheredConsent(){

    }*/


    @FXML
    private void handle_createApplication(ActionEvent event) {

        Representative representative = this.getRepresentative();
        Citizen citizen = getCitizen(representative);
        String description = this.txtAreaInqueryDescription.getText();
        boolean intentIsClear;
        boolean citizenAwareOfInquiry;
        boolean citizenInformedOfRights;
        boolean citizenInformedOfDataReservation;
        String agreementOfProgress = this.txtAreaSubmitFurtherProgress.getText();
        ConsentType consentType;
        boolean isRelevantToGatherConsent;

        if(this.togIsConsentRelevantYES.isSelected()){
            isRelevantToGatherConsent = true;
        } else
            isRelevantToGatherConsent = false;

        if(isRelevantToGatherConsent){
            if(this.togHowIsConsentGivenVERBAL.isSelected())
                consentType = ConsentType.VERBAL;
            else if(this.togHowIsConsentGivenWRITEN.isSelected())
                consentType = ConsentType.WRITTEN;
            else
                consentType = null;
        }

        if(this.togIsCitizenInformedOfOnlineSavingYES.isSelected())
            citizenInformedOfDataReservation = true;
        else
            citizenInformedOfDataReservation = false;

        if (this.togRightToByStanderAndRepresentative.isSelected())
            citizenInformedOfRights = true;
        else
            citizenInformedOfRights = false;

        if(this.togIsCitizenAwareOfInquiryYES.isSelected())
            citizenAwareOfInquiry = true;
        else
            citizenAwareOfInquiry = false;

        if(this.togIntentIsClearYES.isSelected())
            intentIsClear = true;
        else
            intentIsClear = false;


       /* UI.getInstance().getDomain().injectInquiry(new Inquiry.Builder(this.user).
        setCitizen(citizen).setDraft(false).setDescription(description)
        .setIntentIsClear(intentIsClear).setCitizenAwareOfInquiry(citizenAwareOfInquiry);*/
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
