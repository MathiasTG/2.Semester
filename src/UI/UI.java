/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Acq.IDomainFacade;
import Acq.IUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ulriksandberg
 */
public class UI extends Application implements IUI {
    
    private IDomainFacade domainFacade;
    private static UI ui;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static UI getInstance()
    {
        return ui;
    }
    
    public IDomainFacade getDomain()
    {
        return this.domainFacade;
    }

    @Override
    public void injectDomain(IDomainFacade domainFacade) {
         this.domainFacade = domainFacade;
    }

    @Override
    public void startApplication(String[] args) {
        
        System.out.println("UI initializing, bbeee bobb, beeeeeeb");
        
        ui = this;
        launch(args);
        
    }
    
}
