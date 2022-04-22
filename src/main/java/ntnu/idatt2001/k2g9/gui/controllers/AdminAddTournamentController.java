package ntnu.idatt2001.k2g9.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ntnu.idatt2001.k2g9.gui.application.Application;
import ntnu.idatt2001.k2g9.gui.models.CompetitorModel;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.RegistryClient;
import ntnu.idatt2001.k2g9.tournament.Tournament;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Controller that administers the fxml file "admin-add-tournaments" and handles all the events on said fxml file
 */
public class AdminAddTournamentController implements Initializable {

    @FXML private TextField inpTournamentName;
    @FXML private DatePicker inpDate;
    @FXML private MenuButton inpTournamentFormat;
    @FXML private MenuItem formatKnockout;
    @FXML private TextField inpFullName;
    @FXML private TextField inpAge;
    @FXML private String tournamentFormat;
    @FXML private TableView tableCompetitors;
    @FXML private TableColumn<CompetitorModel, String> tblName;
    @FXML private TableColumn<CompetitorModel, Integer> tblAge;
    @FXML private ObservableList<CompetitorModel> observableList = FXCollections.observableArrayList();
    PlayerRegistry playerRegistry = new PlayerRegistry();

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

    /**
     * Sets current tournament format to knockout, which will be stored and used for registration
     */
    @FXML
    public void setFormatKnockout() {
        this.inpTournamentFormat.setText("Knock-Out");
        this.tournamentFormat = "Knock-Out";
    }

    /**
     * Sets current tournament format to Swiss, which will be stored and used for registration
     */
    @FXML
    public void formatSwiss() {
        this.inpTournamentFormat.setText("Swiss-System");
        this.tournamentFormat = "Swiss-System";
    }

    /**
     * Method that runs when fxml file is loaded
     * This particular override
     * @param url url
     * @param resourceBundle url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tblName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.tblAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        this.tableCompetitors.setItems(observableList);



        //TESTDATA
        for (int i = 0; i < 10; i++) {
            Player player = new Player("player"+(i+1), (i + 10));
            playerRegistry.addPlayer(player);
        }
        LocalDate date = LocalDate.parse("2020-06-14");
        Tournament newTournament = new Tournament("testTournament", date, "Knock-Out");
        newTournament.addFromList(playerRegistry.getPlayers());
        newTournament.createTournamentBracket();

        RegistryClient.tournamentRegistry.addTournaments(newTournament);
    }

    /**
     * Method that adds registered competitor to the table in GUI.
     */
    public void addCompetitor() {
        Player player = new Player(this.inpFullName.getText(), Integer.parseInt(this.inpAge.getText()));
        playerRegistry.addPlayer(player);
        //object to be added to table in GUI.
        CompetitorModel competitor = new CompetitorModel(this.inpFullName.getText(), Integer.parseInt(this.inpAge.getText()), player.getPlayerID());
        tableCompetitors.getItems().add(competitor);



        //resets input fields
        this.inpFullName.setText("");
        this.inpAge.setText("");
    }

    /**
     * Method that removes the selected competitor from the table in the application
     */
    public void removeCompetitor(){
        ObservableList<CompetitorModel> allCompetitors, singleCompetitor;
        allCompetitors = tableCompetitors.getItems();
        singleCompetitor = tableCompetitors.getSelectionModel().getSelectedItems();

        Player player = new Player(singleCompetitor.get(0).getName(), singleCompetitor.get(0).getAge());
        player.setPlayerID(singleCompetitor.get(0).getCompetitorID());

        playerRegistry.removePlayer(player);
        singleCompetitor.forEach(allCompetitors::remove);
    }

    /**
     * checks if the date is valid. returns true if date is after current date
     * @return true if date is valid, meaning the date has not already passed, false if otherwise
     */
    public boolean isDateValid(LocalDate date){
        return !date.isBefore(LocalDate.now());
    }

    /**
     * Method that gets all the data from the input field when a user clicks the add tournament button
     */
    @FXML
    public void addTournament(ActionEvent event) throws IOException {
        String tournamentName = inpTournamentName.getText();
        //will be null if a format is not selected in the application
        String tournamentFormat = this.tournamentFormat;
        LocalDate date = this.inpDate.getValue();
        if (this.isDateValid(date)){
            //RegistryClient.tournamentRegistry.addTournament();
            Tournament newTournament = new Tournament(tournamentName, date, tournamentFormat);
            newTournament.addFromList(playerRegistry.getPlayers());
            newTournament.createTournamentBracket();

            RegistryClient.tournamentRegistry.addTournaments(newTournament);

            //clears player arraylist so it can be
            playerRegistry.getPlayers().clear();

            //clear competitor table
            ObservableList<CompetitorModel> allCompetitors;
            allCompetitors = tableCompetitors.getItems();
            allCompetitors.clear();


            //resets input fields
            this.inpFullName.setText("");
            this.inpAge.setText("");
            this.inpTournamentName.setText("");
            this.inpTournamentFormat.setText("");
            this.inpDate.getEditor().setText("");
            gotoAdminManageTournament(event);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning! Date cannot be added.");
            alert.setHeaderText(null);
            alert.setContentText("You have chosen a date that has passed. Please choose a date in the future.");
            alert.showAndWait();
        }
    }
}
