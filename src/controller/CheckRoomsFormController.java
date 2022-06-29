package controller;

import bo.custom.ReservationBO;
import bo.custom.impl.ReservationBOImpl;
import dto.ReservationDTO;
import entity.Reservation;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import view.tm.ReservationListTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : Gihan Madhusankha
 * 2022-06-29 12:44 AM
 **/

public class CheckRoomsFormController {
    private final ReservationBO reservationBO = new ReservationBOImpl();

    public AnchorPane checkTheRoomContext;
    public TableView<ReservationListTM> tblCheckTheRoom;
    public TableColumn colResID;
    public TableColumn colStudentID;
    public TableColumn colRoomTypeID;
    public TableColumn colRegDate;
    public TableColumn colStatus;
    public TableColumn colOperate;
    public TextField searchTextField;

    public void initialize(){
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

    private void deleteReservation(ImageView delete) {
        delete.setOnMouseClicked(event -> {
            ReservationListTM reservationDTO = tblCheckTheRoom.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get().equals(ButtonType.YES)){
                reservationBO.deleteReservationByResID(reservationDTO.getResId());

                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong..!").show();
            }

        });
    }

    private void loadReservationDetails() {
        ArrayList<ReservationListTM> roomDetails = reservationBO.getAllBookingRoomDetails();
        ObservableList<ReservationListTM> obList = FXCollections.observableArrayList();
        for (ReservationListTM detail : roomDetails) {
            obList.add(new ReservationListTM(
                detail.getResId(), detail.getDate(), detail.getStatus(), detail.getStudent(), detail.getRoom()
            ));
        }
        tblCheckTheRoom.setItems(obList);
    }

    public void backBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) checkTheRoomContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/AvailableRoomForm.fxml"))));
        stage.centerOnScreen();
    }
}
