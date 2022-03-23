package ntnu.idatt2001.k2g9.player;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Superclass representing a user. Contains the protected variables: email, password and name.
 */
public class User extends Player{
    protected String email;
    protected String password;
    protected String rank;
    protected int matchesWon;
    protected int matchesLost;
    protected int fideRating;

    public User(String name, int age, String email, String password) {
        super(name, age);
        this.email = email;
        this.password = password;
        this.rank = "Noob";
        this.matchesWon = 0;
        this.matchesLost = 0;
        this.fideRating = 300;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    public int getFideRating() {
        return fideRating;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public void setFideRating(int fideRating) {
        this.fideRating = fideRating;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", playerID=" + playerID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rank='" + rank + '\'' +
                ", matchesWon=" + matchesWon +
                ", matchesLost=" + matchesLost +
                ", fideRating=" + fideRating +
                '}';
    }

    /**
     * Generates a salt to use for encryption. NOT IN USE YET.
     * @return
     */
    public byte[] generateSalt() {
        byte[] byteTable = SecureRandom.getSeed(16);
        return byteTable;
    }

    /**
     * Method takes in a salt as a parameter and returns a String with a hashed password using the SHA-256 algorithm.
     * NOT IN USE YET
     * @param password
     * @param salt
     * @return
     */
    public String hashPassword(String password, byte[] salt){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            byte[] bytes = digest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            String hashedPassword = stringBuilder.toString();
            return hashedPassword;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
