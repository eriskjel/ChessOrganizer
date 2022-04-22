package ntnu.idatt2001.k2g9.tournament;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class representing a registry of Tournaments. This is the object that will track all tournaments in the app.
 *
 */
public class TournamentRegistry {
    private ArrayList<Tournament> tournaments;

    /**
     * Constructor with no parameters that creates a completely new ArrayList.
     */
     public TournamentRegistry(){
         this.tournaments = new ArrayList<>();
     }

    /**
     * Second constructor that creates a Registry object from an already existing ArrayList.
     * @param tournaments
     */
     public TournamentRegistry(ArrayList<Tournament> tournaments){
         this.tournaments = tournaments;
     }

    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }

    public int getSize(){
         return tournaments.size();
    }

    /**
     * Adds a tournament to the TournamentRegistry.
     * @param tournament
     */
    public void addTournaments(Tournament tournament){
        tournament.setTournamentID(tournaments.size());
         this.tournaments.add(tournament);
    }


    /**
     * Finds a tournament in the Registry using the tournamentID.
     * @param tournamentID
     * @return
     */
    public Tournament getTournament(int tournamentID){
         ArrayList<Tournament> foundT = tournaments.stream()
                 .filter(Tournament -> Tournament.getTournamentID() == tournamentID)
                 .collect(Collectors.toCollection(ArrayList::new));
         if(foundT.size()==1){
             return foundT.get(0);
         }
         else{
             return null;
         }
    }

    /**
     * to string method
     * @return string format of tournaments
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(Tournament tournament : tournaments){
            string.append(tournament.toString() + "\n");
        }
        return "TournamentRegistry{" +
                "tournaments=" + string.toString() +
                '}';
    }
}

