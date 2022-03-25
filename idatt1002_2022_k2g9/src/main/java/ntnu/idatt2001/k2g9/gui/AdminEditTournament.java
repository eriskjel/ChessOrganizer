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
import ntnu.idatt2001.k2g9.tournament.RegistryClient;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminEditTournament implements Initializable {

    @FXML
    public static int tournamentID;
    public TableView tableCompetitors;
    public TableColumn tblName;
    public TableColumn tblAge;
    public TextField inpFullName;
    public TextField inpAge;
    public TextField inpTournamentName;
    public MenuItem formatKnockout;
    public DatePicker inpDate;
    public MenuButton inpTournamentFormat;
    public String tournamentFormat;
    public Label lblTournamentName;

    ObservableList<CompetitorModel> observableList = FXCollections.observableArrayList();

    public static void setTournamentID(int ID){
        tournamentID = ID;
    }

    public static int getTournamentID(){
        return tournamentID;
    }

    /**
     * stage of application
     */
    private Stage stage;

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

        //this.addTournamentBtnTournamentHub.setStyle("-fx-background-color: #2B78E4 !important;");
        //this.addTournamentBtnTournamentHub.setStyle("-fx-text-fill: red");
        stage.setTitle("Add tournament!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that loads a new fxml file and sets it as the current scene
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
     * Method that loads a new fxml file and sets it as the current scene
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
     * Method that loads a new fxml file and sets it as the current scene
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

    public void refreshData() {
        //clear table beforehand
        ObservableList<CompetitorModel> allCompetitors;
        allCompetitors = this.tableCompetitors.getItems();
        allCompetitors.clear();


        ArrayList<Player> competitors = RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getPlayers().getPlayers();
        ArrayList<CompetitorModel> competitorModels = new ArrayList<>();
        for (Player player : competitors){
            CompetitorModel competitorModel = new CompetitorModel(player);
            competitorModels.add(competitorModel);
        }

        tableCompetitors.getItems().addAll(competitorModels);

        this.updateTurnamentInfo();
        this.lblTournamentName.setText(RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getName());



    }

    //TODO: does not work
    public void removeCompetitor(ActionEvent actionEvent) {
        //removes from table
        ObservableList<CompetitorModel> allCompetitors, singleCompetitor;
        allCompetitors = tableCompetitors.getItems();
        singleCompetitor = tableCompetitors.getSelectionModel().getSelectedItems();
        singleCompetitor.forEach(allCompetitors::remove);

        //removes from tournament
        Player player = new Player(singleCompetitor.get(0).getName(), singleCompetitor.get(0).getAge());
        RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getPlayers().removePlayer(player);

    }

    public void addCompetitor(ActionEvent actionEvent) {
        Player player = new Player(this.inpFullName.getText(), Integer.parseInt(this.inpAge.getText()));
        RegistryClient.tournamentRegistry.getTournament(getTournamentID()).addPlayer(player);

        //object to be added to table in GUI.
        //CompetitorModel competitor = new CompetitorModel(this.tblName.getText(), Integer.parseInt(this.tblAge.getText()), );
        CompetitorModel competitor = new CompetitorModel(player);
        tableCompetitors.getItems().add(competitor);


        //adds player to tournament
        //Player player = new Player(this.tblName.getText(), Integer.parseInt(this.tblAge.getText()));


        //resets input fields
        this.inpFullName.setText("");
        this.inpAge.setText("");
    }

    public void goToBrackets(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-view-bracket.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);
        AdminViewBracketController.setTournamentID(tournamentID);

        stage.setTitle("Tournament hub");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tblName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.tblAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        this.tableCompetitors.setItems(observableList);
        refreshData();
    }

    public void updateTurnamentInfo(){
        this.inpTournamentName.setText(RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getName());
        this.inpTournamentFormat.setText(RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getLayout());
        //this.inpDate.getEditor().setText(RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getDate().toString());
        //this.inpDate.getEditor().setPromptText(RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getDate().toString());
        this.inpDate.setPromptText(RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getDate().toString());
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

    @FXML
    public void saveTournament(){
        String tournamentName = inpTournamentName.getText();
        //will be null if a format is not selected in the application
        String tournamentFormat = this.tournamentFormat;
        LocalDate date = inpDate.getValue();
        if (date == null){
            date = RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getDate();
        }

        RegistryClient.tournamentRegistry.getTournament(tournamentID).setName(tournamentName);
        RegistryClient.tournamentRegistry.getTournament(tournamentID).setDate(date);
        RegistryClient.tournamentRegistry.getTournament(tournamentID).setLayout(tournamentFormat);
    }
}
