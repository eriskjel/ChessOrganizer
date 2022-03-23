import ntnu.idatt2001.k2g9.Match;
import ntnu.idatt2001.k2g9.Player;
import ntnu.idatt2001.k2g9.PlayerRegistry;
import ntnu.idatt2001.k2g9.TournamentFormat;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author Navid Muradi
 * @project idatt1002_2022_k2g9
 */
class TournamentFormatTest {
    @Test
    void tournamentFormatCreatesCorrect() {
        PlayerRegistry players = new PlayerRegistry();

        for(int i = 0 ; i<20 ; i++){
            players.addPlayer(new Player("eriskjel@ntnu.no","456"+Integer.toString(i),"Erik"+Integer.toString(i), 300,20,"Dirt",0,100));
        }

        ArrayList<Match[]> layout = TournamentFormat.createRounds("Knock-Out", players);

        for(int i = 0 ; i<layout.size() ; i++){
            for (Match match : layout.get(i)){
                try {
                    System.out.println(match.toString());
                }
                catch(NullPointerException ex){

                }
            }
        }
    }
}
