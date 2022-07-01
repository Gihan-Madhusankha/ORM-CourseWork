package controller;

import bo.BOFactory;
import bo.custom.ReservationBO;
import bo.custom.RoomBO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.NotificationUtil;
import view.tm.ReservationListTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : Gihan Madhusankha
 * 2022-06-29 12:44 AM
 **/

public class CheckRoomsFormController {
    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    public AnchorPane checkTheRoomContext;
    public TableView<ReservationListTM> tblCheckTheRoom;
    public TableColumn colResID;
    public TableColumn colStudentID;
    public TableColumn colRoomTypeID;
    public TableColumn colRegDate;
    public TableColumn colStatus;
    public TableColumn colOperate;
    public TextField searchTextField;
    private ObservableList<ReservationListTM> obList = null;

    public void initialize() {
        colResID.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("student"));
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("room"));
        colOperate.setCellValueFactory(param -> {
            ImageView delete = new ImageView("view/asserts/image/delete.png");

            HBox hBox = new HBox(delete);
            delete.setFitWidth(20);
            delete.setFitHeight(20);

            hBox.setStyle("-fx-cursor: Hand");
            hBox.setAlignment(Pos.CENTER);
            deleteReservation(delete);
            return new ReadOnlyObjectWrapper<>(hBox);
        });

        loadReservationDetails();
    }

    private void filteredList() {
        FilteredList<ReservationListTM> filteredList = new FilteredList<>(obList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(searchModel -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (searchModel.getResId().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (searchModel.getStatus().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (searchModel.getStudent().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                return searchModel.getRoom().toLowerCase().indexOf(searchKeyword) > -1;

            });
        });

        SortedList<ReservationListTM> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblCheckTheRoom.comparatorProperty());
        tblCheckTheRoom.setItems(sortedList);

    }

    private void deleteReservation(ImageView delete) {
        delete.setOnMouseClicked(event -> {
            ReservationListTM reservationDTO = tblCheckTheRoom.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get().equals(ButtonType.YES)) {
                try {
                    reservationBO.deleteReservationByResID(reservationDTO.getResId());
                    updateRoomQty(reservationDTO.getRoom());
                    obList.clear();
                    loadReservationDetails();
                    filteredList();

                    new NotificationUtil().showNotification("confirm", "CONFIRMATION", "Deleted");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                new NotificationUtil().showNotification("error", "ERROR", "Something went wrong..!");
            }

        });
    }

    private void updateRoomQty(String roomTypeId) {
        try {
            roomBO.updateQtyOfRoom(roomTypeId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadReservationDetails() {
        ArrayList<ReservationListTM> roomDetails = null;
        try {
            roomDetails = reservationBO.getAllBookingRoomDetails();
            obList = FXCollections.observableArrayList();
            for (ReservationListTM detail : roomDetails) {
                obList.add(new ReservationListTM(
                        detail.getResId(), detail.getDate(), detail.getStatus(), detail.getStudent(), detail.getRoom()
                ));
            }
            tblCheckTheRoom.setItems(obList);
            filteredList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) checkTheRoomContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/AvailableRoomForm.fxml"))));
        stage.centerOnScreen();
    }
}
