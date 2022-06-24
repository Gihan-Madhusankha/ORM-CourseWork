package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-18 11:00 PM
 **/

public class MainFormController {
    public ImageView imgAvailable;
    public ImageView imgRegistration;
    public ImageView imgManageStudents;
    public ImageView imgManageRooms;
    public ImageView imgBooking;
    public AnchorPane mainContext;
    public Label lblAvailable;
    public Label lblRegistration;
    public Label lblManageStudents;
    public Label lblMangeRooms;
    public Label lblBooking;

    public void initialize() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), mainContext);
        fadeTransition.play();
    }

    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), icon);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();

            switch (icon.getId()) {
                case "imgAvailable":
                    enterScaleLabel(lblAvailable);
                    break;
                case "imgRegistration":
                    enterScaleLabel(lblRegistration);
                    break;
                case "imgManageStudents":
                    enterScaleLabel(lblManageStudents);
                    break;
                case "imgManageRooms":
                    enterScaleLabel(lblMangeRooms);
                    break;
                case "imgBooking":
                    enterScaleLabel(lblBooking);
            }

        }
    }

    private void enterScaleLabel(Label label) {
        label.setTextFill(Color.rgb(255, 118, 117));
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), icon);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();

            ArrayList<Label> lblList = new ArrayList<>();
            lblList.add(lblAvailable);
            lblList.add(lblRegistration);
            lblList.add(lblManageStudents);
            lblList.add(lblMangeRooms);
            lblList.add(lblBooking);
            exitScaleLabel(lblList);
        }
    }

    private void exitScaleLabel(ArrayList<Label> lblList) {
        for (Label label : lblList) {
            label.setTextFill(Color.rgb(45, 52, 54));
        }
    }
}
