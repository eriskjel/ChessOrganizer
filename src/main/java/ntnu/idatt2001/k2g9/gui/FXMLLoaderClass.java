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

    /**
     * Method that loads a new fxml file and sets it as the current scene
     * This particular methods is called when the Manage tournaments button is pressed, sending user to the admin manage fxml file
     * @param actionEvent event
     * @throws IOException exception
     */

    @FXML
    public void gotoAdminManageTournament(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-manage-tournaments.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Manage tournament");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Method that loads a new fxml file and sets it as the current scene
     * This particular methods is called when the Admin log out button is pressed, sending user to main page of application
     * @param actionEvent event
     * @throws IOException exception
     */
    public void adminLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Chess tournament organizer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that loads a new fxml file and sets it as the current scene
     * @param actionEvent event
     * @throws IOException
     */
    @FXML
    public void goToAddTournament(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-add-tournament.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);


        stage.setTitle("Add tournament!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that loads a new fxml file and sets it as the current scene
     * @param actionEvent actionevent
     * @throws IOException exception
     */
    public void goToBrackets(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("knockout-admin-view-bracket.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Bracket for tournament");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * method that loads a new fxml file and sets it as the current scene
     * @param actionEvent actionevent
     * @throws IOException exception
     */
    public void goToSpecificTournament(ActionEvent actionEvent) throws IOException {
        //load new fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-edit-tournament.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Edit tournament");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void goToKnockoutBracket(ActionEvent actionEvent) throws IOException {
        //load new fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("knockout-admin-view-bracket.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View bracket for tournament");
        stage.setScene(scene);
        stage.show();
    }

    public void goToRoundRobinBracket(ActionEvent actionEvent) throws IOException {
        //load new fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("robin-admin-view-bracket.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View bracket for tournament");
        stage.setScene(scene);
        stage.show();
    }

    public void goToSwissBracket(ActionEvent actionEvent) throws IOException {
        //load new fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("swiss-admin-view-bracket.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View bracket for tournament");
        stage.setScene(scene);
        stage.show();
    }
}
