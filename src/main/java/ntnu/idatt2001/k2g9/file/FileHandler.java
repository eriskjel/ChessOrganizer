package ntnu.idatt2001.k2g9.file;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.tournament.Tournament;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    ObjectMapper mapper = new ObjectMapper();



    public void readTournamentFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Tournament tournament = mapper.readValue(new File("src/main/resources/ntnu/idatt2001/k2g9/gui/registry/tournamentstest0.json"), Tournament.class);
        System.out.println(tournament);

    }


    public void writeTournamentToFile(Tournament object) throws IOException {

        String path = object.getName() + object.getTournamentID() + ".json";
        mapper.writeValue(new File("src/main/resources/ntnu/idatt2001/k2g9/gui/registry/tournaments" + path.replaceAll(" ", "")), object);
    }

    public Player readCompetitorFromFile(){
        return null;
    }

    public void writeCompetitorToFile(){
        //fd
    }


}

