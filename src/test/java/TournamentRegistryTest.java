import ntnu.idatt2001.k2g9.player.Admin;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.Tournament;
import ntnu.idatt2001.k2g9.tournament.TournamentRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TournamentRegistryTest {
    @Test
    public void testGetTournament() {
        TournamentRegistry testReg = new TournamentRegistry();
        PlayerRegistry players = new PlayerRegistry();
        players.addPlayerObject(new Player("Erik1",20));
        players.addPlayerObject(new Player("Erik2",20));
        players.addPlayerObject(new Player("Erik3",20));
        players.addPlayerObject(new Player("Erik4",20));;
        Tournament testT1 = new Tournament("Tournament 1", LocalDate.parse("2022-03-23"),"Knock-out");
        Tournament testT2 = new Tournament("Tournament 2", LocalDate.parse("2022-03-22"),"Knock-out");
        Tournament testT3 = new Tournament("Tournament 3", LocalDate.parse("2022-03-21"),"Knock-out");
        testReg.addTournaments(testT1);
        testReg.addTournaments(testT2);
        testReg.addTournaments(testT3);
        Tournament foundT = testReg.getTournament(0);
        Assertions.assertEquals(testT1,foundT);
        Assertions.assertNotEquals(testT2,foundT);
        Assertions.assertEquals(0,1);
    }
}
