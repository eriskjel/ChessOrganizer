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
    private String defaultPath = "src/main/resources/ntnu/idatt2001/k2g9/gui/registry/tournaments/";



    public Tournament readTournamentFromFile(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(defaultPath + fileName), Tournament.class);

    }


    public void writeTournamentToFile(Tournament tournament) throws IOException {
        mapper.writeValue(new File(defaultPath + this.getTournamentFilePath(tournament).replaceAll(" ", "")), tournament);
    }

    public Player readCompetitorFromFile(){
        return null;
    }

    public void writeCompetitorToFile(){
        //fd
    }

    public String getTournamentFilePath(Tournament tournament){
        return tournament.getName() + tournament.getTournamentID() + ".json";
    }


}

