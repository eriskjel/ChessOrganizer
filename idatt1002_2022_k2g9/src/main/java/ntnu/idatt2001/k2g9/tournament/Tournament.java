package ntnu.idatt2001.k2g9.tournament;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import ntnu.idatt2001.k2g9.player.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tournament {
    private PlayerRegistry players;
    private String name;
    private LocalDate date;
    private ArrayList<Match[]> tournamentBracket; //
    private String layout;
    private User organizer;

    public Tournament(String name, LocalDate date, User organizer, PlayerRegistry players, String layout) {
        this.name = name;
        this.date = date;
        this.organizer = organizer;
        this.players = players;
        this.layout = layout;
    }

    public ArrayList<Match[]> getTournamentBracket() {
        return tournamentBracket;
    }

    public PlayerRegistry getPlayers() {
        return players;
    }

    public int getTotalRounds() {
        return tournamentBracket.size();
    }

    public User getOrganizer() {
        return organizer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void getPlayers(PlayerRegistry players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }


    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Adds player to participate in the tournament.
     * Checks if the player already is added to the tournament before trying to add.
     *
     * @param player  Player to be added to the tournament.
     * @return  True if player is added, false otherwise.
     */
    public boolean addPlayer(Player player){
        return !players.getPlayers().contains(player) && players.addPlayerObject(player);
    }

    /**
     * Sets the winner of a specific match in the tournament.
     * Once winner is set, the winner is then moved up the bracket.
     * If the winner of a match is changed, the current winner will be moved back and the new winner will take his place.
     *
     * @param roundNo   The round in the tournament, Preliminary is round 0, first round is 1, second round is 2, etc.
     * @param matchNo   The match number in the round starting from top to bottom. Uppermost match of a round is index 0.
     * @param player  The winner of the match.
     */
    public void setMatchWinnerKnockout(int roundNo, int matchNo, Player player){
        if (layout != "Knock-Out")
            throw new IllegalArgumentException("Incorrect layout, this method is for handling knockout tournaments");
        tournamentBracket.get(roundNo)[matchNo].setWinner(player);
        if (matchNo%2 == 0){
            tournamentBracket.get(roundNo+1)[(int)(matchNo/2)].setPlayer1(player);
        } else if (matchNo%2 == 1){
            tournamentBracket.get(roundNo+1)[(int)(matchNo/2)].setPlayer1(player);
        }
    }

    public Player getMatchWinner(int roundNo, int matchNo){
        return tournamentBracket.get(roundNo)[matchNo].getWinner();
    }

    /**
     * Creates bracket once the tournament is ready to be created.
     */
    public void createTournamentBracket(){
        tournamentBracket = TournamentFormat.createBracket(layout, players);
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", organizer=" + organizer +
                '}';
    }
}
