package ntnu.idatt2001.k2g9.gui;

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
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.RegistryClient;
import ntnu.idatt2001.k2g9.tournament.Tournament;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller that administers the fxml file "admin-add-tournaments" and handels all the events on said fxml file
 */
public class AdminAddTournamentController implements Initializable {

    //fxml variables
    @FXML
    public TextField inpTournamentName;
    public DatePicker inpDate;
    public MenuButton inpTournamentFormat;
    public MenuItem formatKnockout;
    public TextField inpFullName;
    public TextField inpAge;
    private Stage stage;
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
     * Method that loads a new fxml file and sets it a the scene
     * @param actionEvent event
     * @throws IOException
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
     * Method that loads a new fxml and sets it as a new scene
     * @param actionEvent event
     * @throws IOException
     */
    public void gotoAdminManageTournament(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-manage-tournaments.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Tournament hub");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *Method that changed the scene to  a new fxml page
     * @param actionEvent event
     * @throws IOException
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
     *
     *Method that sets tournament format variable based on what is chosen in the selection menu
     */
    @FXML
    public void setFormatKnockout() {
        this.inpTournamentFormat.setText("Knock-Out");
        this.tournamentFormat = "Knock-Out";
    }

    /**
     *
     *Method that sets tournament format variable based on what is chosen in the selection menu
     */
    @FXML
    public void formatSwiss() {
        this.inpTournamentFormat.setText("Swiss");
        this.tournamentFormat = "Swiss";
    }

    /**
     * Method that initializes the method that can edit the competitor table
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
        playerRegistry.addPlayerObject(player);
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
