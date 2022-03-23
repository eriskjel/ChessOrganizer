module ChessTournament {
    requires javafx.controls;
    requires javafx.fxml;
    //requires charm.glisten;



    //exports ntnu.idatt2001.k2g9;
    //opens ntnu.idatt2001.k2g9 to javafx.fxml;
    exports ntnu.idatt2001.k2g9.gui;
    opens ntnu.idatt2001.k2g9.gui to javafx.fxml;
}