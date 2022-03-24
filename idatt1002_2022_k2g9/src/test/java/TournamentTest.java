import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.player.User;
import ntnu.idatt2001.k2g9.tournament.Match;
import ntnu.idatt2001.k2g9.tournament.Tournament;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class TournamentTest {

    @Test
    public void tournamentHandlesKnockoutTournaments(){
        PlayerRegistry players = new PlayerRegistry();

        for(int i = 0 ; i<20 ; i++){
            players.addPlayerObject(new Player("Erik"+Integer.toString(i),20+i));
        }

        User user = new User("Erik",20,"eriskje@ntnu.no","123");

        Tournament tournament = new Tournament("test", LocalDate.now(), user, players, "Knock-Out");

        tournament.createTournamentBracket();

        for (int i = 0 ; i < tournament.getTournamentBracket().get(0).length ; i++){
            tournament.setMatchWinnerKnockout(0,i,tournament.getTournamentBracket().get(0)[i].getPlayer1());
        }

        int playersMovedUpBracketProperly = 0;
        for (int i = 0 ; i < tournament.getTournamentBracket().get(0).length ; i++){
            if (i%2 == 0 && tournament.getTournamentBracket().get(1)[(int) i/2].getPlayer1() == tournament.getTournamentBracket().get(0)[i].getWinner()) {
                playersMovedUpBracketProperly++;
            }
            else if (i%2 == 1 && tournament.getTournamentBracket().get(1)[(int) i/2].getPlayer2() == tournament.getTournamentBracket().get(0)[i].getWinner()) {
                playersMovedUpBracketProperly++;
            }
        }

        Assertions.assertEquals(playersMovedUpBracketProperly, tournament.getTournamentBracket().get(0).length);
    }
}
