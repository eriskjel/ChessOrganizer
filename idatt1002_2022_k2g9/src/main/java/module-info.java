module ChessTournament {
    requires javafx.controls;
    requires javafx.fxml;
    //requires charm.glisten;


    opens ntnu.idatt2001 to javafx.fxml;
    exports ntnu.idatt2001;
}