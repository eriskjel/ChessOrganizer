package ntnu.idatt2001.k2g9.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ntnu.idatt2001.k2g9.file.FileHandler;
import ntnu.idatt2001.k2g9.gui.models.CompetitorModel;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.tournament.RegistryClient;
import ntnu.idatt2001.k2g9.tournament.Tournament;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class AdminEditTournamentController implements Initializable {

    private static int tournamentID;
    @FXML private TableView tableCompetitors;
    @FXML private TableColumn tblName;
    @FXML private TableColumn tblAge;
    @FXML private TextField inpFullName;
    @FXML private TextField inpAge;
    @FXML private TextField inpTournamentName;
    @FXML private DatePicker inpDate;
    @FXML private MenuButton inpTournamentFormat;
    @FXML private Label lblTournamentName;
    @FXML private ObservableList<CompetitorModel> observableList = FXCollections.observableArrayList();

    private FileHandler f = new FileHandler();
    Tournament t = f.readTournamentFromFile(getTournamentID());

    /**
     * sets current tournamentID
     * @param ID id of tournament
     */
    public static void setTournamentID(int ID){
        tournamentID = ID;
    }

    /**
     * getter for tournamentID
     * @return tournamentID
     */
    public static int getTournamentID(){
        return tournamentID;
    }


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
     * method that calls on the fxmlloaderClass to load the admin manage fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void gotoAdminManageTournament(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.gotoAdminManageTournament(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load the login fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void adminLogOut(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.adminLogOut(actionEvent);
    }

    public void clearCompetitorTable(){
        ObservableList<CompetitorModel> allCompetitors;
        allCompetitors = this.tableCompetitors.getItems();
        allCompetitors.clear();
    }

    /**
     * refreshes data in tables in application. clears table and fills the table with the players registered in selected tournament
     */
    public void refreshData() {
        //clear table beforehand
        clearCompetitorTable();


        ArrayList<Player> competitors = this.t.getPlayerRegistry().getPlayers();
        ArrayList<CompetitorModel> competitorModels = new ArrayList<>();
        for (Player player : competitors){
            CompetitorModel competitorModel = new CompetitorModel(player);
            competitorModels.add(competitorModel);
        }

        tableCompetitors.getItems().addAll(competitorModels);

        this.updateTournamentInfo();
        this.lblTournamentName.setText(this.t.getName());
    }

    /**
     * updates tournament info on the application. In other words, displays the already registered tournament info, so the user
     * can see what the tournament info already is.
     */
    public void updateTournamentInfo(){
        this.inpTournamentName.setText(this.t.getName());
        this.inpTournamentFormat.setText(this.t.getLayout());
        this.inpDate.setPromptText(this.t.getDate().toString());
    }

    /**
     * removes competitors from gui table and from tournament, and also calls on the registry to reset all player id's
     * @param actionEvent event
     */
    public void removeCompetitor(ActionEvent actionEvent) {
        //removes from table
        ObservableList<CompetitorModel> allCompetitors, singleCompetitor;
        allCompetitors = tableCompetitors.getItems();
        singleCompetitor = tableCompetitors.getSelectionModel().getSelectedItems();
        singleCompetitor.forEach(allCompetitors::remove);

        //removes from tournament
        Player player = new Player(singleCompetitor.get(0).getName(), singleCompetitor.get(0).getAge());
        player.setPlayerID(singleCompetitor.get(0).getCompetitorID());
        //RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getPlayerRegistry().removePlayer(player);
        this.t.removePlayer(player);

        //RegistryClient.tournamentRegistry.getTournament(getTournamentID()).removePlayer(player);
        this.t.getPlayerRegistry().resetPlayerIDs();
        //RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getPlayerRegistry().resetPlayerIDs();
    }

    /**
     * adds competitor to GUI table and to tournament
     * @param actionEvent event
     */
    public void addCompetitor(ActionEvent actionEvent) {
        Player player = new Player(this.inpFullName.getText(), Integer.parseInt(this.inpAge.getText()));
        this.t.addPlayer(player);

        //RegistryClient.tournamentRegistry.getTournament(getTournamentID()).addPlayer(player);

        //object to be added to table in GUI.
        //CompetitorModel competitor = new CompetitorModel(this.tblName.getText(), Integer.parseInt(this.tblAge.getText()), );
        CompetitorModel competitor = new CompetitorModel(player);
        tableCompetitors.getItems().add(competitor);


        //creates new bracket with the new competitor
        this.t.createTournamentBracket();
        //RegistryClient.tournamentRegistry.getTournament(getTournamentID()).createTournamentBracket();


        //resets input fields
        this.inpFullName.setText("");
        this.inpAge.setText("");
    }


    /**
     * method that runs as soon as the fxml file is loaded. this makes sure the competitor table is filled when fxml is opened
     * @param url url
     * @param resourceBundle bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tblName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.tblAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        this.tableCompetitors.setItems(observableList);
        refreshData();
    }


    /**
     *
     *Method that sets tournament format variable based on what is chosen in the selection menu
     */
    public void setFormatKnockout() {
        this.inpTournamentFormat.setText("Knock-Out");
    }

    /**
     *
     *Method that sets tournament format variable based on what is chosen in the selection menu
     */
    public void formatSwiss() {
        this.inpTournamentFormat.setText("Swiss-System");
    }

    /**
     * checks if the date is valid. returns true if date is after current date
     * @return true if date is valid, meaning the date has not already passed, false if otherwise
     */
    public boolean isDateValid(LocalDate date) {
        return !date.isBefore(LocalDate.now());
    }
    /**
     * Saves a tournament. If values are not changed in the application, they will be read as null. However if so
     * the method just uses the already registered data from the already registered tournament.
     * This will make sure that only new values will be added, and old values that are not to be changed stays the same
     * @param event event
     * @throws IOException exception
     */
    public void saveTournament(ActionEvent event) throws IOException {
        String tournamentName = inpTournamentName.getText();

        String newTournamentFormat = this.inpTournamentFormat.getText();
        //will be null if not altered with in application, is so the program uses the already registered format
        if (newTournamentFormat == null){
            newTournamentFormat = this.t.getLayout();
            //newTournamentFormat = RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getLayout();
        }


        //will be null if not altered with in application, is so the program uses the already registered date
        LocalDate date = this.inpDate.getValue();
        if (date == null){
            date = this.t.getDate();
            //date = RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getDate();
        }
        if(isDateValid(date)){
            //updates with fresh info
            this.t.setName(tournamentName);
            this.t.setDate(date);
            this.t.setLayout(newTournamentFormat);
            /*
            RegistryClient.tournamentRegistry.getTournament(tournamentID).setName(tournamentName);
            RegistryClient.tournamentRegistry.getTournament(tournamentID).setDate(date);
            RegistryClient.tournamentRegistry.getTournament(tournamentID).setLayout(newTournamentFormat);

             */
            //Saves all changes to the tournament.
            this.f.updateTournament(this.t);
            //loads new fxml file
            this.gotoAdminManageTournament(event);
        }
    }

    /**
     * method that calls on the fxmlloaderClass to load the knockout bracket fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToKnockoutBracket(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToKnockoutBracket(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load the round robin fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToRoundRobinBracket(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToRoundRobinBracket(actionEvent);
    }

    /**
     * method that calls on the fxmlloaderClass to load the swiss bracket fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void goToSwissBracket(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToSwissBracket(actionEvent);
    }

    /**
     * method that determines which fxml should be loaded when user clicks to go to the selected tournament bracket
     * @param actionEvent event
     * @throws IOException exception
     */
    public void goToBracket(ActionEvent actionEvent) throws IOException {
        String layout = t.getLayout();
        //String layout = RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getLayout();
        switch (layout) {
            case "Knock-Out" -> {
                AdminViewKnockOutBracketController.setTournamentID(getTournamentID());
                this.goToKnockoutBracket(actionEvent);
            }
            case "Round-Robin" -> this.goToRoundRobinBracket(actionEvent);
            case "Swiss-System" -> this.goToSwissBracket(actionEvent);
            default -> System.err.println("something went wrong");
        }
    }
}
