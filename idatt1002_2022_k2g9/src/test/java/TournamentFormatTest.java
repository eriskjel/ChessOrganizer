import ntnu.idatt2001.k2g9.tournament.Match;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.TournamentFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author Navid Muradi
 * @project idatt1002_2022_k2g9
 */
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
            players.addPlayerObject(new Player("Erik"+Integer.toString(i),20+i));
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

        for(int i = 0 ; i<6 ; i++){
            players.addPlayerObject(new Player("Erik"+Integer.toString(i+1),20+i));
        }

        ArrayList<Match[]> layout = TournamentFormat.createBracket("Round-robin", players);


    }

    @Test
    void roundRobinBracketFilledCorrectly() {
        PlayerRegistry players = new PlayerRegistry();

        for (int i = 0; i < 7; i++) {
            players.addPlayerObject(new Player("Test" + Integer.toString(i) , 20 + i));
        }

        ArrayList<Match[]> layout = TournamentFormat.createBracket("Round-Robin" , players);
    }
}
