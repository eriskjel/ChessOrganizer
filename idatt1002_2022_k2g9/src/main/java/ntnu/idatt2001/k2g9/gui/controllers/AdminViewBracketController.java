package ntnu.idatt2001.k2g9.gui.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ntnu.idatt2001.k2g9.gui.application.Application;
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

    HashMap<String, Button> buttonHashMap;
    public TextField tournamentWinner;
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
        Button button = (Button) event.getSource();
        String fxid = button.getId();

        //get round, match and winner player info
        int roundIndex = Integer.parseInt(fxid.substring(1,2));
        int matchIndex = Integer.parseInt(fxid.substring(2,3));
        int playerIndex = Integer.parseInt(fxid.substring(3,4));

        Tournament tournament = RegistryClient.tournamentRegistry.getTournament(tournamentID);
        ArrayList<Match[]> bracket = tournament.getTournamentBracket();


        //Checks if the current player is in the final round.
        if (roundIndex+1 == tournament.getTotalRounds()){
            if (bracket.get(roundIndex)[matchIndex].getWinner() != null && bracket.get(roundIndex)[matchIndex].getWinner().getName() == button.getText()) {
                tournamentWinner.setText("");
                bracket.get(roundIndex)[matchIndex].setWinner(null);
            }
            else if (playerIndex == 0){
                if (buttonHashMap.get("x" + roundIndex + matchIndex + (playerIndex + 1)).getText().isEmpty()){
                    button.setText("");
                    bracket.get(roundIndex-1)[matchIndex*2+playerIndex].setWinner(null);
                }
                else {
                    bracket.get(roundIndex)[matchIndex].setWinner(bracket.get(roundIndex)[matchIndex].getPlayer1());
                    tournamentWinner.setText(bracket.get(roundIndex)[matchIndex].getWinner().getName());
                }
            }
            else if(playerIndex == 1){
                if(buttonHashMap.get("x" + roundIndex + matchIndex + (playerIndex - 1)).getText().isEmpty()){
                    button.setText("");
                    bracket.get(roundIndex-1)[matchIndex*2+playerIndex].setWinner(null);
                }
                else {
                    bracket.get(roundIndex)[matchIndex].setWinner(bracket.get(roundIndex)[matchIndex].getPlayer2());
                    tournamentWinner.setText(bracket.get(roundIndex)[matchIndex].getWinner().getName());
                }
            }
        }
        //Checks if winner for that round is already assigned. If it is, it'll instead move the player back to previous bracket.
        else if (!(bracket.get(roundIndex)[matchIndex].getWinner()==null)
                && bracket.get(roundIndex)[matchIndex].getWinner().getName().equals(button.getText())){
            bracket.get(roundIndex)[matchIndex].setWinner(null);
            buttonHashMap.get("x"+(roundIndex+1)+ "" + (int) (matchIndex/2) + "" + matchIndex%2).setText("");
        }

        else if(playerIndex == 0) {
            if(buttonHashMap.get("x" + roundIndex + matchIndex + (playerIndex + 1)).getText().isEmpty()){
                //If a player1 is clicked while player2 of match hasn't been assigned, player1 will be moved back.
                button.setText("");
                bracket.get(roundIndex-1)[matchIndex*2+playerIndex].setWinner(null);
            }
            else if (matchIndex%2 == 0){
                //If player 1 is the winner, and they're in an even match, they get sent to the match in next round as player 1
                bracket.get(roundIndex)[matchIndex].setWinner(bracket.get(roundIndex)[matchIndex].getPlayer1());
                bracket.get(roundIndex+1)[(int) (matchIndex/2)].setPlayer1(bracket.get(roundIndex)[matchIndex].getPlayer1());
            }
            else {//If player 1 is the winner, and they're in an even match, they get sent to the match in next round as player 2
                bracket.get(roundIndex)[matchIndex].setWinner(bracket.get(roundIndex)[matchIndex].getPlayer1());
                bracket.get(roundIndex + 1)[(int) ( matchIndex / 2 )].setPlayer2(bracket.get(roundIndex)[matchIndex].getPlayer1());
            }
            buttonHashMap.get("x"+(roundIndex+1)+ "" + (int) (matchIndex/2) + "" + matchIndex%2).setText(button.getText());
        }
        else if(playerIndex == 1) {
            if(buttonHashMap.get("x" + roundIndex + matchIndex + (playerIndex - 1)).getText().isEmpty()){
                //If a player2 is clicked while player1 of match hasn't been assigned, player2 will be moved back.
                button.setText("");
                bracket.get(roundIndex-1)[matchIndex*2+playerIndex].setWinner(null);
            }
            else if (matchIndex%2 == 0){
                //If player 2 is the winner, and they're in an even match, they get sent to the match in next round as player 1
                bracket.get(roundIndex)[matchIndex].setWinner(bracket.get(roundIndex)[matchIndex].getPlayer2());
                bracket.get(roundIndex+1)[(int) (matchIndex/2)].setPlayer1(bracket.get(roundIndex)[matchIndex].getPlayer2());
            }
            else {
                //If player 2 is the winner, and they're in an odd match, they get sent to the match in next round as player 2
                bracket.get(roundIndex)[matchIndex].setWinner(bracket.get(roundIndex)[matchIndex].getPlayer2());
                bracket.get(roundIndex+1)[(int) (matchIndex/2)].setPlayer2(bracket.get(roundIndex)[matchIndex].getPlayer2());
            }
            buttonHashMap.get("x"+(roundIndex+1)+ "" + (int) (matchIndex/2) + "" + matchIndex%2).setText(button.getText());
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        //Sets all buttons visible and enabled again in case they were disabled on last init.
        for (int i = 0 ; i < buttons.length ; i++){
            buttons[i].setVisible(true);
            buttons[i].setDisable(false);
        }

        //Gets tournament and bracket.
        Tournament tournament = RegistryClient.tournamentRegistry.getTournament(tournamentID);
        tournament.createTournamentBracket();
        ArrayList<Match[]> bracket = tournament.getTournamentBracket();
        int totalRounds = tournament.getTotalRounds();
        
        
        /**
         * This loop overwrites the statically assigned ids of the bracket layout with dynamically reassigned ones.
         * The loop reassigns the ids to the buttons in a way that makes all matches meet in the middle.
         * Since the ids are reassigned to the buttons dynamically, they will always meet in the middle of the screen,
         * regardless of the number of matches and number of players.
         * This fixes the problem of the bracket filling in from the upper left corner.
         * Rather than having 3 player tournaments placed in the upper left corner, they will meet in the middle.
         *
         */
        int currentRound = tournament.getTotalRounds();
        int currentMatch = 0;
        int currentPlayer = 1;
        for (int i = buttons.length-1 ; i >= 0 ; i--){
             if (currentRound > 0){
                 buttons[i].setId("x"
                         + Integer.toString(currentRound-1)
                         + Integer.toString(currentMatch)
                         + Integer.toString(currentPlayer));

                 if(currentPlayer == 0) currentMatch--;

                 currentPlayer = (currentPlayer == 1 ? 0 : 1);

                 if(currentMatch < 0){
                    currentRound--;
                    currentMatch = (int) Math.pow(2, totalRounds-currentRound)-1;
                 }
             }
             else {
                 buttons[i].setId("x0");
             }
        }
        /**
         * Creates hashmap of the buttons that will be used, any button with id x0 will be ignored.
         */
        buttonHashMap = new HashMap<>();
        for (Button button : buttons) {
            if (!button.getId().equals("x0")){
                buttonHashMap.put(button.getId() , button);
        }
        }
        
        //Fills in the bracket using the button hashmap.
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
        
        //Sets all buttons no longer in use invisible.
        for (int i = 0 ; i < buttons.length ; i++){
            if (buttons[i].getText().equals("Player 1")) {
                buttons[i].setVisible(false);
                buttons[i].setDisable(true);
            }
        }
    }
}