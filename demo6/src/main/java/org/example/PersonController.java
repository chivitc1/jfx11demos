package org.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class PersonController {

    @FXML
    private void switchToSecondary() throws IOException {
        PersonApp.setRoot("secondary");
    }
}
