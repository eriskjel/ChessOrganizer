package ntnu.idatt2001;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tournament {
    private PlayerRegistry players;
    private String format;
    private LocalDate date;
    private ArrayList<Match> matches;
    private User organizer;

    public Tournament(String format, LocalDate date, User organizer) {
        this.format = format;
        this.date = date;
        this.organizer = organizer;
    }

    public PlayerRegistry getPlayers() {
        return players;
    }

    public String getFormat() {
        return format;
    }

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }
    public boolean addPlayer(Player player){
        if(players.getPlayers().contains(player)){
            return false;
        }
        else{
            players.addPlayer(player);
            return true;
        }
    }
    public boolean addMatch(Match match){
        matches.add(match);
        return true;
    }
    //TODO: 21.03.2022 ADD TOURNAMENT FUNCTIONALITIES
}
