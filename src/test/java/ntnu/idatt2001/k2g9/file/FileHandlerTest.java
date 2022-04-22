package ntnu.idatt2001.k2g9.file;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.tournament.Tournament;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    public String testPath = "src/main/resources/ntnu/idatt2001/k2g9/gui/registry/tournaments/TournamentsTest.json";

    @Test
    public void writeAndReadTest() throws IOException, ParseException {
        //Initialize a PlayerRegistry with 5 players to use in the tournament.
        PlayerRegistry players = new PlayerRegistry();
        for(int i = 0 ; i<5 ; i++){
            players.addPlayer(new Player("Test" + i,20+i));
        }

        // Initialize a tournament with the players, the current date and a specific name.
        Tournament tournament = new Tournament("TournamentTestOne", LocalDate.now(), "Knock-Out");
        tournament.setPlayers(players);
        tournament.createTournamentBracket();

        // Create a fileHandler with the specified path to the file used for test writing and reading.
        FileHandler fileHandler = new FileHandler(testPath);
        fileHandler.writeTournamentToFile(tournament);

        // We use the id from the tournament created above to locate the tournament in the JSON file.
        Tournament read = fileHandler.readTournamentFromFile(tournament.getTournamentID());

        //Confirms that the tournament was written correctly.
        Assertions.assertEquals(tournament,read);

        //Run the test for two tournaments, to assure that several tournaments wont cause issues.
        Tournament tournament1 = new Tournament("TournamentTestTwo", LocalDate.now(), "Swiss-System");
        tournament1.setPlayers(players);
        tournament1.createTournamentBracket();
        tournament1.setTournamentID(1);

        fileHandler.writeTournamentToFile(tournament1);
        Tournament read1 = fileHandler.readTournamentFromFile(tournament1.getTournamentID());
        Assertions.assertEquals(tournament1,read1);

        //Overwrites the test file with an empty JSONArray after testing is done.
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray jsonArray = new JSONArray();
        objectMapper.writeValue(new File(testPath),jsonArray);


    }

    @Test

    public void removeTest() throws IOException, ParseException {
        //Initialize a PlayerRegistry with 5 players to use in the tournament.
        PlayerRegistry players = new PlayerRegistry();
        for(int i = 0 ; i<5 ; i++){
            players.addPlayer(new Player("Test" + i,20+i));
        }

        // Initialize a tournament with the players, the current date and a specific name.
        Tournament tournament = new Tournament("TournamentTestOne", LocalDate.now(), "Knock-Out");
        tournament.setPlayers(players);
        tournament.createTournamentBracket();

        // Create a fileHandler with the specified path to the file used for test writing and reading.
        FileHandler fileHandler = new FileHandler(testPath);
        fileHandler.writeTournamentToFile(tournament);

        // We use the id from the tournament created above to locate the tournament in the JSON file.
        Tournament read = fileHandler.readTournamentFromFile(tournament.getTournamentID());

        //Confirms that the tournament was written correctly.
        Assertions.assertEquals(tournament,read);

        fileHandler.removeTournament(tournament.getTournamentID());

        Assertions.assertThrows(IndexOutOfBoundsException.class ,() -> fileHandler.readTournamentFromFile(tournament.getTournamentID()));

    }
    //Testene er unødvendig?
/*
    @Test
    public void readFromFile() throws IOException, ParseException {
        //Initialize a PlayerRegistry with 5 players to use in the tournament.
        PlayerRegistry players = new PlayerRegistry();
        for(int i = 0 ; i < 5 ; i++){
            players.addPlayer(new Player("Test"+ Integer.toString(i),20+i));
        }

        //Create a tournament with the players above.
        Tournament tournament = new Tournament("TournamentTestOne", LocalDate.now(), "Knock-Out");
        tournament.setPlayers(players);
        tournament.createTournamentBracket();

        //Filehandler object created with the testpath.
        FileHandler fileHandler = new FileHandler(testPath);
        fileHandler.writeTournamentToFile(tournament);

        //Confirms that the tournament has been read properly.
        assertEquals(tournament, fileHandler.readTournamentFromFile(0));

        //Overwrites the test file with an empty JSONArray after testing is done.
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray jsonArray = new JSONArray();
        objectMapper.writeValue(new File(testPath),jsonArray);
    }


    @Test
    public void readAndWriteToFile() throws IOException, ParseException {
        PlayerRegistry players = new PlayerRegistry();

        for(int i = 0 ; i<5 ; i++){
            players.addPlayer(new Player("Test" + i,20+i));
        }

        Tournament tournament = new Tournament("TournamentTestOne", LocalDate.now(), "Knock-Out");
        tournament.setPlayers(players);
        tournament.createTournamentBracket();

        FileHandler fileHandler = new FileHandler(testPath);
        fileHandler.writeTournamentToFile(tournament);

        Tournament sameTournamentFromFile = fileHandler.readTournamentFromFile(tournament.getTournamentID());
        assertEquals(tournament, sameTournamentFromFile);

        //Overwrites the test file with an empty JSONArray after testing is done.
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray jsonArray = new JSONArray();
        objectMapper.writeValue(new File(testPath),jsonArray);

    }

 */

}