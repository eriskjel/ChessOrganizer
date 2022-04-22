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
 * Controller that administers the fxml file "admin-add-tournaments" and handels all the events on said fxml file
 */
public class AdminAddTournamentController implements Initializable {

    //fxml variables
    public TextField inpTournamentName;
    public DatePicker inpDate;
    public MenuButton inpTournamentFormat;
    public MenuItem formatKnockout;
    public TextField inpFullName;
    public TextField inpAge;
    @FXML
    private Stage stage;
    @FXML
    private String tournamentFormat;


    //table for handling added competitors
    public TableView tableCompetitors;
    public TableColumn<CompetitorModel, String> tblName;
    public TableColumn<CompetitorModel, Integer> tblAge;
    //list that will be used to feed into table
    ObservableList<CompetitorModel> observableList = FXCollections.observableArrayList();

    //ArrayList<Player> players = new ArrayList<>();
    PlayerRegistry playerRegistry = new PlayerRegistry();

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

        stage.setTitle("Tournament hub");
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
     * Method that gets all the data from the input field when a user clicks the add tournament button
     */
    @FXML
    public void addTournament(ActionEvent event) throws IOException {
        String tournamentName = inpTournamentName.getText();
        //will be null if a format is not selected in the application
        String tournamentFormat = this.tournamentFormat;
        LocalDate date = inpDate.getValue();



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
}
