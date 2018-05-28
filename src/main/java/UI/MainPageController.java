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
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import DTO.Inquiry;
import javafx.application.Platform;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ulriksandberg
 */
public class MainPageController extends AbstractPageController implements Initializable {

    
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
    @FXML
    public TableView inquiryView;
    @FXML
    public TableColumn citizen;
    @FXML
    public TableColumn inquiry;


    private ObservableList<Inquiry> currentUserInquries;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SetCurrentUserInfo();

        //Download all inquiries related to the current user.
        new Thread(this::downloadCurrentUserInquiries).run();
    }

    private void downloadCurrentUserInquiries() {
        Platform.runLater(()->errorLabel.setText("Henter henvendelser...."));
        List<Inquiry> result = UI.getDomain().downloadCurrentUserInquiries();

        currentUserInquries = FXCollections.observableList(result);


        setInquiriesInTable(currentUserInquries);
        Platform.runLater(()->errorLabel.setText(""));
    }


    @FXML
    private void handle_CreateInquiry(ActionEvent event) throws IOException {

        navigateNextPage(event, "HenvendelsesPage.fxml");
    }

    @FXML
    private void handle_ContinueInquiry(ActionEvent event) throws IOException {

        if (inquiryView.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("HenvendelsesPage.fxml"));

            Parent adminScene = fxmlLoader.load();

            InquiryPageController hPage = fxmlLoader.<InquiryPageController>getController();
            hPage.setReopenedInquiry((Inquiry) inquiryView
                    .getSelectionModel().getSelectedItem());

            Scene newScene = new Scene(adminScene);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(newScene);
            appStage.show();

        } else {
            errorLabel.setText("Vælg en henvendelse");
        }
    }

    @FXML
    private void handle_logout(ActionEvent event) throws IOException {

        UI.getDomain().logout();

        navigateNextPage(event, "LoginPage.fxml");
    }


    private void SetCurrentUserInfo() {
        CurrentUserName.setText(UI.getDomain().getCurrentUserName());
        if (UI.getDomain().getCurrentUserAccessRights() == 1) {
            CurrentUserTitle.setText("Sekretær");
        } else {
            CurrentUserTitle.setText("Sagsbehandler");
        }
    }

    public void handle_SearchCriteriaSelected(ActionEvent actionEvent) {

        disableTextFields();

        if (togSearchCriteriaID.isSelected())
            txtInquiryId.setDisable(false);

        if (togSearchCriteriaCPR.isSelected())
            txtCPR.setDisable(false);

        if (togSearchCriteriaNAME.isSelected())
            txtCitizenName.setDisable(false);
    }


    private void disableTextFields() {
        txtInquiryId.setDisable(true);
        txtCitizenName.setDisable(true);
        txtCPR.setDisable(true);
    }

    private void setInquiriesInTable(ObservableList list) {
        currentUserInquries = list;

        citizen.setCellValueFactory(new PropertyValueFactory<Inquiry, String>("CitizenName"));

        inquiry.setCellValueFactory(new PropertyValueFactory<Inquiry, String>("Id"));

        inquiryView.setItems(currentUserInquries);
    }

    public void handle_BeginSearchOnCriteria(ActionEvent actionEvent) {

        List<Inquiry> result = null;

        if (togSearchCriteriaID.isSelected()) {

            if (!txtInquiryId.getText().isEmpty()) {
                System.out.println("Search for id: " + txtInquiryId.getText());

                result = UI.getDomain().getInquriesByInquiryId(
                        UUID.fromString(txtInquiryId.getText()));

            } else {
                errorLabel.setText("Please enter id");
            }
        }
        if (togSearchCriteriaCPR.isSelected()) {
            if (!txtCPR.getText().isEmpty()) {
                System.out.println("Search for cpr: " + txtCPR.getText());

                result = UI.getDomain().getInquiresByCPR(txtCPR.getText());

            } else {
                errorLabel.setText("Please enter cpr");
            }
        }

        if (togSearchCriteriaNAME.isSelected()) {
            if (!txtCitizenName.getText().isEmpty()) {
                System.out.println("Search for name: " + txtCitizenName.getText());

                result = UI.getDomain().getInquiresByCitizenName(txtCitizenName.getText());

            } else {
                errorLabel.setText("Please enter name");
            }
        }
        if (result != null) {

            currentUserInquries = FXCollections.observableList(result);
            setInquiriesInTable(currentUserInquries);

        }
    }
}
