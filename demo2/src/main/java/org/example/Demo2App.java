package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class Demo2App extends Application {

    @Override
    public void start(Stage stage) {
        var ellipse = new Ellipse(110, 70);
        ellipse.setFill(Color.LIGHTBLUE);

        Text text = new Text("Demo Shapes");
        text.setFont(new Font("Arial Bold", 24));

        var stackPane = new StackPane();
        stackPane.getChildren().addAll(ellipse, text);

        var scene = new Scene(stackPane, 640, 480, Color.LIGHTYELLOW);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}