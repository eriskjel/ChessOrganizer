package ntnu.idatt2001.k2g9;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * Superclass representing a user. Contains the protected variables: email, password and name.
 */
public class User {
    protected String email;
    protected String password;
    protected String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return email.equals(user.email) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    /**
     * Generates a salt to use for encryption.
     * @return
     */
    public byte[] generateSalt() {
        byte[] byteTable = SecureRandom.getSeed(16);
        return byteTable;
    }

    /**
     * Method takes in a salt as a parameter and returns a String with a hashed password using the SHA-256 algorithm.
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
