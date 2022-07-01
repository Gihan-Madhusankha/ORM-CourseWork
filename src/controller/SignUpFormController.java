package controller;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.UserDTO;
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
import util.ValidateJFXUtil;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @author : Gihan Madhusankha
 * 2022-06-30 11:35 AM
 **/

public class SignUpFormController {
    private final LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public AnchorPane signUpContext;
    public JFXTextField txtUserName;
    public JFXTextField txtEmpName;
    public JFXDatePicker txtDob;
    public JFXPasswordField txtHidePwd;
    public JFXTextField txtShowPwd;
    public JFXButton btnCreateAccount;
    public Label lblAlreadyAccount;
    public ImageView imgCloseEye;
    public ImageView imgOpenEye;
    public JFXTextField txtEmpAddress;
    private String password;

    public void initialize() {
        txtShowPwd.setVisible(false);
        imgOpenEye.setVisible(false);
        txtDob.setEditable(false);
        txtUserName.setEditable(false);

        try {
            txtUserName.setText(userBO.generateUserId());

        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern namePattern = Pattern.compile("^[A-z ]*$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/.]*$");
        map.put(txtEmpName, namePattern);
        map.put(txtEmpAddress, addressPattern);

        lblAlreadyAccount.setOnMouseClicked(event -> {
            Stage stage = (Stage) signUpContext.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
                stage.centerOnScreen();
                stage.setTitle("Login");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void openEyeOnAction(MouseEvent event) {
        txtShowPwd.setVisible(false);
        txtHidePwd.setVisible(true);
        imgOpenEye.setVisible(false);
        imgCloseEye.setVisible(true);
    }

    public void CloseEyeOnAction(MouseEvent event) {
        txtShowPwd.setVisible(true);
        txtHidePwd.setVisible(false);
        imgOpenEye.setVisible(true);
        imgCloseEye.setVisible(false);
    }

    public void showPasswordKeyReleasedOnAction(KeyEvent keyEvent) {
        password = txtShowPwd.getText();
        txtHidePwd.setText(password);
    }

    public void HidePasswordKeyReleasedOnAction(KeyEvent keyEvent) {
        password = txtHidePwd.getText();
        txtShowPwd.setText(password);
    }

    public void enteredMouseEffect(MouseEvent event) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.WHITE);
        btnCreateAccount.setEffect(glow);
    }

    public void exitedMouseEffect(MouseEvent event) {
        btnCreateAccount.setEffect(null);
    }

    public void signUpBtnOnAction(ActionEvent actionEvent) {
        if (txtDob.getValue() != null && txtHidePwd.getText() != null) {
            try {
                String userName = userBO.generateUserId();

                boolean b = userBO.saveUser(new UserDTO(
                        userName, txtEmpName.getText(), txtEmpAddress.getText(), txtDob.getValue(), txtHidePwd.getText()
                ));
                if (b) {
                    Stage stage = (Stage) signUpContext.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
                    stage.centerOnScreen();
                    new NotificationUtil().showNotification("confirm", "CONFIRMATION", "Account created successfully");

                } else {
                    new NotificationUtil().showNotification("error", "ERROR", "something else...!");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            new NotificationUtil().showNotification("error", "ERROR", "Not complete...!");
        }
    }

    public void textFieldKeyReleased(KeyEvent keyEvent) {
        ValidateJFXUtil.validate(map, btnCreateAccount);
    }
}
