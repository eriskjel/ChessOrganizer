package ntnu.idatt2001.k2g9.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ntnu.idatt2001.k2g9.file.PlayerRegistryDeserializer;

import java.util.ArrayList;

/**
 * Class representing a registry of Players.
 */
@JsonDeserialize(using = PlayerRegistryDeserializer.class)
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
     * Method for adding a player to the PlayerRegistry. Sets playerID from size of registry.
     */
    public boolean addPlayer(Player player){
        try{
            player.setPlayerID(players.size());
            players.add(player);
            return true;
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }

    public void resetPlayerIDs(){
        //resets players with new player IDs
        for (int i = 0; i < this.getSize(); i++) {
            this.getPlayers().get(i).setPlayerID(i);
        }
    }
    @Override
    public String toString() {
        return "PlayerRegistry{" +
                "players=" + players.toString() +
                '}';
    }

    public int getSize(){
        return players.size();
    }

    public boolean removePlayer(Player player){
        try {
            if(players.contains(player)) {
                players.remove(player);
                return true;
            }
            else{
                return false;
            }

        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }

}
