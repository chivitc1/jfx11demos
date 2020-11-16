package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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
        ellipse.setFill(Color.rgb(173, 216, 230, .5));

        Text text = new Text("Demo Shapes");
        text.setFont(new Font("Arial Bold", 24));

        var stackPane = new StackPane();
        stackPane.getChildren().addAll(ellipse, text);

        Stop[] stops = new Stop[]{
                new Stop(0, Color.DODGERBLUE),
                new Stop(0.5, Color.LIGHTBLUE),
                new Stop(1.0, Color.LIGHTGREEN)
        };
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        ellipse.setFill(gradient);

        ellipse.setEffect(new DropShadow(30, 10, 10, Color.GRAY));

        Reflection reflection = new Reflection();
        reflection.setFraction(.8);
        reflection.setTopOffset(1.0);
        text.setEffect(reflection);

        var scene = new Scene(stackPane, 350, 230, Color.LIGHTYELLOW);

        ellipse.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.getSource().getClass().getSimpleName() + " clicked.");
        });
        text.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.getSource().getClass().getSimpleName() + " clicked.");
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}