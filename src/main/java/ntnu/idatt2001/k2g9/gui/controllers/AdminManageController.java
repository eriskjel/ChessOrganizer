package ntnu.idatt2001.k2g9.gui.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ntnu.idatt2001.k2g9.gui.models.CompetitorModel;
import ntnu.idatt2001.k2g9.gui.models.TournamentModel;
import ntnu.idatt2001.k2g9.tournament.RegistryClient;
import ntnu.idatt2001.k2g9.tournament.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller that administers the fxml file "admin-manage-tournaments" and handles all the events on said fxml file
 */
public class AdminManageController implements Initializable {

    @FXML private TableColumn colDate;
    @FXML private TableColumn colName;
    @FXML private TableColumn colCompetitors;
    @FXML private TableColumn colFormat;
    @FXML private TableView tblTournaments;
    @FXML private ObservableList<CompetitorModel> observableList = FXCollections.observableArrayList();

    /**
     * method that calls on the fxmlloaderClass to load the tournament hub fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToAddTournament(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToAddTournament(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load the tournament hub fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToAdminTournamentHub(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToAdminTournamentHub(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load the login fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void adminLogOut(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.adminLogOut(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load specific tournament edit fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToSpecificTournament(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToSpecificTournament(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load specific tournament edit fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToKnockoutBracket(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToKnockoutBracket(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load specific tournament edit fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToRoundRobinBracket(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToRoundRobinBracket(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load specific tournament edit fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToSwissBracket(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToSwissBracket(actionEvent);
    }


    /**
     * method that runs as soon as the fxml file loads. this particular override account for filling in the table with the tournament data registered
     * @param url url
     * @param resourceBundle bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.colCompetitors.setCellValueFactory(new PropertyValueFactory<>("numCompetitors"));
        this.colFormat.setCellValueFactory(new PropertyValueFactory<>("Format"));
        this.tblTournaments.setItems(observableList);
        refreshTable();
    }

    /**
     * clear all data from table, and updates and inserts data from the Registryclient
     */
    public void refreshTable() {
        //clear table beforehand
        ObservableList<TournamentModel> allTournaments;
        allTournaments = tblTournaments.getItems();
        allTournaments.clear();

        //add tournament
        ArrayList<Tournament> tournaments = RegistryClient.tournamentRegistry.getTournaments();
        ArrayList<TournamentModel> tournamentModels = new ArrayList<>();
        for (Tournament tournament : tournaments) {
            TournamentModel tournamentModel = new TournamentModel(tournament);
            tournamentModels.add(tournamentModel);
        }
        tblTournaments.getItems().addAll(tournamentModels);

    }

    public int getSelectedTournamentID(){
        //get selected tournament
        ObservableList<TournamentModel> singleTournament;
        singleTournament = tblTournaments.getSelectionModel().getSelectedItems();

        //get selected tournament ID
        return singleTournament.get(0).getTournamentID();
    }

    /**
     * method that gets the tournament ID from the user selected tournament in the GUI.
     * it then sets the ID in the controller that will be handling the edit functionality, and then calls
     * the fxml loader method in this class to load the edit fxml file
     * @param event
     * @throws IOException
     */
    public void editTournament(ActionEvent event) throws IOException {
        AdminEditTournamentController.setTournamentID(getSelectedTournamentID());
        this.goToSpecificTournament(event);
    }

    /**
     * method that checks which layout the current tournament is, and afterwards calls on the correct fxml loading method
     * if the layout does not correspond with any of the registered layouts, the method will print an error
     * @param actionEvent action event
     * @throws IOException exception
     */
    public void determineAndGoToBracket(ActionEvent actionEvent) throws IOException, IllegalArgumentException {
        int selectedTournamentID = getSelectedTournamentID();
        String layout = RegistryClient.tournamentRegistry.getTournament(selectedTournamentID).getLayout();
        switch (layout) {
            case "Knock-Out" -> {
                AdminViewKnockOutBracketController.setTournamentID(selectedTournamentID);
                this.goToKnockoutBracket(actionEvent);
            }
            case "Round-Robin" -> this.goToRoundRobinBracket(actionEvent);
            case "Swiss-System" -> this.goToSwissBracket(actionEvent);
            default -> throw new IllegalArgumentException("Layout not found.");
        }
    }
}
