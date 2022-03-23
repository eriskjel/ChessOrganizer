package ntnu.idatt2001.k2g9.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Application class of the project.
 */
    public class Application extends javafx.application.Application {

    /**
     * start method which loads a fxml file, and shows it to the user in an application window
     * @param stage stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * main method which does not not extend any other classes. this method just calls for the application to boot up
     * @param args args
     */
    public static void main(String[] args) {
        launch();
    }
}