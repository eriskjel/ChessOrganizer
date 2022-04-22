package ntnu.idatt2001.k2g9.gui.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polyline;
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
public class AdminViewKnockOutBracketController implements Initializable {


    private HashMap<String, Button> buttonHashMap;
    @FXML private Label lblTournamentName;
    @FXML private TextField tournamentWinner;
    @FXML private Button x000;
    @FXML private Button x010;
    @FXML private Button x001;
    @FXML private Button x020;
    @FXML private Button x100;
    @FXML private Button x201;
    @FXML private Button x031;
    @FXML private Button x011;
    @FXML private Button x021;
    @FXML private Button x030;
    @FXML private Button x101;
    @FXML private Button x200;
    @FXML private Button x111;
    @FXML private Button x110;
    @FXML private Button x040;
    @FXML private Button x050;
    @FXML private Button x041;
    @FXML private Button x060;
    @FXML private Button x071;
    @FXML private Button x051;
    @FXML private Button x061;
    @FXML private Button x070;
    @FXML private Button x120;
    @FXML private Button x121;
    @FXML private Button x131;
    @FXML private Button x130;
    @FXML private Button x210;
    @FXML private Button x211;
    @FXML private Button x300;
    @FXML private Button x301;
    @FXML private Polyline x00;
    @FXML private Polyline x01;
    @FXML private Polyline x02;
    @FXML private Polyline x03;
    @FXML private Polyline x04;
    @FXML private Polyline x05;
    @FXML private Polyline x06;
    @FXML private Polyline x07;
    @FXML private Polyline x10;
    @FXML private Polyline x11;
    @FXML private Polyline x12;
    @FXML private Polyline x13;
    @FXML private Polyline x20;
    @FXML private Polyline x21;
    @FXML private Polyline x30;
    private static int tournamentID;

    /**
     * setter for tournamentID
     * other controllers can pass the tournamentID to this controller using this method
     * meaning that this controller can read the info from said tournament by using the ID
     * @param id tournament id
     */
    public static void setTournamentID(int id){
        tournamentID = id;
    }

    /**
     * getter for tournamentID
     * @return tournament id
     */
    public int getTournamentID(){
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
     * method that calls on the fxmlloaderClass to load the login fxml file
     * @param actionEvent action event
     * @throws IOException io exception
     */
    public void adminLogOut(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.adminLogOut(actionEvent);
    }

    //TODO: add javadoc @navid muradi
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

    /**
     * Method for filling knockout bracket page with all the matches of the tournament.
     *
     * @param tournament The tournament to be visualized.
     */
    public void fillKnockoutBracketPage(Tournament tournament){
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

        Polyline[] polylines = {
            this.x00,
            this.x01,
            this.x02,
            this.x03,
            this.x04,
            this.x05,
            this.x06,
            this.x07,
            this.x10,
            this.x11,
            this.x12,
            this.x13,
            this.x20,
            this.x21,
            this.x30
        };

        //Sets all buttons visible and enabled again in case they were disabled on last init.
        for (int i = 0 ; i < buttons.length ; i++){
            buttons[i].setVisible(true);
            buttons[i].setDisable(false);
        }

        int totalRounds = tournament.getTotalRounds();
        int totalMatches = tournament.getPlayerRegistry().getSize() - 1;
        ArrayList<Match[]> bracket = tournament.getTournamentBracket();

        /**
         * Creates hashmap of the polyline objects.
         */
        HashMap<String, Polyline> polylineHashMap = new HashMap<>();
        for (Polyline polyline : polylines) {
            polylineHashMap.put(polyline.getId() , polyline);
        }

        /**
         * Sets polylines of empty matches invisible.
         */
        for(Polyline polyline : polylines){
            int roundNo = Integer.parseInt(polyline.getId().substring(1,2));
            int matchNo = Integer.parseInt(polyline.getId().substring(2)) + 1;
            if (Math.pow(2,4)-Math.pow(2,4-roundNo)-1+matchNo < 15 - totalMatches) {
                polylineHashMap.get("x"+ Integer.toString(roundNo) + Integer.toString((int) Math.pow(2,3-roundNo) - matchNo)).setVisible(false);
            }
        }

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

        //Sets all buttons not in use invisible.
        for (int i = 0 ; i < buttons.length; i++){
            if (buttons[i].getText().equals("Player 1")) {
                buttons[i].setVisible(false);
                buttons[i].setDisable(true);
            }
        }
    }

    /**
     * runs when fxml file is loaded. sets the header name with the corresponding tournament name
     * and calls the fill bracket method
     * @param url url
     * @param resourceBundle bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //sets header with correct tournament name
        this.lblTournamentName.setText(RegistryClient.tournamentRegistry.getTournament(getTournamentID()).getName());

        //Gets tournament and bracket.
        Tournament tournament = RegistryClient.tournamentRegistry.getTournament(getTournamentID());
        this.fillKnockoutBracketPage(tournament);
    }
}