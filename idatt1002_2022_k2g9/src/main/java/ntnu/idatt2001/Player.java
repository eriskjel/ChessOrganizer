package ntnu.idatt2001;

public class Player extends User {
    private int fideRating;
    private int age;
    private String rank;
    private int matchesWon;
    private int matchesLost;

    public Player(String email, String password, String name, int fideRating, int age, String rank, int matchesWon, int matchesLost) {
        super(email, password, name);
        this.fideRating = fideRating;
        this.age = age;
        this.rank = rank;
        this.matchesWon = matchesWon;
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
