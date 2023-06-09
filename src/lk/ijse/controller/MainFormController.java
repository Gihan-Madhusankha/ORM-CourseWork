package lk.ijse.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
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
    public ImageView imgSetting;
    public AnchorPane mainContext;
    public Label lblAvailable;
    public Label lblRegistration;
    public Label lblManageStudents;
    public Label lblMangeRooms;
    public Label lblBooking;
    public ImageView imgPowerBtn;

    public void initialize() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), mainContext);
        fadeTransition.play();

        imgPowerBtn.setOnMouseClicked(event -> {
            try {
                Stage stage = (Stage) mainContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
                stage.centerOnScreen();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), icon);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.BLUEVIOLET);/*CORNFLOWERBLUE*/
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);

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
        label.setTextFill(Color.BLUEVIOLET);
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), icon);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
            icon.setEffect(null);

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
            label.setTextFill(Color.BLACK);
        }
    }

    public void playMouseClickedAnimation(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            Stage stage = (Stage) mainContext.getScene().getWindow();

            switch (icon.getId()) {
                case "imgAvailable":
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/AvailableRoomForm.fxml"))));
                    stage.setTitle("Available");
                    break;
                case "imgRegistration":
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/RegistrationFrom.fxml"))));
                    stage.setTitle("Registration");
                    break;
                case "imgManageStudents":
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageStudentForm.fxml"))));
                    stage.setTitle("Manage Students");
                    break;
                case "imgManageRooms":
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageRoomForm.fxml"))));
                    stage.setTitle("Manage Rooms");
                    break;
                case "imgBooking":
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ReserveRoomForm.fxml"))));
                    stage.setTitle("Book the Room");
                    break;
                case "imgSetting":
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ProfileForm.fxml"))));
                    stage.setTitle("Setting");
                    break;
                default:
                    System.out.println("Default");
            }
            stage.centerOnScreen();
        }
    }

}
