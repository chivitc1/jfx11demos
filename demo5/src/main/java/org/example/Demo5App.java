package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Demo5App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/demo5.fxml"));
        Scene scene = new Scene(root, Color.LIGHTYELLOW);
        stage.setScene(scene);
        stage.show();
    }
}
