/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DTO.Inquiry;
import javafx.application.Platform;
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
import javafx.scene.image.ImageView;
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
    @FXML
    public Label loadingIndicator;

    @FXML
    public ImageView loadingIndicatorGif;


    private ObservableList<Inquiry> currentUserInquries;

    private ExecutorService service = Executors.newFixedThreadPool(3);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SetCurrentUserInfo();

        //Download all inquiries related to the current user.
        service.execute(this::downloadCurrentUserInquiries);

    }

    @FXML
    private void handle_getAllUserInquries(ActionEvent event) {
        service.execute(this::downloadCurrentUserInquiries);
    }

    private void downloadCurrentUserInquiries() {
        Platform.runLater(() -> {
                    loadingIndicator.setText("Henter henvendelser..");
                    loadingIndicatorGif.setVisible(true);
                }
        );
        List<Inquiry> result = UI.getDomain().downloadCurrentUserInquiries();

        currentUserInquries = FXCollections.observableList(result);


        setInquiriesInTable(currentUserInquries);
        Platform.runLater(() ->{
            loadingIndicator.setText("");
            loadingIndicatorGif.setVisible(false);
        }
        );
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
        service.execute(this::beginSearchOnCriteria);
    }

    public void beginSearchOnCriteria() {
        Platform.runLater(() -> loadingIndicator.setText("Henter henvendelser.."));
        List<Inquiry> result = null;

        if (togSearchCriteriaID.isSelected()) {

            if (!txtInquiryId.getText().isEmpty()) {
                System.out.println("Search for id: " + txtInquiryId.getText());

                result = UI.getDomain().getInquriesByInquiryId(
                        UUID.fromString(txtInquiryId.getText()));

            } else {
                Platform.runLater(() -> errorLabel.setText("Indtast ID"));
            }
        }
        if (togSearchCriteriaCPR.isSelected()) {
            if (!txtCPR.getText().isEmpty()) {
                System.out.println("Search for cpr: " + txtCPR.getText());

                result = UI.getDomain().getInquiresByCPR(txtCPR.getText());

            } else {
                Platform.runLater(() -> errorLabel.setText("Indtast cpr"));
            }
        }

        if (togSearchCriteriaNAME.isSelected()) {
            if (!txtCitizenName.getText().isEmpty()) {
                System.out.println("Search for name: " + txtCitizenName.getText());

                result = UI.getDomain().getInquiresByCitizenName(txtCitizenName.getText());

            } else {
                Platform.runLater(() -> errorLabel.setText("Indtast navn"));
            }
        }
        if (result != null) {

            currentUserInquries = FXCollections.observableList(result);
            setInquiriesInTable(currentUserInquries);

        }
        Platform.runLater(() -> loadingIndicator.setText(""));
    }
}
