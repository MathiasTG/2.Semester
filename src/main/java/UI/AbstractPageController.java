package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Changes the current stage to the one specified with the pageName
 * Extended by all page controllers
 */
public abstract class AbstractPageController {

    protected void navigateNextPage(ActionEvent sender, String pageName) throws IOException
    {
        Parent adminScene = FXMLLoader.load(getClass().getClassLoader().getResource(pageName));


        Scene newScene = new Scene(adminScene);
        Stage appStage = (Stage) ((Node) sender.getSource()).getScene().getWindow();
        appStage.setScene(newScene);
        appStage.show();
    }
}
