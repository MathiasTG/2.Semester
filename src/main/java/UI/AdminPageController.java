/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Acq.IResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import Acq.IUser;
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
public class AdminPageController implements Initializable {

    @FXML
    private TextField txtEditUserName;
    @FXML
    private TextField txtEditpassword;
    @FXML
    private TextField txtEditAccessRight;
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
    @FXML
    private TableView tableViewUsers;

    private ObservableList<IUser> users;
    private final String SECRETARY = "Sekretær";
    private final String CASEWORKER = "Sagsbehandler";
    private final String ADMIN = "Admin";
    private boolean listCreated = false;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handle_createUser(ActionEvent event) {
        
        String role = RoleGroup.getSelectedToggle().toString();
        
        IResponse response = null;
        
        if(role != null){
            if(role.contains(SECRETARY)){
                response = UI.getDomain().createUser(UsernameField.getText(), 1);

            }
            else if(role.contains(CASEWORKER)){
                response = UI.getDomain().createUser(UsernameField.getText(), 2);
            }
            else if(role.contains(ADMIN)){
                response = UI.getDomain().createUser(UsernameField.getText(), 3);
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

    public void btnDeleteUser(ActionEvent actionEvent) {
        if (!this.tableViewUsers.getSelectionModel().isEmpty()) {
            IUser user = (IUser) this.tableViewUsers.getSelectionModel().getSelectedItem();
            UI.getDomain().getPersistence().deleteById(user.getID());
            this.users.remove(user);
            //this.btnListUsers(actionEvent);
        }
    }

    public void btnEditUserName(ActionEvent actionEvent) {
        if (!this.tableViewUsers.getSelectionModel().isEmpty()) {
            String newName = this.txtEditUserName.getText();
            IUser user = (IUser) this.tableViewUsers.getSelectionModel().getSelectedItem();
            String oldName = user.getUsername();

            if (!newName.isEmpty()) {
                UI.getDomain().getPersistence().changeUserName(user, newName);
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(oldName))
                        users.get(i).setName(newName);
                }
                this.tableViewUsers.refresh();
            } else {
                this.txtEditUserName.setText("Select user, enter new name");
            }
        }
    }

    public void btnEditPassword(ActionEvent actionEvent) {
        if (!this.tableViewUsers.getSelectionModel().isEmpty()) {
            if (!this.txtEditpassword.getText().isEmpty()) {
                String password = this.txtEditpassword.getText();
                IUser user = (IUser) this.tableViewUsers.getSelectionModel().getSelectedItem();
                UI.getDomain().getPersistence().changePassword(user, password, false);
            }
        }
    }

    public void btnEditAccessRight(ActionEvent actionEvent) {
        if (!this.tableViewUsers.getSelectionModel().isEmpty()) {
            String newAccessRightString = this.txtEditAccessRight.getText();
            IUser user = (IUser) this.tableViewUsers.getSelectionModel().getSelectedItem();
            if (!newAccessRightString.matches("\\d*")) {
                this.txtEditAccessRight.setText("Indtast tal 1-3");
                System.out.println("Ikke et tal");
            } else {
                int accessRight = Integer.parseInt(newAccessRightString);
                if (accessRight > 0 && accessRight < 4) {
                    UI.getDomain().getPersistence().changeAccessRight(user, accessRight);
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getUsername().equals(user.getUsername())) {
                            users.get(i).setAccessRight(accessRight);
                            this.tableViewUsers.refresh();
                        }
                    }
                } else {
                    this.txtEditAccessRight.setText("Indtast tal 1-3");
                }
            }
        }
    }

    public void btnListUsers(ActionEvent actionEvent) {

        if (this.listCreated) {
            this.tableViewUsers.refresh();
        }
        else {
            this.listCreated = true;

            users = FXCollections.observableArrayList();
            List<IUser> IUsers = UI.getDomain().revertIPUserToIUser(UI.getDomain().getPersistence().getAllUsers(1, 100));


            users.addAll(IUsers);

            TableColumn id = new TableColumn("ID");
            id.setCellValueFactory(new PropertyValueFactory<IUser, UUID>("ID"));

            TableColumn userName = new TableColumn("Navn");
            userName.setCellValueFactory(new PropertyValueFactory<IUser, String>("username"));

            TableColumn role = new TableColumn("Adgangs Niveau");
            role.setCellValueFactory(new PropertyValueFactory<IUser, Integer>("accessRight"));

            this.tableViewUsers.setEditable(true);
            this.tableViewUsers.setItems(users);

            this.tableViewUsers.getColumns().addAll(id, userName, role);
        }

    }
}
