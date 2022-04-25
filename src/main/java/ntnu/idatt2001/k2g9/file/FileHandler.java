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
import java.util.Collection;
import java.util.NoSuchElementException;

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
            JSONObject jsonObject = null;
            String jsonValue = null;
            for(Object object : jsonArray){
                jsonObject = (JSONObject) object;
                    if(jsonObject.get(String.valueOf(tournamentID)) != null){
                        jsonValue = (String)jsonObject.get(String.valueOf(tournamentID));
                    }
            }
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
            int id = tournament.getTournamentID();
            JSONObject jsonObject = null;
            String jsonValue = null;
            for(Object object : jsonArray){
                jsonObject = (JSONObject) object;
                jsonValue = (String) jsonObject.get(String.valueOf(id));
                }
            if(jsonValue != null){
                throw new IllegalArgumentException("Tournament already exists");
            }

            //Create a json object with the formatted string and a key, which is assigned by tournamentID.
            jsonObject = new JSONObject();
            jsonObject.put(id,jsonString);
            jsonArray.add(jsonObject);


            //Overwrites the Json file with updated JSONArray.
            mapper.writeValue(new File(defaultPath),jsonArray);

            //Catches any errors that occur when parsing the JSONArray or mapping the object into a JSON String.
            // eg. if the JSON file was to contain elements other than a JSONArray.
        } catch (IOException | ParseException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void updateTournament(Tournament  tournament){
        JSONParser jsonParser = new JSONParser();

        //Reads the Array that exists in the tournament file.
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
            //Creates a json string formatted properly using serializer.
            String jsonString = mapper.writeValueAsString(tournament);
            int id = tournament.getTournamentID();
            JSONObject jsonObject = null;
            String correctObject = null;
            int counter = 0;
            for(Object object : jsonArray){
                jsonObject = (JSONObject) object;
                    if(jsonObject.get(String.valueOf(id)) != null){
                        correctObject = (String) jsonObject.get(String.valueOf(id));
                        break;
                    }

                    counter++;
            }
            if(jsonObject == null){
                throw new IllegalArgumentException("Could not find the tournament");
            }

                jsonArray.remove(jsonObject);
                JSONObject newObject = new JSONObject();
                newObject.put(String.valueOf(id),jsonString);
                /*
                jsonObject.remove(String.valueOf(id),correctObject);
                jsonObject.put(id,jsonString);

                 */
                jsonArray.add(newObject);
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
            JSONObject jsonObject = null;
            String correctObject = null;
            for(Object object : jsonArray){
                jsonObject = (JSONObject) object;
                if(jsonObject.get(String.valueOf(tournamentID))!=null){
                    break;
                }
            }
            if(jsonObject == null){
                throw new IllegalArgumentException("Tournament was not found");
            }

            jsonArray.remove(jsonObject);
            mapper.writeValue(new File(defaultPath),jsonArray);
        } catch (IOException | ParseException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Tournament> readAllFromFile(){
        JSONParser jsonParser = new JSONParser();
        ArrayList<Tournament> tournaments = new ArrayList<>();
        JSONObject j = null;
        Collection<String> item;
        try{
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
            if(jsonArray.isEmpty()){
                return null;
            }
            for(Object object : jsonArray){
                j = (JSONObject) object;
                item = j.values();
                item.stream()
                        .forEach(String -> {
                            try {
                                tournaments.add(mapper.readValue(String, Tournament.class));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

            }
            return tournaments;

        }
        catch(IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }

    public int initIDs(){
        ArrayList<Tournament> tournaments = this.readAllFromFile();
        if(tournaments == null){
            return 0;
        }
            return tournaments.stream()
                    .mapToInt(Tournament -> Tournament.getTournamentID())
                    .max().orElse(0);
    }
}

