package controller;

import bo.custom.ReservationBO;
import bo.custom.RoomBO;
import bo.custom.StudentBO;
import bo.custom.impl.ReservationBOImpl;
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

/**
 * @author : Gihan Madhusankha
 * 2022-06-26 4:23 PM
 **/

public class ReserveRoomFormController {
    private final RoomBO roomBO = new RoomBOImpl();
    private final StudentBO studentBO = new StudentBOImpl();
    private final ReservationBO reservationBO = new ReservationBOImpl();
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

    public void initialize() {
        loadAllStudentIds();
        loadAllRoomTypeIds();
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtRoomType.setEditable(false);
        txtKeyMoney.setEditable(false);
        txtDate.setEditable(false);
        cmbStudentId.setEditable(false);
        cmbRoomTypeId.setEditable(false);
        btnBook.setDisable(true);

        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setStudentDetails(newValue);
            }
        });

        cmbRoomTypeId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setRoomDetails(newValue);
            }
        });
    }

    private void setRoomDetails(String roomTypeId) {
        ArrayList<RoomDTO> roomDetailsByRoomTypeId = roomBO.getRoomDetailsByRoomTypeId(roomTypeId);
        for (RoomDTO roomDTO : roomDetailsByRoomTypeId) {
            String rmTypeId = roomDTO.getRoomTypeId();
            String rmType = roomDTO.getType();
            txtRoomType.setText(rmType);
            txtKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
            generateRoomNo(rmTypeId, rmType);
        }

    }

    private void generateRoomNo(String typeId, String type) {
        String s = reservationBO.generateRoomIdByRoomType(typeId, type);
        lblRoomNo.setText(s);
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
        if (txtStatus.getText().length() > 0) {
            btnBook.setDisable(false);
        }
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) bookingContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
        stage.centerOnScreen();
    }

    public void bookBtnOnAction(ActionEvent actionEvent) {
    }

    public void clearFormOnAction(ActionEvent actionEvent) {
        clearForm();
    }

    private void clearForm() {
        cmbStudentId.getSelectionModel().clearSelection();
        cmbRoomTypeId.getSelectionModel().clearSelection();
        txtName.clear();
        txtAddress.clear();
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtStatus.clear();
        lblRoomNo.setText("ROOM NO");
    }
}
