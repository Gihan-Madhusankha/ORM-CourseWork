package controller;

import bo.BOFactory;
import bo.custom.RoomBO;
import dto.RoomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Gihan Madhusankha
 * 2022-06-24 10:55 PM
 **/

public class AvailableRoomFormController {
    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    public TableView<RoomDTO> tblAvailable;
    public TableColumn colRoomTypeId;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colRoomQty;
    public AnchorPane availableContext;
    public TextField searchTextField;

    public void initialize() {
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("roomQty"));

        loadAllRoomDetails();
    }

    private void loadAllRoomDetails() {
        try {
            ArrayList<RoomDTO> allRooms = null;
            allRooms = roomBO.getAllRooms();
            ObservableList<RoomDTO> obList = FXCollections.observableArrayList(allRooms);
            tblAvailable.setItems(obList);

            FilteredList<RoomDTO> filteredList = new FilteredList<>(obList, b -> true);
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(searchModel -> {

                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();
                    if (searchModel.getRoomTypeId().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else return (searchModel.getType().toLowerCase().indexOf(searchKeyword) > -1);

                });
            });

            SortedList<RoomDTO> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tblAvailable.comparatorProperty());
            tblAvailable.setItems(sortedList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) availableContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
        stage.show();

    }

    public void checkRoomsBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) availableContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/CheckRoomsForm.fxml"))));
        stage.centerOnScreen();
    }
}
