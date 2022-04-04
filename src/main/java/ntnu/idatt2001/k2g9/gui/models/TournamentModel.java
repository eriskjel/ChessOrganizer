package ntnu.idatt2001.k2g9.gui.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import ntnu.idatt2001.k2g9.tournament.Tournament;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Class that represents a Tournament. This is solely used to add data to the table, used in "AdminManageTournaments".
 * This should not be confused with the actual Competitor object, which represent an actual competitor in the chess tournament.
 *
 */
public class TournamentModel {

    /**
     * uses SimpleString and SimpleInt properties. this is to use the correct format in order to add data to the javafx tableview
     */
    private SimpleStringProperty date;
    private SimpleStringProperty name;
    private SimpleIntegerProperty numCompetitors;
    private SimpleStringProperty format;
    private int tournamentID;

    /**
     * constructor for tournament model
     * @param date
     * @param name
     * @param numCompetitors
     * @param format
     */
    public TournamentModel(LocalDate date, String name, Integer numCompetitors, String format) {
        this.date = new SimpleStringProperty(date.toString());
        this.name = new SimpleStringProperty(name);
        this.numCompetitors = new SimpleIntegerProperty(numCompetitors);
        this.format = new SimpleStringProperty(format);

    }

    public TournamentModel(Tournament tournament){
        this.date = new SimpleStringProperty(tournament.getDate().toString());
        this.name = new SimpleStringProperty(tournament.getName());
        this.numCompetitors = new SimpleIntegerProperty(tournament.getPlayers().size());
        this.format = new SimpleStringProperty(tournament.getLayout());
        this.tournamentID = tournament.getTournamentID();
    }

    public int getTournamentID(){
        return this.tournamentID;
    }

    public String getDate(){
        return this.date.get();
    }

    public void setDate(SimpleStringProperty date){
        this.date = date;
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
     * @param name new name of tournament
     */
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    /**
     * getter for num competitors
     * @return num of competitor
     */
    public int getNumCompetitors() {
        return numCompetitors.get();
    }

    /**
     * setter for age
     * @param numCompetitors new age
     */
    public void setNumCompetitors(int numCompetitors) {
        this.numCompetitors = new SimpleIntegerProperty(numCompetitors);
    }

    /**
     * getter for format
     * @return format
     */
    public String getFormat(){
        return this.format.get();
    }

    /**
     * setter for format
     * @param format new format
     */
    public void setFormat(String format){
        this.format = new SimpleStringProperty(format);
    }
}