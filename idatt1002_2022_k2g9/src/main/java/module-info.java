module ChessTournament {
    requires javafx.controls;
    requires javafx.fxml;
    //requires charm.glisten;




    exports ntnu.idatt2001.k2g9.gui;
    opens ntnu.idatt2001.k2g9.gui to javafx.fxml;
    exports ntnu.idatt2001.k2g9.tournament;
    opens ntnu.idatt2001.k2g9.tournament to javafx.fxml;
    exports ntnu.idatt2001.k2g9.player;
    opens ntnu.idatt2001.k2g9.player to javafx.fxml;
}