package ntnu.idatt2001.k2g9;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Admin is a subclass of User that has extra permission within the Application and a ArrayList of tournaments the
 * Admin organizes.
 */
public class Admin extends User{
    private ArrayList<Tournament> myTournaments;

    public Admin(String name, int age, String email, String password) {
        super(name, age, email, password);
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

