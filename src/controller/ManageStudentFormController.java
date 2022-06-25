package controller;

import bo.custom.StudentBO;
import bo.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : Gihan Madhusankha
 * 2022-06-25 9:52 PM
 **/

public class ManageStudentFormController {
    public AnchorPane studentContext;
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXComboBox<String> cmbGender;
    public JFXButton btnUpdate;
    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTelNo;
    public TableColumn colGender;
    public TableColumn colRegDate;
    public TableColumn colOperate;
    public TextField searchTextField;
    public TableView<StudentDTO> tblManageStudent;
    private StudentDTO studentDTO = null;

    public void initialize() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
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
            HBox.setMargin(edit, new Insets(2,3,2,2));
            HBox.setMargin(delete, new Insets(2,2,2,3));

            clickedEditBtn(edit);
//            deleteStudent(delete);
            return new ReadOnlyObjectWrapper<>(hBox);
        });

        loadAllStudents();
        btnUpdate.setDisable(true);
    }

    private void clickedEditBtn(ImageView edit) {
        edit.setOnMouseClicked(event -> {
            studentDTO = tblManageStudent.getSelectionModel().getSelectedItem();
            txtStudentId.setText(studentDTO.getId());
            txtName.setText(studentDTO.getName());
            txtAddress.setText(studentDTO.getAddress());
            txtContactNo.setText(studentDTO.getContactNo());
            cmbGender.setValue(studentDTO.getGender());
            btnUpdate.setDisable(false);
        });
    }

    private void loadAllStudents() {
        StudentBO studentBO = new StudentBOImpl();
        ArrayList<StudentDTO> allStudents = studentBO.getAllStudents();
        ObservableList<StudentDTO> obList = FXCollections.observableArrayList(allStudents);
        tblManageStudent.setItems(obList);
    }

    public void backBtnOnAction(ActionEvent actionEvent) {

    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
    }

}
