/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

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
    @FXML
    private RadioButton togConsentFromOFFER;
    // SE HER !!!!     private RadioButton togConsentFromExternalOTHER;
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
    @FXML
    private TextArea textAreaConsentFromOFFER;


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
        
        if(togConsentFromOFFER.isSelected())
        {
            textAreaConsentFromOFFER.setDisable(false);
        }
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


    /*
    Creates a representative object, which is a parameter
    for the Inquiry class´ constructur
    Both from the DTO package
     */
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

    /*
    Creates a Citizen object, which is a parameter
    for the Inquiry class´ constructur
    Both from the DTO package
     */
    public Citizen getCitizen(Representative representative){

        String cpr = this.txtCitizenCPR.getText();
        String name = this.txtCitizenName.getText();
        String address = this.txtCitizenAddress.getText();
        String email = this.txtCitizenEmail.getText();
        int phoneNumber;
        if(this.txtCitizenPhone.getText().matches("\\d*")) {
            phoneNumber = Integer.parseInt(this.txtCitizenPhone.getText());
        } else
            phoneNumber = 00000000;





        return new Citizen.Builder(cpr, name, address).setEmail(email).setPhoneNumber(phoneNumber).
                setRepresentative(representative).build();
    }

    /*
    Creates and adds to a list (ArrayList) containing statements of consent gathered
    from different relevant sources. Returns the list, which is used in the Inquiry class´ constructor.
    Both classes are from the DTO pacakge
     */
    public void getGatheredConsent(Inquiry.Builder builder){
        if(this.togConsentFromExternalEmployer.isSelected())
            builder.addGatheredConsents(new GatheredConsent(ConsentEntity.EMPLOYER, this.textAreaConsentFromEMPLOYER.getText()));
        if(this.togConsentFromExternalSPECIALDOCTOR.isSelected())
            builder.addGatheredConsents(new GatheredConsent(ConsentEntity.SPECIAL_DOCTER, this.textAreaConsentFromSPECIALDOCTOR.getText()));
        if(this.togConsentFromExternalHOSPITAL.isSelected())
            builder.addGatheredConsents(new GatheredConsent(ConsentEntity.HOSPITAL, this.textAreaConsentFromHOSPITAL.getText()));
        if(this.togConsentFromExternalUNEMPLOYMENTFUND.isSelected())
            builder.addGatheredConsents(new GatheredConsent(ConsentEntity.UNEMPLOYMENT_FUND, this.textAreaConsentFromUNEMPLOYMENTFUND.getText()));
        if(this.togConsentFromOFFER.isSelected())
            builder.addGatheredConsents(new GatheredConsent(ConsentEntity.OFFER, this.textAreaConsentFromOFFER.getText()));
        if(this.togConsentFromExternalEmployer.isSelected())
            builder.addGatheredConsents(new GatheredConsent(ConsentEntity.EMPLOYER, this.textAreaConsentFromEMPLOYER.getText()));
        if(this.togConsentFromExternalOTHERMANAGEMENT.isSelected())
            builder.addGatheredConsents(new GatheredConsent(ConsentEntity.OTHER_MANAGEMENT, this.textAreaConsentFromOTHERMANAGEMENT.getText()));
    }

    /*
    Creates and returns a Submitter, which is the person or organisation which submits the
    request. Used in the Inquiry class´constructor
    Both classes are from the DTO package
     */
    public Submitter getSubmitter(){
        SubmitterType type;
        String contactInfo = this.textAreaSubmittedByCONTACTINFO.getText();

        if (togSubmittedByCITIZEN.isSelected())
            type = SubmitterType.CITIZIN;
        if (togSubmittedByDOCTOR.isSelected())
            type = SubmitterType.DOCTOR;
        if(togSubmittedByHOSPITAL.isSelected())
            type = SubmitterType.HOSPITAL;
        if(togSubmittedByMISCELLAEOUS.isSelected())
            type = SubmitterType.MISCELLANEOUS;
        if(togSubmittedByONGOINGEFFORT.isSelected())
            type = SubmitterType.ONGOING_EFFORT;
        if(togSubmittedByOTHERMANAGEMENT.isSelected())
            type = SubmitterType.OTER_MUNICIPALITY;
        if(togSubmittedByRELATIVE.isSelected())
            type = SubmitterType.RELATIVE;
        if(togSubmittedByOTHERMUNICIPALITY.isSelected())
            type = SubmitterType.OTER_MUNICIPALITY;
        else
            type = null;

        return new Submitter(type, contactInfo);

    }


    /*
    Creates the Inquiry object itself, which is injected to the domain layer.
    Calls several methods defined in this class, which creates and returns the different instances
    needed in the Inquiry constructor.
     */
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
        String specialConditions = this.textAreaExtraOrdinaryConditions.getText();
        String actingMunicipality = this.txtActingMunicipality.getText();
        String payingMunicipality = this.txtPaymentMunicipality.getText();
        Submitter submitter = this.getSubmitter();


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
        } else
            consentType = null;

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




        System.out.println("Representative: " + representative.getContactInfo());
        System.out.println("Citizen: " + citizen.getContactInfo());
        System.out.println("Description: " + description);
        System.out.println("IntentIsClear? " + intentIsClear);
        System.out.println("citizenAwareOfInquiry? " + citizenAwareOfInquiry);
        System.out.println("AgreementOfProgress: " + agreementOfProgress);

        UI.getDomain().injectInquiry(new Inquiry.Builder(this.user).setCitizen(citizen)
                .setCreatedBy(user).setDescription(description).setIntentIsClear(intentIsClear)
                .setCitizenAwareOfInquiry(citizenAwareOfInquiry).setCitizenInformedOfRights(citizenInformedOfRights)
                .setCitizenInformedOfDataReservation(citizenInformedOfDataReservation)
                .setAgreementOfProgress(agreementOfProgress).setConsentType(consentType)
                .setSpecialConditions(specialConditions)
                .setActingMunicipality(actingMunicipality).setPayingMunicipality(payingMunicipality)
                .setSubmittedBy(submitter).setIsRelevantToGatherConsent(isRelevantToGatherConsent)
                .build());
        //Hvor for jeg UUIDid fra? Er det Id op brugeren eller på henvendelsen?
        //Hvor får jeg supportsVUM? Tror ikke den er implementeret i GUI´en endnu :)
        // .addGatheredConsents(this.getGatheredConsent()) Fix, kan ikke tage liste. Metoden jeg har lavet
        //kan ikke tage et Builder objekt, da vi laver det anonymt. Man kan heller ikke få det i et loop,
        // da det anonyme objekt injectes samtidig med det laves.

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