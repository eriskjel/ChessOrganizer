package ntnu.idatt2001.k2g9.player;

import ntnu.idatt2001.k2g9.tournament.Tournament;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that represents an Admin in the application. Contains an ArrayList of all the tournaments the admin organises.
 * Also contains login in information.
 */
public class Admin{
    private ArrayList<Tournament> myTournaments;
    protected String email;
    protected String password;


    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
        this.myTournaments =  new ArrayList<Tournament>();
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
    public ArrayList<Tournament> getMyTournaments() {
        return myTournaments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(email, admin.email) && Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    /**
     * Method for adding a tournament to the myTournaments ArrayList.
     * @param tournament
     */
    public void addTournament(Tournament tournament){
        this.myTournaments.add(tournament);
    }


    @Override
    public String toString() {
        return "Admin{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
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

