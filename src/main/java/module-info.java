module ChessTournament {
    requires javafx.controls;
    requires javafx.fxml;
    //requires charm.glisten;



    //exports ntnu.idatt2001.k2g9;
    //opens ntnu.idatt2001.k2g9 to javafx.fxml;
    exports ntnu.idatt2001.k2g9.player;
    opens ntnu.idatt2001.k2g9.player to javafx.fxml;
    exports ntnu.idatt2001.k2g9.tournament;
    opens ntnu.idatt2001.k2g9.tournament to javafx.fxml;
    //exports ntnu.idatt2001.k2g9.gui;
    //opens ntnu.idatt2001.k2g9.gui to javafx.fxml;
    exports ntnu.idatt2001.k2g9.gui.models;
    opens ntnu.idatt2001.k2g9.gui.models to javafx.fxml;
    exports ntnu.idatt2001.k2g9.gui.controllers;
    opens ntnu.idatt2001.k2g9.gui.controllers to javafx.fxml;
    exports ntnu.idatt2001.k2g9.gui.application;
    opens ntnu.idatt2001.k2g9.gui.application to javafx.fxml;
}