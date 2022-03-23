package ntnu.idatt2001.k2g9;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Subclass representing an Admin user. Simple class that contains an ArrayList of tournaments that the Admin
 * is in charge of organizing.
 */
public class Admin extends User{
    private ArrayList<Tournament> myTournaments;

    public Admin(String email, String password, String name) {
        super(email, password, name);
        this.myTournaments = new ArrayList<>();
    }

    /**
     * Method for adding a tournament to the myTournaments ArrayList.
     * @param tournament
     */
    public void newTournament(Tournament tournament){
        this.myTournaments.add(tournament);
    }

    public ArrayList<Tournament> getMyTournaments() {
        return myTournaments;
    }
}

