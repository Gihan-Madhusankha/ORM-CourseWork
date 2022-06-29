package controller;

import bo.custom.ReservationBO;
import bo.custom.RoomBO;
import bo.custom.StudentBO;
import bo.custom.impl.ReservationBOImpl;
import bo.custom.impl.RoomBOImpl;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.tm.ReservationList;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    public TextField txtRoomType;
    public TextField txtName;
    public TextField txtStatus;
    public TextField txtKeyMoney;
    public TextField txtAddress;
    public JFXButton btnBook;
    public TableView<ReservationList> tblReservationList;
    public TableColumn colStId;
    public TableColumn colName;
    public TableColumn colRoomTypeId;
    public TableColumn colRoomType;
    public TableColumn colStatus;
    public TableColumn colOperate;
    public JFXButton btnAddToList;
    private ObservableList<ReservationList> obList = null;

    public void initialize() {
        colStId.setCellValueFactory(new PropertyValueFactory<>("stId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOperate.setCellValueFactory(param -> {
            ImageView delete = new ImageView("view/asserts/image/delete.png");

            HBox hBox = new HBox(delete);
            delete.setFitWidth(20);
            delete.setFitHeight(20);
            hBox.setAlignment(Pos.CENTER);
            delete.setStyle("-fx-cursor: Hand");
            deleteRoomofList(delete);
            return new ReadOnlyObjectWrapper<>(hBox);
        });

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

    private void deleteRoomofList(ImageView delete) {
        delete.setOnMouseClicked(event -> {
            obList.clear();
        });
    }

    private void setRoomDetails(String roomTypeId) {
        ArrayList<RoomDTO> roomDetailsByRoomTypeId = null;
        try {
            roomDetailsByRoomTypeId = roomBO.getRoomDetailsByRoomTypeId(roomTypeId);
            for (RoomDTO roomDTO : roomDetailsByRoomTypeId) {
                String rmTypeId = roomDTO.getRoomTypeId();
                String rmType = roomDTO.getType();
                txtRoomType.setText(rmType);
                txtKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setStudentDetails(String id) {
        ArrayList<StudentDTO> studentDetailsById = null;
        try {
            studentDetailsById = studentBO.getStudentDetailsById(id);
            for (StudentDTO s : studentDetailsById) {
                txtName.setText(s.getName());
                txtAddress.setText(s.getAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudentIds() {
        ArrayList<StudentDTO> allStudents = null;
        try {
            allStudents = studentBO.getAllStudents();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for (StudentDTO allStudent : allStudents) {
                obList.add(allStudent.getId());
            }
            cmbStudentId.setItems(obList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAllRoomTypeIds() {
        ArrayList<RoomDTO> allRooms = null;
        try {
            allRooms = roomBO.getAllRooms();
            ObservableList<String> obList = FXCollections.observableArrayList();
            for (RoomDTO allRoom : allRooms) {
                obList.add(allRoom.getRoomTypeId());
            }
            cmbRoomTypeId.setItems(obList);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            ArrayList<StudentDTO> s = studentBO.getStudentDetailsById(cmbStudentId.getValue());
            StudentDTO studentDTO = new StudentDTO();
            for (StudentDTO dto : s) {
                studentDTO.setId(dto.getId());
                studentDTO.setName(dto.getName());
                studentDTO.setAddress(dto.getAddress());
                studentDTO.setContactNo(dto.getContactNo());
                studentDTO.setDob(dto.getDob());
                studentDTO.setGender(dto.getGender());
            }

            ArrayList<RoomDTO> r = roomBO.getRoomDetailsByRoomTypeId(cmbRoomTypeId.getValue());
            RoomDTO roomDTO = new RoomDTO();
            for (RoomDTO dto : r) {
                roomDTO.setRoomTypeId(dto.getRoomTypeId());
                roomDTO.setType(dto.getType());
                roomDTO.setKeyMoney(dto.getKeyMoney());
                roomDTO.setRoomQty(dto.getRoomQty());
            }

            String s1 = reservationBO.generateResId();
            boolean b = reservationBO.bookTheRoom(new ReservationDTO(
                    s1, LocalDate.now(), txtStatus.getText(), studentDTO, roomDTO
            ));
            if (b) {
                roomBO.updateRoomQty(cmbRoomTypeId.getValue());
                clearForm();
                new Alert(Alert.AlertType.CONFIRMATION, "Room Reservation Successful.").show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        obList.clear();
        btnBook.setDisable(true);
    }

    public void addToListOnAction(ActionEvent actionEvent) {
        obList = FXCollections.observableArrayList();
        if (cmbStudentId.getValue() != null && cmbRoomTypeId.getValue() != null && txtStatus.getText() != null) {
            obList.add(new ReservationList(
                    cmbStudentId.getValue(), txtName.getText(), cmbRoomTypeId.getValue(), txtRoomType.getText(), txtStatus.getText()
            ));
        }
        tblReservationList.setItems(obList);
    }
}
