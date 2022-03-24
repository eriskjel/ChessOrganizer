package ntnu.idatt2001.k2g9.player;

import java.util.ArrayList;

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


    public boolean addPlayerObject(Player player){
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

    /**
     * Method for adding a player to the PlayerRegistry. Sets playerID from size of registry.
     * @param name
     * @param age
     */
    public boolean addPlayer(String name, int age) {
        try {
            Player player = new Player(name, age);
            player.setPlayerID(players.size());
            players.add(player);
            return true;

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method for adding a user to the PlayerRegistry. Sets playerID from size of registry.
     * @param name
     * @param age
     * @param email
     * @param password
     */
    public void addUser(String name, int age, String email, String password) {
        try {
            User user = new User(name,age,email,password);
            user.setPlayerID(players.size());
            players.add(user);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for adding an admin to the PlayerRegistry. Sets playerID from size of registry.
     * @param name
     * @param age
     * @param email
     * @param password
     */
    public void addAdmin(String name, int age, String email, String password) {
        try {
            Admin admin = new Admin(name,age,email,password);
            admin.setPlayerID(players.size());
            players.add(admin);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
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

    public void removePlayer(Player player){
        try {
            players.remove(player);
        }
        catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

}
