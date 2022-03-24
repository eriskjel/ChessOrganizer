package ntnu.idatt2001.k2g9.tournament;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Tournament {
    private PlayerRegistry players;
    private String name;
    private PlayerRegistry knockedOutPlayers;
    private LocalDate date;
    private ArrayList<Match[]> tournamentRounds;
    //private User organizer;
    private int totalRounds;
    private int currentRound;
    private String layout;
    private int tournamentID;


    public Tournament(String name, LocalDate date, String layout) {
        this.name = name;
        this.date = date;
        //this.organizer = organizer;
        this.players = new PlayerRegistry();
        this.tournamentRounds = new ArrayList<>();
        this.totalRounds = (int)Math.ceil(players.getSize()/2);
        this.knockedOutPlayers = new PlayerRegistry();
        this.currentRound = 0;
        this.layout = layout;

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

    /*public User getOrganizer() {
        return organizer;
    }

     */

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getLayout() {
        return layout;
    }

    public PlayerRegistry getKnockedOutPlayers() {
        return knockedOutPlayers;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    /*
    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

     */

    public boolean addPlayer(Player player){
        if(players.getPlayers().contains(player)){
            return false;
        }
        else{
            players.addPlayerObject(player);
            this.setTotalRounds();
            return true;
        }
    }
    public void playerLost(Player player){
        players.removePlayer(player);
        knockedOutPlayers.addPlayerObject(player);
    }

    public void updateTournamentRounds(Match[] round){
        this.tournamentRounds.add(round);
        currentRound++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                ", date=" + date.toString() +
                ", layout='" + layout + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;
        Tournament that = (Tournament) o;
        return tournamentID == that.tournamentID && Objects.equals(name, that.name) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, tournamentID);
    }
}
