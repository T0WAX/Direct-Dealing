module eu.telecomnancy.DirectDealing {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens eu.telecomnancy to javafx.fxml;
    exports eu.telecomnancy;
}