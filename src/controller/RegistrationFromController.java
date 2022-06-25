package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import util.ValidateUtil;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 12:39 PM
 **/

public class RegistrationFromController {

    public AnchorPane regContext;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public JFXComboBox<String> cmbGender;
    public TextField txtContactNo;
    public TextField txtRegDate;
    public JFXButton btnReg;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        loadGenderList();
        btnReg.setDisable(true);
        txtRegDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        txtRegDate.setEditable(false);

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
    }

    public void clearFormButtonOnAction(ActionEvent actionEvent) {
    }

    public void textFieldKeyReleased(KeyEvent keyEvent) {
        ValidateUtil.validate(map, btnReg);
    }

    public void backBtnOnAction(ActionEvent actionEvent) {
    }
}
