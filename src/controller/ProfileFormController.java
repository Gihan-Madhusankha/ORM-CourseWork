package controller;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.NotificationUtil;
import util.ValidateJFXUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @author : Gihan Madhusankha
 * 2022-07-01 3:12 PM
 **/

public class ProfileFormController {
    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    private final LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    public AnchorPane profileContext;
    public JFXTextField txtUserName;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXDatePicker txtDob;
    public JFXTextField txtPassword;
    public JFXButton btnSaveChanges;

    public void initialize() {
        txtUserName.setEditable(false);
        txtDob.setEditable(false);
        btnSaveChanges.setDisable(true);
        loadAllUserDetails();

        Pattern namePattern = Pattern.compile("^[A-z ]*$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/.]*$");
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
    }

    private void loadAllUserDetails() {
        try {
            ArrayList<UserDTO> dto = userBO.getDetailsByUserName(LoginFormController.user_name);
            for (UserDTO userDTO : dto) {
                txtUserName.setText(userDTO.getUserName());
                txtName.setText(userDTO.getName());
                txtAddress.setText(userDTO.getAddress());
                txtDob.setValue(userDTO.getDate());
                txtPassword.setText(userDTO.getPassword());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        backtoMenu();
    }

    private void backtoMenu() throws IOException {
        Stage stage = (Stage) profileContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
    }

    public void saveChangesOnAction(ActionEvent actionEvent) {
        try {
            boolean update = userBO.updateUserInfo(new UserDTO(
                    txtUserName.getText(), txtName.getText(), txtAddress.getText(), txtDob.getValue(), txtPassword.getText()
            ));
            if (update) {
                new NotificationUtil().showNotification("confirm", "CONFIRMATION", "Updated..!");
            } else {
                new NotificationUtil().showNotification("error", "ERROR", "Something went wrong..!");
            }
            backtoMenu();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void textFieldKeyReleased(KeyEvent keyEvent) {
        if (txtPassword.getText().length() > 0 && txtName.getText().length() > 0
            && txtAddress.getText().length() > 0) {
            btnSaveChanges.setDisable(false);
        }
        ValidateJFXUtil.validate(map, btnSaveChanges);
    }
}
