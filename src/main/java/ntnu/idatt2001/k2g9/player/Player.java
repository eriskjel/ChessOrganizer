package ntnu.idatt2001.k2g9.player;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ntnu.idatt2001.k2g9.file.PlayerDeserializer;

import java.util.Objects;

/**
 * Subclass representing a Player user. This player has stored ranking, age, fideRating and win/loss statistics.
 *
 */
@JsonDeserialize(using = PlayerDeserializer.class)
public class Player{
    protected String name;
    protected int age;
    protected int playerID;
    /**
     * Constructor for Player class. Checks parameters and throws exception if illegal values found.
     * @param name
     * @param age
     */
    public Player(String name, int age) {
        this.name = name;
        if(age < 0){
            throw new IllegalArgumentException("Age can not be below zero.");
        }
        this.age = age;
    }

    public Player(String name, int age, int playerID) {
        this.name = name;
        this.age = age;
        this.playerID = playerID;
    }

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getPlayerID() {
        return playerID;
    }
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", playerID=" + playerID +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return age == player.age && playerID == player.playerID && Objects.equals(name, player.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, age, playerID);
    }
}
