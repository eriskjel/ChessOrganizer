package ntnu.idatt2001;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tournament {
    private PlayerRegistry players;
    private PlayerRegistry knockedOutPlayers;
    private LocalDate date;
    private ArrayList<Match[]> tournamentRounds;
    private User organizer;
    private int totalRounds;
    private int currentRound;

    public Tournament(LocalDate date, User organizer, PlayerRegistry players) {
        this.date = date;
        this.organizer = organizer;
        this.players = players;
        this.tournamentRounds = new ArrayList<>();
        this.totalRounds = (int)Math.ceil(players.getSize()/2);
        this.knockedOutPlayers = new PlayerRegistry();
        this.currentRound = 0;

    }

    public PlayerRegistry getPlayers() {
        return players;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setTotalRounds() {
        this.totalRounds = (int)Math.ceil(players.getSize()/2);
    }

    public ArrayList<Match[]> getTournamentRounds() {
        return tournamentRounds;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public User getOrganizer() {
        return organizer;
    }

    public PlayerRegistry getKnockedOutPlayers() {
        return knockedOutPlayers;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
            this.setTotalRounds();
            return true;
        }
    }
    public void playerLost(Player player){
        players.removePlayer(player);
        knockedOutPlayers.addPlayer(player);
    }

    public void updateTournamentRounds(Match[] round){
        this.tournamentRounds.add(round);
        currentRound++;
    }
}
