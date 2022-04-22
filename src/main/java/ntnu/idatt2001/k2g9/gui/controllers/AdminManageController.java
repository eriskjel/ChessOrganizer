package ntnu.idatt2001.k2g9.gui.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ntnu.idatt2001.k2g9.gui.application.Application;
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

    public TableColumn colDate;
    public TableColumn colName;
    public TableColumn colCompetitors;
    public TableColumn colFormat;
    public TableView tblTournaments;
    //list that will be used to feed into table
    ObservableList<CompetitorModel> observableList = FXCollections.observableArrayList();

    //javafx stage
    private Stage stage;

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

        //add events listener to tournament table
        /*
        this.tblTournaments.setOnMouseClicked(mouseEvent -> {
            TournamentModel selectedTournament = (TournamentModel) this.tblTournaments.getSelectionModel().getSelectedItem();
            if (selectedTournament != null) {
                System.out.println(selectedTournament.getTournamentID());
            }
        });

         */

        /*
        this.tblTournaments.setOnMouseClicked(mouseEvent -> {
            TournamentModel selectedTournament = (TournamentModel) this.tblTournaments.getSelectionModel().getSelectedItem();
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                if(mouseEvent.getClickCount() == 2){
                    if (selectedTournament != null){
                        this.path = "knockout-admin-view-bracket.fxml";
                        this.getTournamentID();
                    }
                }
            }
        });

         */
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

    /*
    public void getTournament(){
        ObservableList<TournamentModel> selectedTournament;
        selectedTournament = tblTournaments.getSelectionModel().getSelectedItems();

    }

    @FXML
    public void clickMouse(MouseEvent event){
        ObservableList<TournamentModel> allCompetitors, singleTournament;
        allCompetitors = tblTournaments.getItems();
        singleTournament = tblTournaments.getSelectionModel().getSelectedItems();
        System.out.println(singleTournament);

    }

     */

    public int getSelectedTournamentID(){
        //get selected tournament
        ObservableList<TournamentModel> singleTournament;
        singleTournament = tblTournaments.getSelectionModel().getSelectedItems();

        //get selected tournament ID
        return singleTournament.get(0).getTournamentID();
    }

    public void editTournament(ActionEvent event) throws IOException {
        AdminEditTournamentController.setTournamentID(getSelectedTournamentID());

        //load new fxml file
        goToSpecificTournament(event);

    }


    private void goToSpecificTournament(ActionEvent actionEvent) throws IOException {
        //load new fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-edit-tournament.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Edit tournament");
        stage.setScene(scene);
        stage.show();
    }

    /*
    public void gotoAdminViewBracket(ActionEvent actionEvent) throws IOException {
        //load new fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("knockout-admin-view-bracket.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View brackets tournament");
        stage.setScene(scene);
        stage.show();
    }

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

    public void goToBracket(ActionEvent actionEvent) throws IOException {
        int selectedTournamentID = getSelectedTournamentID();
        if (RegistryClient.tournamentRegistry.getTournament(selectedTournamentID).getLayout().equals("Knock-Out")){
            AdminViewBracketController.setTournamentID(selectedTournamentID);
            goToKnockoutBracket(actionEvent);
        }
        else if(RegistryClient.tournamentRegistry.getTournament(selectedTournamentID).getLayout().equals("Round-Robin")){
            goToRoundRobinBracket(actionEvent);
        }
        else if (RegistryClient.tournamentRegistry.getTournament(selectedTournamentID).getLayout().equals("Swiss-System")){
            goToSwissBracket(actionEvent);
        }
        else{
            System.err.println("something went wrong");
        }
    }
}
