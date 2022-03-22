package ntnu.idatt2001.k2g9;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class representing a registry of Players.
 */
public class PlayerRegistry {
    private ArrayList<Player> players;

    /**
     * Constructor that creates new ArrayList holding players.
     */
    public PlayerRegistry(){
        players = new ArrayList<>();
    }

    /**
     * Constructor that creates a PlayerRegistry from an ArrayList of players.
     * @param players
     */
    public PlayerRegistry(ArrayList<Player> players){
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Method for adding a new player to the PlayerRegistry. Checks if player already exists in registry, if so returns
     * false. If not, player is added and true is returned.
     * @param player
     * @return
     */
    public boolean addPlayer(Player player){
        try{
            if(players.contains(player)){
                return false;
            }
            else{
                players.add(player);
                return true;
            }
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Finds a player in the registry using email and password to locate user. Returns null if player is not found.
     * @param email
     * @param password
     * @return
     */
    public Player getPlayer(String email, String password){
        ArrayList<Player> foundPlayer = players.stream()
                .filter(Player -> Player.getEmail().equals(email) && Player.getPassword().equals(password))
                .collect(Collectors.toCollection(ArrayList::new));
        if(foundPlayer.size() == 1){
            return foundPlayer.get(0);
        }
        return null;
    }

    @Override
    public String toString() {
        return "PlayerRegistry{" +
                "players=" + players.toString() +
                '}';
    }
}
