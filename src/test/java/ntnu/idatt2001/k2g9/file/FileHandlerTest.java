package ntnu.idatt2001.k2g9.file;

import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.Tournament;
import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.io.File;
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

        Tournament tournament = new Tournament("TournamentTestOne", LocalDate.now(), "Knock-Out");
        tournament.setPlayers(players);
        tournament.createTournamentBracket();

        FileHandler fileHandler = new FileHandler();
        fileHandler.writeTournamentToFile(tournament);

        String name = tournament.getName() + tournament.getTournamentID() + ".json";
        File file = new File("src/main/resources/ntnu/idatt2001/k2g9/gui/registry/tournaments/" + name);

        assertTrue(file.exists());
        //deletes files after test
        file.delete();



        Tournament tournament1 = new Tournament("TournamentTestTwo", LocalDate.now(), "Swiss-System");
        tournament1.setPlayers(players);
        tournament1.createTournamentBracket();

        fileHandler.writeTournamentToFile(tournament1);

        String name1 = tournament1.getName() + tournament1.getTournamentID() + ".json";
        File file1 = new File("src/main/resources/ntnu/idatt2001/k2g9/gui/registry/tournaments/" + name1);


        assertTrue(file1.exists());
        //delete file after test
        file1.delete();


    }


    @Test
    public void readFromFile() throws IOException {
        PlayerRegistry players = new PlayerRegistry();

        for(int i = 0 ; i < 5 ; i++){
            players.addPlayer(new Player("Test"+ Integer.toString(i),20+i));
        }

        Tournament tournament = new Tournament("TournamentTestOne", LocalDate.now(), "Knock-Out");
        tournament.setPlayers(players);
        tournament.createTournamentBracket();



        FileHandler fileHandler = new FileHandler();
        fileHandler.writeTournamentToFile(tournament);


        assertEquals(tournament, fileHandler.readTournamentFromFile(fileHandler.getTournamentFilePath(tournament)));

        //deletes file after use
        File file = new File("src/main/resources/ntnu/idatt2001/k2g9/gui/registry/tournaments/" + fileHandler.getTournamentFilePath(tournament));
        file.delete();
    }


}