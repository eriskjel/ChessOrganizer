import ntnu.idatt2001.k2g9.tournament.Match;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.TournamentFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
class TournamentFormatTest {
    @Test
    void knockOutLayoutIsAsExpected() {
        PlayerRegistry players = new PlayerRegistry();

        for(int i = 0 ; i<20 ; i++){
            players.addPlayerObject(new Player("Erik"+Integer.toString(i),20+i));
        }

        ArrayList<Match[]> layout = TournamentFormat.createBracket("Knock-Out", players);

        for (Match[] match: layout
        ) {
            System.out.println(match);
        }

        Assertions.assertTrue(layout.get(0).length == 4
                                & layout.get(1).length == 8
                                & layout.get(2).length == 4
                                & layout.get(3).length == 2
                                & layout.get(4).length == 1);
    }

    @Test
    void knockOutBracketFilledCorrectly() {
        PlayerRegistry players = new PlayerRegistry();

        for(int i = 0 ; i<20 ; i++){
            players.addPlayerObject(new Player("Test"+Integer.toString(i),20+i));
        }

        ArrayList<Match[]> layout = TournamentFormat.createBracket("Knock-Out", players);

        int playersPlacedInPreliminary = 0;
        for(int i = 0 ; i < layout.get(0).length ; i++){
            playersPlacedInPreliminary += layout.get(0)[i].playersInitialized();
        }

        int playersPlacedInFirstRound = 0;
        for(int i = layout.get(1).length-1 ; i >= playersPlacedInPreliminary/4 ; i--){
            playersPlacedInFirstRound += layout.get(1)[i].playersInitialized();
        }

        Assertions.assertTrue(playersPlacedInPreliminary == 8 && playersPlacedInFirstRound == 12);
    }

    @Test
    void roundRobinLayoutIsAsExpected() {
        PlayerRegistry players = new PlayerRegistry();

        for(int i = 0 ; i<7 ; i++){
            players.addPlayerObject(new Player("Erik"+Integer.toString(i+1),20+i));
        }

        ArrayList<Match[]> layout = TournamentFormat.createBracket("Round-Robin", players);

        //test to see how many rounds will be played
        //should be participants.size()-1, which is 7, with (7 competitors + 1 dummy "Bye") - 1
        Assertions.assertEquals(layout.size(),7);
        Assertions.assertNotEquals(layout.size(),8);
        Assertions.assertNotEquals(layout.size(),5);

        //test to see how many matches there'll be in each round
        //should be participants.size()/2, which is 4 in each round, since there's (7 competitors + 1 dummy "Bye")/2
        Assertions.assertTrue(layout.get(0).length == 4
                                    && layout.get(1).length == 4
                                    && layout.get(2).length == 4
                                    && layout.get(3).length == 4
                                    && layout.get(4).length == 4
                                    && layout.get(5).length == 4
                                    && layout.get(6).length == 4);
    }

    @Test
    void roundRobinBracketFilledCorrectly() {
        PlayerRegistry players = new PlayerRegistry();

        for (int i = 0; i < 7; i++) {
            players.addPlayerObject(new Player("Test" + Integer.toString(i) , 20 + i));
        }

        ArrayList<Match[]> layout = TournamentFormat.createBracket("Round-Robin" , players);

        int playersInRoundOne = 0;
        for (int i = 0; i <layout.get(0).length ; i++) {
            playersInRoundOne += layout.get(0)[i].playersInitialized();
        }

        int playersInRoundTwo = 0;
        for (int i = 0; i <layout.get(0).length ; i++) {
            playersInRoundTwo += layout.get(1)[i].playersInitialized();
        }

        Assertions.assertEquals(playersInRoundOne, playersInRoundTwo);

    }

    @Test
    void swissSystemLayoutIsAsExpected() {
        PlayerRegistry players = new PlayerRegistry();
        int log2base = 0;
        int matches = 0;

        for(int i = 0 ; i<15 ; i++){
            players.addPlayerObject(new Player("Erik"+Integer.toString(i+1),20+i));
        }

        //if amount of players % == 0, adds one dummy to the sum of players
        // when calculating amount of rounds and matches
        if (players.getSize() % 2 == 0){
            log2base = (int) (Math.log(players.getSize()) / Math.log(2));
            matches = players.getSize()/2;
        }else {
            log2base = (int) (Math.log(players.getSize()+1) / Math.log(2));
            matches = (players.getSize()+1)/2;
        }

        ArrayList<Match[]> layout = TournamentFormat.createBracket("Swiss-System", players);

        Assertions.assertEquals(layout.size(),log2base);
        Assertions.assertNotEquals(layout.size(),log2base + 1);
        Assertions.assertNotEquals(layout.size(),log2base - 1);

        for (int i = 0; i < log2base; i++) {
            Assertions.assertEquals(layout.get(i).length, matches);
        }

    }

    @Test
    void swissSystemBracketFilledCorrectly() {
        PlayerRegistry players = new PlayerRegistry();

        for (int i = 0; i < 33; i++) {
            players.addPlayerObject(new Player("Test" + Integer.toString(i) , 20 + i));
        }

        ArrayList<Match[]> layout = TournamentFormat.createBracket("Swiss-System" , players);

        int playersInRoundOne = 0;
        for (int i = 0; i <layout.get(0).length ; i++) {
            playersInRoundOne += layout.get(0)[i].playersInitialized();
        }
        int playersInRoundTwo = 0;
        for (int i = 0; i <layout.get(0).length ; i++) {
            playersInRoundTwo += layout.get(1)[i].playersInitialized();
        }
        Assertions.assertEquals(playersInRoundOne, playersInRoundTwo);

    }
}
