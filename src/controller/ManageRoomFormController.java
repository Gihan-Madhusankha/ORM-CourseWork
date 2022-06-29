package controller;

import bo.BOFactory;
import bo.custom.RoomBO;
import bo.custom.impl.RoomBOImpl;
import com.jfoenix.controls.JFXButton;
import dto.RoomDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.ValidateUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author : Gihan Madhusankha
 * 2022-06-26 2:03 AM
 **/

public class ManageRoomFormController {
    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    private final LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    public AnchorPane roomContext;
    public TextField txtRoomTypeId;
    public TextField txtRoomType;
    public TextField txtKeyMoney;
    public TextField txtRoomQty;
    public TableView<RoomDTO> tblManageRoom;
    public TableColumn colRoomTypeId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colRoomQty;
    public TableColumn colOperate;
    public JFXButton btnAddNew;
    public TextField searchTextField;
    private ObservableList<RoomDTO> obList = null;
    private RoomDTO roomDTO = null;

    public void initialize() {
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("roomQty"));
        colOperate.setCellValueFactory(param -> {

            ImageView edit = new ImageView("view/asserts/image/edit.png");
            ImageView delete = new ImageView("view/asserts/image/delete.png");

            HBox hBox = new HBox(edit, delete);
            edit.setFitHeight(20);
            edit.setFitWidth(20);
            delete.setFitHeight(20);
            delete.setFitWidth(20);
            edit.setStyle("-fx-cursor: Hand");
            delete.setStyle("-fx-cursor: Hand");

            hBox.setStyle("-fx-alignment: center");
            HBox.setMargin(edit, new Insets(2, 3, 2, 2));
            HBox.setMargin(delete, new Insets(2, 2, 2, 3));

            clickedEditBtn(edit);
            deleteStudent(delete);
            return new ReadOnlyObjectWrapper<>(hBox);

        });

        loadAllRoomList();
        btnAddNew.setDisable(true);

        Pattern roomTypeIdPattern = Pattern.compile("^(RM-)[0-9]{4}$");
        Pattern roomTypePattern = Pattern.compile("^[A-z /]*$");
        Pattern keyMoneyPattern = Pattern.compile("^[1-9][0-9]*?(.[0-9]{2})$");
        Pattern roomQtyPattern = Pattern.compile("^[0-9]{1,}$");

        map.put(txtRoomTypeId, roomTypeIdPattern);
        map.put(txtRoomType, roomTypePattern);
        map.put(txtKeyMoney, keyMoneyPattern);
        map.put(txtRoomQty, roomQtyPattern);
    }

    private void filteredList() {
        FilteredList<RoomDTO> filteredList = new FilteredList<>(obList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredList.setPredicate(searchModel -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (searchModel.getRoomTypeId().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                return (searchModel.getType().toLowerCase().indexOf(searchKeyword) > -1);
            });
        });

        SortedList<RoomDTO> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblManageRoom.comparatorProperty());
        tblManageRoom.setItems(sortedList);

    }

    private void deleteStudent(ImageView delete) {
        delete.setOnMouseClicked(event -> {
            filteredList();
            roomDTO = tblManageRoom.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?",
                    ButtonType.NO, ButtonType.YES);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get().equals(ButtonType.YES)) {
                try {
                    roomBO.deleteRoom(roomDTO.getRoomTypeId());
                    clearForm();
                    obList.clear();
                    loadAllRoomList();
                    new Alert(Alert.AlertType.CONFIRMATION, "Deleted...").show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void clickedEditBtn(ImageView edit) {
        edit.setOnMouseClicked(event -> {
            filteredList();
            roomDTO = tblManageRoom.getSelectionModel().getSelectedItem();
            txtRoomTypeId.setText(roomDTO.getRoomTypeId());
            txtRoomType.setText(roomDTO.getType());
            txtKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
            txtRoomQty.setText(String.valueOf(roomDTO.getRoomQty()));
            btnAddNew.setDisable(false);
            txtRoomTypeId.setEditable(false);
            btnAddNew.setText("UPDATE");
        });
    }

    private void loadAllRoomList() {
        ArrayList<RoomDTO> allRooms = null;
        try {
            allRooms = roomBO.getAllRooms();
            obList = FXCollections.observableArrayList(allRooms);
            tblManageRoom.setItems(obList);
            filteredList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) roomContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"))));
        stage.show();
    }

    public void addBtnOnAction(ActionEvent actionEvent) {
        /*save room*/
        try {

            if (btnAddNew.getText().equals("ADD")) {
                boolean b = roomBO.saveRoom(new RoomDTO(
                        txtRoomTypeId.getText(), txtRoomType.getText(), Double.parseDouble(txtKeyMoney.getText()), Integer.parseInt(txtRoomQty.getText())
                ));

                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Added").show();
                    clearForm();
                    obList.clear();
                    ValidateUtil.setBorders(txtRoomTypeId, txtRoomType, txtKeyMoney, txtRoomQty);
                    loadAllRoomList();
                }

                /*update room*/
            } else {
                boolean b = roomBO.updateRoom(new RoomDTO(
                        txtRoomTypeId.getText(), txtRoomType.getText(), Double.parseDouble(txtKeyMoney.getText()), Integer.parseInt(txtRoomQty.getText())
                ));

                if (b) {
                    clearForm();
                    obList.clear();
                    loadAllRoomList();
                    ValidateUtil.setBorders(txtRoomTypeId, txtRoomType, txtKeyMoney, txtRoomQty);
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong...!").show();
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        txtRoomTypeId.clear();
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtRoomQty.clear();
        btnAddNew.setDisable(true);
        txtRoomTypeId.setEditable(true);
        btnAddNew.setText("ADD");
    }

    public void textFieldKeyReleased(KeyEvent keyEvent) {
        ValidateUtil.validate(map, btnAddNew);
    }

}
