package controller;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.NotificationUtil;

import java.io.IOException;

/**
 * @author : Gihan Madhusankha
 * 2022-06-29 1:11 PM
 **/

public class LoginFormController {
    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public JFXButton btnLogin;
    public AnchorPane loginContext;
    public JFXTextField txtUserName;
    public Label lblCreateAnAccount;
    public JFXPasswordField txtHidePwd;
    public JFXTextField txtShowPwd;
    public ImageView imgCloseEye;
    public ImageView imgOpenEye;
    private String password;

    public void initialize() {
        txtShowPwd.setVisible(false);
        imgOpenEye.setVisible(false);

        lblCreateAnAccount.setOnMouseClicked(event -> {
            Stage stage = (Stage) loginContext.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/SignUpForm.fxml"))));
                stage.setTitle("Sign up");
                stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void enteredMouseEffect(MouseEvent event) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.WHITE);
        btnLogin.setEffect(glow);
    }

    public void exitedMouseEffect(MouseEvent event) {
        btnLogin.setEffect(null);
    }

    public void loginBtnOnAction(ActionEvent actionEvent) {
        try {
            String password = null;
            password = userBO.getPasswordByUserName(txtUserName.getText());
            if (txtHidePwd.getText().equals(password)) {
                Stage stage = (Stage) loginContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
                stage.setTitle("Dashboard");
                stage.centerOnScreen();

            } else {
                new NotificationUtil().showNotification("error", "ERROR", "username or password is incorrect..!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openEyeOnAction(MouseEvent event) {
        txtShowPwd.setVisible(false);
        txtHidePwd.setVisible(true);
        imgCloseEye.setVisible(true);
        imgOpenEye.setVisible(false);
    }

    public void CloseEyeOnAction(MouseEvent event) {
        txtShowPwd.setVisible(true);
        txtHidePwd.setVisible(false);
        imgCloseEye.setVisible(false);
        imgOpenEye.setVisible(true);
    }

    public void showPasswordKeyReleasedOnAction(KeyEvent keyEvent) {
        password = txtShowPwd.getText();
        txtHidePwd.setText(password);
    }

    public void HidePasswordKeyReleasedOnAction(KeyEvent keyEvent) {
        password = txtHidePwd.getText();
        txtShowPwd.setText(password);
    }


}
