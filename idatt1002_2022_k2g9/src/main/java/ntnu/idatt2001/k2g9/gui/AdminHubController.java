package ntnu.idatt2001.k2g9.gui;

//import com.gluonhq.charm.glisten.control.*;
//import com.gluonhq.charm.glisten.control.TextField;
//import com.gluonhq.charm.glisten.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class AdminHubController {
    @FXML
    public TextField inpTournamentName;
    public DatePicker inpDate;
    public MenuButton inpTournamentFormat;
    public MenuItem formatKnockout;
    public TextField inpFullName;
    public TextField inpAge;
    public TableView tableCompetitors;
    public TableColumn tblName;
    public TableColumn tblAge;
    public Button hubBtnTournamentHub;
    public Button addTournamentBtnTournamentHub;
    private Stage stage;


    private String tournamentFormat;
    TableView<CompetitorModel> table;
    ObservableList<CompetitorModel> data;


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

    @FXML
    public void goToAdminTournamentHub(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-hub.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Tournament hub");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void addTournament(ActionEvent actionEvent) {
        String tournamentName = inpTournamentName.getText();
        //will be null if a format is not selected in the application
        String tournamentFormat = this.tournamentFormat;
        LocalDate date = inpDate.getValue();

        System.out.println(tournamentName + ", " + tournamentFormat  + ", " + date);
    }

    @FXML
    public void setFormatKnockout(ActionEvent actionEvent) {
        this.inpTournamentFormat.setText("Knockout");
        this.tournamentFormat = "Knockout";
    }

    @FXML
    public void formatSwiss(ActionEvent actionEvent) {
        this.inpTournamentFormat.setText("Swiss");
        this.tournamentFormat = "Swiss";
    }

    public void addCompetitor(ActionEvent actionEvent) {
        System.out.println(this.tblName);
        data = FXCollections.observableArrayList(
                new CompetitorModel(this.inpFullName.getText(), Integer.parseInt(this.inpAge.getText()))
        );
        this.tableCompetitors.setItems(data);
        this.tableCompetitors.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //this.tableCompetitors.getColumns().addAll()
    }

    @FXML
    public void updateTable(ActionEvent event){
        //codee
    }

    public void adminLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Chess tournament organizer");
        stage.setScene(scene);
        stage.show();
    }

    public void gotoAdminManageTournament(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-manage-tournaments.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Tournament hub");
        stage.setScene(scene);
        stage.show();
    }

    public void newTournament(ActionEvent actionEvent) {
        //add code
    }

        /*
        public void SwitchToScene2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
    }
     */
}