package ntnu.idatt2001.k2g9.file;

import ntnu.idatt2001.k2g9.tournament.Tournament;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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



    public Tournament readTournamentFromFile(int tournamentID){
        JSONParser jsonParser = new JSONParser();
        //Reads the JSONArray in the file.
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
            //Retrieves jsonObject within the jsonArray, which contains tournaments.
            JSONObject jsonObject = (JSONObject) jsonArray.get(tournamentID);
            String jsonValue = (String)jsonObject.get(String.valueOf(tournamentID));

            ObjectMapper objectMapper = new ObjectMapper();
            Tournament t = objectMapper.readValue(jsonValue, Tournament.class);
            return t;

            //Catches any errors that occur when parsing the JSONArray or mapping the object.
            // eg. if the JSON file was to contain elements other than a JSONArray.
        } catch (IOException | ParseException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }

    }


    public void writeTournamentToFile(Tournament tournament){
        JSONParser jsonParser = new JSONParser();

        //Reads the Array that exists in the tournament file.
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
            //Creates a json string formatted properly using serializer.
            String jsonString = mapper.writeValueAsString(tournament);

            //Create a json object with the formatted string and a key, which is assigned by tournamentID.
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(tournament.getTournamentID(),jsonString);
            jsonArray.add(jsonObject);

            //Overwrites the Json file with updated JSONArray.
            mapper.writeValue(new File(defaultPath),jsonArray);

            //Catches any errors that occur when parsing the JSONArray or mapping the object into a JSON String.
            // eg. if the JSON file was to contain elements other than a JSONArray.
        } catch (IOException | ParseException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }


    }

    public void removeTournament(int tournamentID){
        JSONParser jsonParser = new JSONParser();
        //Reads the JSONArray in the file.
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
            jsonArray.remove(tournamentID);
            mapper.writeValue(new File(defaultPath),jsonArray);
        } catch (IOException | ParseException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Tournament> readAllFromFile(){
        JSONParser jsonParser = new JSONParser();
        ArrayList<Tournament> tournaments = new ArrayList<>();
        JSONObject j = new JSONObject();
        try{
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
            for(int i = 0; i < jsonArray.size();i++){
                try{
                    j = (JSONObject) jsonArray.get(i);
                    String tString = (String) j.get(String.valueOf(i));
                    tournaments.add(mapper.readValue(tString,Tournament.class));
                }catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
            return tournaments;

        }
        catch(IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }
}

