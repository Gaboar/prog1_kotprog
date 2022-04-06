module com.progegy.kotprog {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.progegy.kotprog to javafx.fxml;
    exports com.progegy.kotprog;
}