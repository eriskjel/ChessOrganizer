package ntnu.idatt2001;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PlayerRegistry {
    private ArrayList<Player> players;

    public PlayerRegistry(){
        players = new ArrayList<>();
    }
    public PlayerRegistry(ArrayList<Player> players){
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
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
