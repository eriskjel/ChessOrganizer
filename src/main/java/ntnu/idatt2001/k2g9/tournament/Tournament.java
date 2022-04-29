package ntnu.idatt2001.k2g9.tournament;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ntnu.idatt2001.k2g9.file.FileHandler;
import ntnu.idatt2001.k2g9.file.LocalDateSerializer;
import ntnu.idatt2001.k2g9.file.TournamentDeserializer;
import ntnu.idatt2001.k2g9.player.Admin;
import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
@JsonDeserialize(using = TournamentDeserializer.class)
/**
 * Class representing a Tournament object. This class contains all information about players, date, name etc. for a tournament.
 * The class has defined which de-serializer to use when creating Tournament objects from ObjectMapper.
 */
public class Tournament {
    private PlayerRegistry players;
    private String name;
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate date;
    private ArrayList<Match[]> tournamentBracket; //
    private String layout;
    private Admin organizer;
    private int tournamentID;


    //Static variable that is used for giving Tournaments their IDs. ID is set in constructor then never reassigned.
    public static int idSetter = new FileHandler().initIDs() + 1;

    /**
     * Constructor that only uses name, date and layout. Used for creating a brand new Tournament.
     * @param name
     * @param date
     * @param layout
     * @throws NullPointerException
     */
    public Tournament(String name, LocalDate date, String layout) throws NullPointerException {
        this.name = name;
        this.date = date;
        this.players = new PlayerRegistry();
        this.layout = layout;
        this.tournamentID = idSetter;
        idSetter++;

    }

    /**
     * Contructor with all data given. Used for reconstructing a tournament that has been saved in the JSON file.
     * @param name
     * @param date
     * @param tournamentBracket
     * @param layout
     * @param organizer
     * @param tournamentID
     * @param players
     */
    public Tournament(String name, LocalDate date, ArrayList<Match[]> tournamentBracket, String layout, Admin organizer, int tournamentID, PlayerRegistry players) {
        this.name = name;
        this.date = date;
        this.tournamentBracket = tournamentBracket;
        this.layout = layout;
        this.organizer = organizer;
        this.tournamentID = tournamentID;
        this.players = players;
    }

    /**
     * Returns the tournament bracket.
     * @return
     */
    public ArrayList<Match[]> getTournamentBracket() {
        return tournamentBracket;
    }

    /**
     * Returns the player register.
     * @return
     */
    public PlayerRegistry getPlayerRegistry(){
        return this.players;
    }

    /**
     * Returns the total rounds in the tournament.
     * @return
     */
    public int getTotalRounds() {
        return tournamentBracket.size();
    }

    /**
     * Returns the id of the tournament.
     * @return
     */
    public int getTournamentID() {
        return tournamentID;
    }

    /**
     * Returns the tournament format.
     * @return
     */
    public String getLayout() {
        return layout;
    }

    /**
     * Returns the date of the tournament.
     * @return
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the tournament name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the tournament format.
     * @param layout
     */
    public void setLayout(String layout) {
        this.layout = layout;
    }

    /**
     * Sets the player Registry.
     * @param players
     */
    public void setPlayers(PlayerRegistry players) {
        this.players = players;
    }

    /**
     * Sets the date of the tournament.
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the name of the tournament.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds all players from parameter ArrayList to the PlayerRegistry.
     * @param playerList
     */
    public void addFromList(ArrayList<Player> playerList){
        for(Player p : playerList){
            p.setPlayerID(players.getSize());
            players.addPlayer(p);
        }
    }
    /**
     * Adds player to participate in the tournament.
     * Checks if the player already is added to the tournament before trying to add.
     *
     * @param player  Player to be added to the tournament.
     * @return  True if player is added, false otherwise.
     */
    public boolean addPlayer(Player player){
        return !players.getPlayers().contains(player) && players.addPlayer(player);
    }

    public boolean removePlayer(Player player){
        return players.removePlayer(player);
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
            tournamentBracket.get(roundNo+1)[(int)(matchNo/2)].setPlayer2(player);
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

