package controller;

import bo.custom.RoomBO;
import bo.custom.StudentBO;
import bo.custom.impl.RoomBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.RoomDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Gihan Madhusankha
 * 2022-06-26 4:23 PM
 **/

public class ReserveRoomFormController {
    public AnchorPane bookingContext;
    public JFXComboBox<String> cmbRoomTypeId;
    public JFXComboBox<String> cmbStudentId;
    public TextField txtDate;
    public Label lblRoomNo;
    public TextField txtRoomType;
    public TextField txtName;
    public TextField txtStatus;
    public TextField txtKeyMoney;
    public TextField txtAddress;
    public JFXButton btnBook;
    private final RoomBO roomBO = new RoomBOImpl();
    private final StudentBO studentBO = new StudentBOImpl();

    public void initialize() {
        loadAllStudentIds();
        loadAllRoomTypeIds();
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null) {
                setStudentDetails(newValue);
            }
        });

        cmbRoomTypeId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null) {
                setRoomDetails(newValue);
            }
        });
    }

    private void setRoomDetails(String roomTypeId) {
        ArrayList<RoomDTO> roomDetailsByRoomTypeId = roomBO.getRoomDetailsByRoomTypeId(roomTypeId);
        for (RoomDTO roomDTO : roomDetailsByRoomTypeId) {
            txtRoomType.setText(roomDTO.getType());
            txtKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
        }
    }

    private void setStudentDetails(String id) {
        ArrayList<StudentDTO> studentDetailsById = studentBO.getStudentDetailsById(id);
        for (StudentDTO s : studentDetailsById) {
            txtName.setText(s.getName());
            txtAddress.setText(s.getAddress());
        }
    }

    private void loadAllStudentIds() {
        ArrayList<StudentDTO> allStudents = studentBO.getAllStudents();
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (StudentDTO allStudent : allStudents) {
            obList.add(allStudent.getId());
        }
        cmbStudentId.setItems(obList);
    }

    private void loadAllRoomTypeIds() {
        ArrayList<RoomDTO> allRooms = roomBO.getAllRooms();
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (RoomDTO allRoom : allRooms) {
            obList.add(allRoom.getRoomTypeId());
        }
        cmbRoomTypeId.setItems(obList);
    }


    public void textFieldKeyReleased(KeyEvent keyEvent) {
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) bookingContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
        stage.centerOnScreen();
    }

    public void bookBtnOnAction(ActionEvent actionEvent) {
    }

    public void cancelBtnOnAction(ActionEvent actionEvent) {
    }
}
