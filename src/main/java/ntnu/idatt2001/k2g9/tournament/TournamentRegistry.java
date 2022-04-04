package ntnu.idatt2001.k2g9.tournament;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.player.User;

import java.time.LocalDate;
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
     * Constructor that creates tournament object in this tournamentRegistry instead of outside of it. Checks if
     * the tournament already exists.
     * @param name
     * @param date
     * @param layout
     * @return
     */
    public void addTournament(String name, LocalDate date, String layout){
        Tournament newT = new Tournament(name,date,layout);
            newT.setTournamentID(tournaments.size());
            tournaments.add(newT);
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

