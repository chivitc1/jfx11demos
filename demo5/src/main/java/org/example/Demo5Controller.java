package org.example;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Demo5Controller implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    private RotateTransition rotate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rotate = new RotateTransition();
        rotate.setDuration(Duration.millis(2500));
        rotate.setNode(stackPane);
        rotate.setFromAngle(0);
        rotate.setToAngle(360);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.statusProperty().addListener((observable, oldValue, newValue) -> {
            text2.setText("Was " + oldValue + ", Now " + newValue);
        });

        text2.strokeProperty().bind(new When(rotate.statusProperty().isEqualTo(Animation.Status.RUNNING))
                .then(Color.GREEN).otherwise(Color.RED));

    }

    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {
        if (rotate.getStatus().equals(Animation.Status.RUNNING)) {
            rotate.pause();
        } else {
            rotate.play();
        }
    }
}
