package ntnu.idatt2001.k2g9.file;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.tournament.Tournament;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    ObjectMapper mapper;
    private String defaultPath;

    public FileHandler(){
        this.defaultPath = "src/main/resources/ntnu/idatt2001/k2g9/gui/registry/tournaments/Tournaments.json";
        this.mapper = new ObjectMapper();
    }

    public FileHandler(String path){
        this.defaultPath = path;
        this.mapper = new ObjectMapper();
    }



    public Tournament readTournamentFromFile(int tournamentID) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        //Reads the JSONArray in the file.
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
        //Retrieves jsonObject within the jsonArray, which contains tournaments.
        JSONObject jsonObject = (JSONObject) jsonArray.get(tournamentID);
        String jsonValue = (String)jsonObject.get(String.valueOf(tournamentID));

        ObjectMapper objectMapper = new ObjectMapper();
        Tournament t = objectMapper.readValue(jsonValue,Tournament.class);
        return t;

    }


    public void writeTournamentToFile(Tournament tournament) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        //Reads the Array that exists in the tournament file.
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));

        //Creates a json string formatted properly using serializer.
        String jsonString = mapper.writeValueAsString(tournament);

        //Create a json object with the formatted string and a key, which is assigned by tournamentID.
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(tournament.getTournamentID(),jsonString);
        jsonArray.add(jsonObject);

        //Overwrites the Json file with updated JSONArray.
        mapper.writeValue(new File(defaultPath),jsonArray);
    }

    public void removeTournament(int tournamentID) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        //Reads the JSONArray in the file.
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
        jsonArray.remove(tournamentID);
        mapper.writeValue(new File(defaultPath),jsonArray);
    }
}

