package ntnu.idatt2001.k2g9.file;

import ntnu.idatt2001.k2g9.tournament.Tournament;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Class used for handling JSON file Tournament registry. Class has a default path, which refers to the Tournaments.json file.
 * This file is the main file in the Project used for storing information.
 */
public class FileHandler {

    ObjectMapper mapper;
    private String defaultPath;

    public FileHandler(){
        this.defaultPath = "src/main/resources/ntnu/idatt2001/k2g9/gui/registry/tournaments/Tournaments.json";
        this.mapper = new ObjectMapper();
    }

    /**
     * Constructor that allows you to use a custom path.
     * @param path
     */
    public FileHandler(String path){
        this.defaultPath = path;
        this.mapper = new ObjectMapper();
    }


    /**
     * Method for reading a Tournament from the file. Takes id in as parameter and locates and parses json value into
     * Tournament object.
     * @param tournamentID
     * @return
     */
    public Tournament readTournamentFromFile(int tournamentID){
        JSONParser jsonParser = new JSONParser();
        //Reads the JSONArray in the file.
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
            //Retrieves jsonObject within the jsonArray, which contains tournaments.

            JSONObject jsonObject = null;
            String jsonValue = null;
            //For statement that enters every object in array.
            for(Object object : jsonArray){
                //Parses the object into a JSONObject, and if said object has a key equal to tournamentID, jsonValue
                // is defined as the String within the object.
                jsonObject = (JSONObject) object;
                    if(jsonObject.get(String.valueOf(tournamentID)) != null){
                        jsonValue = (String)jsonObject.get(String.valueOf(tournamentID));
                    }
            }
            //Mapper is created and String is parsed into Tournament object.
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

            /*For loop that goes through all objects in the jsonArray. Loop tries to retrieve internal object with key
            and if its not found it will only return null. Therefore running through the whole array and not finding
             any values assigned to the tournamentID key will prove that the ArrayList does not already contain the tournament.

             */
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

    /**
     * Method used for updating Tournament that already exists in Register. Used in Edit Tournament (GUI).
     * @param tournament
     */
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

            // For loop that traverses Array and breaks if content with key is found, and therefore locating correct object.

            for(Object object : jsonArray){
                jsonObject = (JSONObject) object;
                    if(jsonObject.get(String.valueOf(id)) != null){
                        break;
                    }
            }
            if(jsonObject == null){
                throw new IllegalArgumentException("Could not find the tournament");
            }
                //Object found earlier is removed from Array.
                jsonArray.remove(jsonObject);

                //New JSON Object is created and given the correct value and key.
                JSONObject newObject = new JSONObject();
                newObject.put(String.valueOf(id),jsonString);

                jsonArray.add(newObject);
                //Overwrites the Json file with updated JSONArray.
                mapper.writeValue(new File(defaultPath),jsonArray);


            //Catches any errors that occur when parsing the JSONArray or mapping the object into a JSON String.
            // eg. if the JSON file was to contain elements other than a JSONArray.
        } catch (IOException | ParseException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for removing a tournament from the JSON tournament register. Takes id as parameter and removes file
     * if found.
     * @param tournamentID
     */
    public void removeTournament(int tournamentID){
        JSONParser jsonParser = new JSONParser();
        //Reads the JSONArray in the file.
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
            JSONObject jsonObject = null;
            String correctObject = null;
            //Locates the object with the proper key and value.
            for(Object object : jsonArray){
                jsonObject = (JSONObject) object;
                if(jsonObject.get(String.valueOf(tournamentID))!=null){
                    break;
                }
            }
            if(jsonObject == null){
                throw new IllegalArgumentException("Tournament was not found");
            }

            //Removes said value from Array and writes over Array in file.
            jsonArray.remove(jsonObject);
            mapper.writeValue(new File(defaultPath),jsonArray);
        } catch (IOException | ParseException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that reads all tournaments stored in the JSON file.
     * @return
     */
    public ArrayList<Tournament> readAllFromFile(){
        // Initalize objects needed in method.
        JSONParser jsonParser = new JSONParser();
        ArrayList<Tournament> tournaments = new ArrayList<>();
        JSONObject j = null;
        Collection<String> item;
        try{
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(defaultPath));
            // Controls if the array is empty.
            if(jsonArray.isEmpty()){
                return null;
            }
            //Traverses through array and maps object and adds them to ArrayList.
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
            // Return
            return tournaments;

        }
        catch(IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }
    /*
       Method that is used for initializing the static idSetter in Tournament class at the start of the run. This is to
       make sure the static variable knows how many preexisting tournaments there are.
     */
    public int initIDs(){
        ArrayList<Tournament> tournaments = this.readAllFromFile();
        if(tournaments == null){
            return 0;
        }
            return tournaments.stream()
                    .mapToInt(Tournament -> Tournament.getTournamentID())
                    .max().orElse(0);
    }

    /**
     * Method that is used once in the start of the application. Method creates the json file if it does not exist.
     * @throws IOException
     */
    public void initFile() throws IOException {
        File f = new File(defaultPath);
        if(!f.exists()){
            f.createNewFile();
            FileWriter writer = new FileWriter(f);
            writer.write("[]");
            writer.close();
        }

    }
}

