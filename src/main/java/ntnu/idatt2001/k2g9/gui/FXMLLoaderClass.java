package ntnu.idatt2001.k2g9.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ntnu.idatt2001.k2g9.gui.application.Application;

import java.io.IOException;

public class FXMLLoaderClass {


    private Stage stage;


    /**
     * Method that loads a new fxml file and sets it as the current scene
     * This particular methods is called when the tournament hub button is pressed, sending user to the admin tournament hub fxml file
     * @param actionEvent event
     * @throws IOException exception
     */
    @FXML
    public void goToAdminTournamentHub(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-hub.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Tournament hub");
        stage.setScene(scene);
        stage.show();
    }
}
