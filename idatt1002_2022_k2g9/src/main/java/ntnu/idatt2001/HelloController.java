package ntnu.idatt2001;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class HelloController {
    @FXML
    public TextField inpTournamentName;
    public TextField inpNumCompetitors;
    public DatePicker inpDate;
    public MenuButton inpTournamentFormat;
    public MenuItem formatKnockout;
    private Stage stage;
    private Scene scene;
    private Label welcomeText;

    private String tournamentFormat;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void goToAddTournament(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-add-tournament.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1250, 680);


        stage.setTitle("Add tournament!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToAdminTournamentHub(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-hub.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1250, 680);


        stage.setTitle("Tournament hub");
        stage.setScene(scene);
        stage.show();
    }



    public void SwitchToScene1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addTournament(ActionEvent actionEvent) {
        String tournamentName = inpTournamentName.getText();
        int numCompetitors = Integer.parseInt(inpNumCompetitors.getText());
        //will be null if a format is not selected in the application
        String tournamentFormat = this.tournamentFormat;
        LocalDate date = inpDate.getValue();

        System.out.println(tournamentName + ", " + tournamentFormat + ", " + numCompetitors + ", " + date);
    }

    @FXML
    public void setFormatKnockout(ActionEvent actionEvent) {
        this.tournamentFormat = "Knockout";
    }

    @FXML
    public void formatSwiss(ActionEvent actionEvent) {
        this.tournamentFormat = "Swiss";
    }

        /*
    public void SwitchToScene2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
    }
     */
}