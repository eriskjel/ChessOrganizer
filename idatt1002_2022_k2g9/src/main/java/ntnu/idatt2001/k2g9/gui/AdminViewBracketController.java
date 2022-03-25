package ntnu.idatt2001.k2g9.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.tournament.Match;
import ntnu.idatt2001.k2g9.tournament.RegistryClient;
import ntnu.idatt2001.k2g9.tournament.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller that administers the fxml file "admin-hub-tournaments" and handles all the events on said fxml file
 */
public class AdminViewBracketController implements Initializable {


    public Button x000;
    public Button x010;
    public Button x001;
    public Button x020;
    public Button x100;
    public Button x201;
    public Button x031;
    public Button x011;
    public Button x021;
    public Button x030;
    public Button x101;
    public Button x200;
    public Button x111;
    public Button x110;
    public Button x040;
    public Button x050;
    public Button x041;
    public Button x060;
    public Button x071;
    public Button x051;
    public Button x061;
    public Button x070;
    public Button x120;
    public Button x121;
    public Button x131;
    public Button x130;
    public Button x210;
    public Button x211;
    public Button x300;
    public Button x301;

    public static int tournamentID;

    public static void setTournamentID(int id){
        tournamentID = id;
    }

    public int getTournamentID(){
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


    public void setWinnerKnockout(ActionEvent event) {
        //get fx id from button clicked
        Button label = (Button) event.getSource();
        String fxid = label.getId();

        //get round, match and winner player info
        int round = Integer.parseInt(fxid.substring(1,2));
        int match = Integer.parseInt(fxid.substring(2,3));
        int player = Integer.parseInt(fxid.substring(3,4));

        Tournament tournament = RegistryClient.tournamentRegistry.getTournament(tournamentID);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get tournament
        Tournament tournament = RegistryClient.tournamentRegistry.getTournament(tournamentID);
        for (int a = 0; a< 10; a++){
            tournament.getPlayers().addPlayer("abc"+Integer.toString(a),1);
        }
        tournament.createTournamentBracket();
        //all buttons
        Button[] buttons = {
                this.x000,
                this.x001,
                this.x010,
                this.x011,
                this.x020,
                this.x021,
                this.x030,
                this.x031,
                this.x040,
                this.x041,
                this.x050,
                this.x051,
                this.x060,
                this.x061,
                this.x070,
                this.x071,
                this.x100,
                this.x101,
                this.x110,
                this.x111,
                this.x120,
                this.x121,
                this.x130,
                this.x131,
                this.x200,
                this.x201,
                this.x210,
                this.x211,
                this.x300,
                this.x301
        };

        HashMap<String, Button> buttonHashMap = new HashMap<>();
        for (Button button : buttons){
            buttonHashMap.put(button.getId(), button);
        }
        ArrayList<Match[]> bracket = tournament.getTournamentBracket();
        int totalRounds = tournament.getTotalRounds();


        for (int i = 0 ; i < totalRounds ; i++){
            int matchesInRound = bracket.get(i).length;
            for (int n = 0 ; n < matchesInRound ; n++){
                if (i > 0) {
                    buttonHashMap.get("x" + i + n + 0).setText("");
                    buttonHashMap.get("x" + i + n + 1).setText("");
                }

                if (!(tournament.getTournamentBracket().get(i)[n].getPlayer1() == null))
                    buttonHashMap.get("x"+i+n+0).setText(bracket.get(i)[n].getPlayer1().getName());

                if (!(tournament.getTournamentBracket().get(i)[n].getPlayer2() == null))
                    buttonHashMap.get("x"+i+n+1).setText(bracket.get(i)[n].getPlayer2().getName());
            }
        }

        for (int i = 0 ; i < buttons.length ; i++){
            if (buttons[i].getText().equals("Player 1")) {
                System.out.println("Found button");
                buttons[i].setVisible(false);
                buttons[i].setDisable(true);
            }
        }
    }
}