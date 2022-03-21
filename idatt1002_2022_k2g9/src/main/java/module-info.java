module com.example.chesstournament {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chesstournament to javafx.fxml;
    exports com.example.chesstournament;
}