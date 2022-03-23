package ntnu.idatt2001.k2g9.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CompetitorModel {

    private SimpleStringProperty name;
    private SimpleIntegerProperty age;

    public CompetitorModel(String name, int age) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);

    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age = new SimpleIntegerProperty(age);
    }
}