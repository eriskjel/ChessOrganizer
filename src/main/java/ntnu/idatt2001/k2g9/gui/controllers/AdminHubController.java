package ntnu.idatt2001.k2g9.gui.controllers;


import javafx.event.ActionEvent;
import ntnu.idatt2001.k2g9.gui.FXMLLoaderClass;

import java.io.IOException;

/**
 * Controller that administers the fxml file "admin-hub-tournaments" and handles all the events on said fxml file
 */
public class AdminHubController {


    /**
     * method that calls on the fxmlloaderClass to load the tournament hub fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToAddTournament(ActionEvent actionEvent) throws IOException {
        FXMLLoaderClass.goToAddTournament(actionEvent);
    }


    /**
     * method that calls on the fxmlloaderClass to load the tournament hub fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToAdminTournamentHub(ActionEvent actionEvent) throws IOException {
        FXMLLoaderClass.goToAdminTournamentHub(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load the admin manage fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void gotoAdminManageTournament(ActionEvent actionEvent) throws IOException {
        FXMLLoaderClass.gotoAdminManageTournament(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load the login fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void adminLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoaderClass.adminLogOut(actionEvent);
    }


}