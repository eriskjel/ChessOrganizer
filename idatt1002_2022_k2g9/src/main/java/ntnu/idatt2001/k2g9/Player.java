package ntnu.idatt2001.k2g9;

/**
 * Subclass representing a Player user. This player has stored ranking, age, fideRating and win/loss statistics.
 *
 */
public class Player extends User {
    private int fideRating;
    private int age;
    private String rank;
    private int matchesWon;
    private int matchesLost;

    /**
     * Constructor for Player class. Checks parameters and throws exception if illegal values found.
     * @param email
     * @param password
     * @param name
     * @param fideRating
     * @param age
     * @param rank
     * @param matchesWon
     * @param matchesLost
     */
    public Player(String email, String password, String name, int fideRating, int age, String rank, int matchesWon, int matchesLost) {
        super(email, password, name);
        if(fideRating < 0){
            throw new IllegalArgumentException("fideRating can not be below zero.");
        }
        this.fideRating = fideRating;
        if(age < 0){
            throw new IllegalArgumentException("Age can not be below zero.");
        }
        this.age = age;
        this.rank = rank;
        if(matchesWon < 0){
            throw new IllegalArgumentException("matchesWon can not be below zero.");
        }
        this.matchesWon = matchesWon;
        if(matchesLost < 0){
            throw new IllegalArgumentException("matchesLost can not be below zero.");
        }
        this.matchesLost = matchesLost;
    }

    public int getFideRating() {
        return fideRating;
    }

    public void setFideRating(int fideRating) {
        this.fideRating = fideRating;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    @Override
    public String toString() {
        return "Player{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                "fideRating=" + fideRating +
                ", age=" + age +
                ", rank='" + rank + '\'' +
                ", matchesWon=" + matchesWon +
                ", matchesLost=" + matchesLost +
                '}';
    }
}
