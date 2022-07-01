package controller;

import bo.BOFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.NotificationUtil;
import util.ValidateUtil;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:39 PM
 **/

public class RegistrationFromController {

    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    public AnchorPane regContext;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public JFXComboBox<String> cmbGender;
    public TextField txtContactNo;
    public JFXButton btnReg;
    public JFXDatePicker txtDob;
    public JFXButton btnManage;
    public JFXButton btnBookTheRoom;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        loadGenderList();
        btnReg.setDisable(true);
        txtDob.setEditable(false);
        btnManage.setDisable(true);
        btnBookTheRoom.setDisable(true);

        Pattern idPattern = Pattern.compile("^(S00-)[0-9]{3}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,./]*$");
        Pattern contactPattern = Pattern.compile("^07(0|1|2|5|6|7|8)[0-9]{7}$");

        map.put(txtId, idPattern);
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtContactNo, contactPattern);
    }

    private void loadGenderList() {
        ObservableList<String> genList = FXCollections.observableArrayList();
        genList.add("Male");
        genList.add("Female");
        cmbGender.setItems(genList);
    }

    public void registerBtnOnAction(ActionEvent actionEvent) {
        boolean b = (txtId.getText() != null) && (txtName.getText() != null) && (txtAddress.getText() != null) && (!txtAddress.getText().equals(" ")) && (txtContactNo.getText() != null)
                && (cmbGender.getValue() != null) && (txtDob.getValue() != null);

        if (!b) {
            new NotificationUtil().showNotification("warning", "WARNING", "Some values are empty..!");
            return;
        }

        /*save student*/
        boolean saveStudent = false;
        try {
            saveStudent = studentBO.saveStudent(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(),
                    txtContactNo.getText(), txtDob.getValue(), cmbGender.getValue()));
            if (saveStudent) {
                new NotificationUtil().showNotification("confirm", "CONFIRMATION", "Registration successfully.");
                btnManage.setDisable(false);
                btnBookTheRoom.setDisable(false);
                clearForm();

            } else {
                new NotificationUtil().showNotification("error", "ERROR", "Something went wrong..!!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void clearFormButtonOnAction(ActionEvent actionEvent) {
        clearForm();
    }

    private void clearForm() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        cmbGender.getSelectionModel().clearSelection();
        btnReg.setDisable(true);
        txtDob.getEditor().clear();
        ValidateUtil.setBorders(txtId, txtName, txtAddress, txtContactNo);
    }

    public void textFieldKeyReleased(KeyEvent keyEvent) {
        ValidateUtil.validate(map, btnReg);
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) regContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
        stage.show();
    }

    public void manageBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageStudentForm.fxml"))));
        stage.show();
    }

    public void bookTheRoomBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ReserveRoomForm.fxml"))));
        stage.show();
    }
}
