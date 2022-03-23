package ntnu.idatt2001.k2g9;

/**
 * Subclass representing a Player user. This player has stored ranking, age, fideRating and win/loss statistics.
 *
 */
public class Player{
    protected String name;
    protected int age;


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


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}
