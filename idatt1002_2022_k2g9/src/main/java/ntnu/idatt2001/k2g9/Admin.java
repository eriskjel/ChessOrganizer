package ntnu.idatt2001.k2g9;

import java.time.LocalDate;
import java.util.ArrayList;

public class Admin extends User{
    private ArrayList<Tournament> myTournaments;

    public Admin(String email, String password, String name) {
        super(email, password, name);
        this.myTournaments = new ArrayList<>();
    }
    public void newTournament(Tournament tournament){
        this.myTournaments.add(tournament);
    }

    public ArrayList<Tournament> getMyTournaments() {
        return myTournaments;
    }
}

