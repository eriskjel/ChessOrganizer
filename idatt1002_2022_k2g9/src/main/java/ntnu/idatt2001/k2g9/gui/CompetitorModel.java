package ntnu.idatt2001.k2g9.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import ntnu.idatt2001.k2g9.player.Player;

/**
 * Class that represents a Competitor. This is solely used to add data to the table, used in "AdminAddTournamentController".
 * This should not be confused with the actual Competitor object, which represent an actual competitor in the chess tournament.
 *
 */
public class CompetitorModel {

    /**
     * uses SimpleString and SimpleInt properties. this is to use the correct format in order to add data to the javafx tableview
     */
    private SimpleStringProperty name;
    private SimpleIntegerProperty age;
    private int competitorID;

    /**
     * constructor for competitormodel
     * @param name
     * @param age
     */
    public CompetitorModel(String name, int age, int competitorID) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.competitorID = competitorID;
    }

    public CompetitorModel(Player player) {
        this.name = new SimpleStringProperty(player.getName());
        this.age = new SimpleIntegerProperty((player.getAge()));
        this.competitorID = player.getPlayerID();
    }

    public int getCompetitorID(){
        return this.competitorID;
    }

    /**
     * getter for name
     * @return name of registered competitor
     */
    public String getName() {
        return name.get();
    }

    /**
     * setter for name
     * @param name new name of competitor
     */
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    /**
     * getter for age
     * @return age of competitor
     */
    public int getAge() {
        return age.get();
    }

    /**
     * setter for age
     * @param age new age
     */
    public void setAge(int age) {
        this.age = new SimpleIntegerProperty(age);
    }
}