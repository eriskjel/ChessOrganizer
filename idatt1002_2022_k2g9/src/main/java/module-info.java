module ChessTournament {
    requires javafx.controls;
    requires javafx.fxml;
    //requires de.jensd.fx.fontawesomefx.fontawesome;


    opens ntnu.idatt2001 to javafx.fxml;
    exports ntnu.idatt2001;
}