import ntnu.idatt2001.k2g9.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TournamentRegistryTest {
    @Test
    public void testGetTournament() {
        TournamentRegistry testReg = new TournamentRegistry();
        PlayerRegistry players = new PlayerRegistry();
        players.addPlayer(new Player("Erik1",20));
        players.addPlayer(new Player("Erik2",20));
        players.addPlayer(new Player("Erik3",20));
        players.addPlayer(new Player("Erik4",20));;
        Admin organizer = new Admin("Daniel",19,"daniesky@ntnu.no","123");
        Tournament testT1 = new Tournament("Tournament 1", LocalDate.parse("2022-03-23"), organizer, players);
        Tournament testT2 = new Tournament("Tournament 2", LocalDate.parse("2022-03-22"), organizer, players);
        Tournament testT3 = new Tournament("Tournament 3", LocalDate.parse("2022-03-21"), organizer, players);
        testReg.addTournaments(testT1);
        testReg.addTournaments(testT2);
        testReg.addTournaments(testT3);
        Tournament foundT = testReg.getTournament("Tournament 1",LocalDate.parse("2022-03-23"));
        Assertions.assertEquals(testT1,foundT);
        Assertions.assertNotEquals(testT2,foundT);
        System.out.println(testReg.toString());

    }
}
