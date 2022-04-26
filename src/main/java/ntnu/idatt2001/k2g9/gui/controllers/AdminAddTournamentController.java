package ntnu.idatt2001.k2g9.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ntnu.idatt2001.k2g9.file.FileHandler;
import ntnu.idatt2001.k2g9.gui.FXMLLoaderClass;
import ntnu.idatt2001.k2g9.gui.models.CompetitorModel;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
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

    /**
     * Sets current tournament format to knockout, which will be stored and used for registration
     */
    public void setFormatKnockout() {
        this.inpTournamentFormat.setText("Knock-Out");
        this.tournamentFormat = "Knock-Out";
    }

    /**
     * Sets current tournament format to Swiss, which will be stored and used for registration
     */
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
    public void addTournament(ActionEvent event) throws IOException {
        String tournamentName = inpTournamentName.getText();
        //will be null if a format is not selected in the application
        String tournamentFormat = this.tournamentFormat;
        LocalDate date = this.inpDate.getValue();
        if (this.isDateValid(date)){
            Tournament newTournament = new Tournament(tournamentName, date, tournamentFormat);
            newTournament.addFromList(playerRegistry.getPlayers());
            newTournament.createTournamentBracket();
            FileHandler f =  new FileHandler();
            f.writeTournamentToFile(newTournament);

            //clears player arraylist so it can be
            playerRegistry.getPlayers().clear();

            //clear competitor table
            clearCompetitorTable();

            //resets input fields
            resetInputFields();

            //calls fxml loader method
            this.gotoAdminManageTournament(event);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning! Date cannot be added.");
            alert.setHeaderText(null);
            alert.setContentText("You have chosen a date that has passed. Please choose a date in the future.");
            alert.showAndWait();
        }
    }

    /**
     * method that resets all input textfields in application
     */
    public void resetInputFields(){
        this.inpFullName.setText("");
        this.inpAge.setText("");
        this.inpTournamentName.setText("");
        this.inpTournamentFormat.setText("");
        this.inpDate.getEditor().setText("");
    }

    /**
     * clear the table of competitors in the application
     */
    public void clearCompetitorTable(){
        ObservableList<CompetitorModel> allCompetitors;
        allCompetitors = tableCompetitors.getItems();
        allCompetitors.clear();
    }
}
