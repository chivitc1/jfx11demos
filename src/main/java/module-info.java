module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.hello to javafx.fxml;
    exports org.example.hello;
}