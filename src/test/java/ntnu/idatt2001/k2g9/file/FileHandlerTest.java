package ntnu.idatt2001.k2g9.file;

import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.Tournament;
import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    @Test
    public void writeTournamentToFile() throws IOException {

        PlayerRegistry players = new PlayerRegistry();

        for(int i = 0 ; i<5 ; i++){
            players.addPlayer(new Player("Test"+ Integer.toString(i),20+i));
        }

        Tournament tournament = new Tournament("Test", LocalDate.now(), "Knock-Out");
        tournament.setPlayers(players);
        tournament.createTournamentBracket();

        FileHandler fileHandler = new FileHandler();
        /*
        fileHandler.writeTournamentToFile(tournament);


         */
        Tournament tournament1 = new Tournament("suck ya mum danielski", LocalDate.now(), "Swiss-System");
        tournament1.setPlayers(players);
        tournament1.createTournamentBracket();

        /*
        fileHandler.writeTournamentToFile(tournament1);

         */
    }

    /*
    @Test
    public void readFromFile() throws IOException {
        FileHandler fileHandler = new FileHandler();
        fileHandler.readTournamentFromFile();
    }

     */
}