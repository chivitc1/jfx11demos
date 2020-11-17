package org.example;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.binding.When;
import javafx.beans.value.ObservableObjectValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class Demo3App extends Application {

    @Override
    public void start(Stage stage) {
        var ellipse = new Ellipse(stage.getWidth()/2, stage.getMaxHeight()/2);
        ellipse.setRadiusX(150);
        ellipse.setRadiusY(60);
        ellipse.setFill(Color.rgb(200, 100, 115, .5));

        Text text = new Text("Hello world");
        text.setFill(Color.GREEN);
        var pane = new StackPane();
        pane.getChildren().addAll(ellipse, text);

        RotateTransition transition = new RotateTransition();
        transition.setDuration(Duration.millis(2500));
        transition.setNode(pane);
        transition.setFromAngle(0);
        transition.setToAngle(360);
        transition.setInterpolator(Interpolator.LINEAR);

        pane.setOnMouseClicked(mouseEvent -> {
            if (transition.getStatus().equals(Animation.Status.RUNNING)) {
                transition.pause();
            } else {
                transition.play();
            }
        });

        var box = new VBox();
        var text2 = new Text();
        text2.setFill(Color.BLUE);
        box.getChildren().addAll(pane, text2);
        box.setAlignment(Pos.TOP_CENTER);
        transition.statusProperty().addListener(observable -> {
            text2.setText("Animation status: " + transition.getStatus());
        });
        text2.rotateProperty().bind(pane.rotateProperty());
        text2.textProperty().bindBidirectional(text.textProperty());
        text2.strokeProperty().bind(
                new When(transition.statusProperty().isEqualTo(Animation.Status.RUNNING))
                .then(Color.GREEN).otherwise(Color.RED));

        var scene = new Scene(box, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}