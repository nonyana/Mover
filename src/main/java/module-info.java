module com.example.mover {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.mover to javafx.fxml;
    exports com.example.mover;
}