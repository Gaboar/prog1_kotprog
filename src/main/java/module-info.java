module com.progegy.kotprog {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.progegy.kotprog to javafx.fxml;
    exports com.progegy.kotprog;
    exports com.progegy.kotprog.egyseg;
    opens com.progegy.kotprog.egyseg to javafx.fxml;
    exports com.progegy.kotprog.varazslat;
    opens com.progegy.kotprog.varazslat to javafx.fxml;
}