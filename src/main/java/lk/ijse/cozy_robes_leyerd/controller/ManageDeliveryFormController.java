package lk.ijse.cozy_robes_leyerd.controller;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import lk.ijse.cozy_robes_leyerd.bo.BOFactory;
import lk.ijse.cozy_robes_leyerd.bo.custom.DeliveryBO;
import lk.ijse.cozy_robes_leyerd.dto.DeliveryDTO;
import lk.ijse.cozy_robes_leyerd.entity.Delivery;
import lk.ijse.cozy_robes_leyerd.viewTm.DeliveryTM;
import lk.ijse.cozy_robes_leyerd.dao.Impl.DeliveryDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManageDeliveryFormController implements Initializable {
    public AnchorPane ancDeliveryPage;


//    private final DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();

    private DeliveryBO deliveryBO =(DeliveryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DELIVERY);

    public TableView<DeliveryTM> tblDelivery;
    public TableColumn<DeliveryTM, String> colId;
    public TableColumn<DeliveryTM, String> colOrdersPlatform;
    public TableColumn<DeliveryTM, String> colAddress;
    public TableColumn<DeliveryTM, String> colStatus;

    public Label lblDeliveryId;
    public TextField txtOrders;
    public TextField txtAddress;
    public TextField txtStatus;

    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public Button btnReset;
    public TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        colOrdersPlatform.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));



        try {
            loadTableData();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to initialize delivery page").show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String deliveryId = deliveryBO.getNextId();
        lblDeliveryId.setText(deliveryId);

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this delivery?",
                ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String deliveryId = lblDeliveryId.getText();
            try {
                boolean isDeleted = deliveryBO.delete(deliveryId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Delivery deleted successfully").show();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete Delivery").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete Delivery").show();
            }
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String deliveryId = lblDeliveryId.getText();
        String orderId = txtOrders.getText();
        String address = txtAddress.getText();
        String status = txtStatus.getText();

        if (deliveryId.isEmpty() || orderId == null || address.isEmpty() || status.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }



        try {
            boolean isUpdated = deliveryBO.update(new DeliveryDTO(deliveryId,orderId,address,status));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Delivery updated successfully").show();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Delivery").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String deliveryId = lblDeliveryId.getText();
        String orderId = txtOrders.getText();
        String address = txtAddress.getText();
        String status = txtStatus.getText();

        if (deliveryId.isEmpty() || orderId == null || address.isEmpty() || status.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }


        try {
            boolean isSaved = deliveryBO.save(new DeliveryDTO(deliveryId,orderId,address,status));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Delivery saved successfully").show();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Delivery").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving").show();
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();
            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            txtOrders.clear();
            txtAddress.clear();
            txtStatus.clear();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to reset page").show();
        }
    }

    public void loadTableData() throws SQLException {
        tblDelivery.setItems(FXCollections.observableArrayList(
                deliveryBO.getAll().stream()
                        .map(dto -> new DeliveryTM(
                                dto.getDeliveryId(),
                                dto.getOrderId(),
                                dto.getAddress(),
                                dto.getStatus()))
                        .toList()
        ));
    }

    public void onClickedTable(MouseEvent mouseEvent) {
        DeliveryTM selected = tblDelivery.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblDeliveryId.setText(selected.getDeliveryId());
           txtOrders.setText(selected.getOrderId());
            txtAddress.setText(selected.getAddress());
            txtStatus.setText(selected.getStatus());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void goToDashboard(MouseEvent mouseEvent) throws IOException {
        ancDeliveryPage.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/DashBoardPage.fxml"));
        ancDeliveryPage.getChildren().add(load);
    }

    public void search(KeyEvent keyEvent) {
        String search = txtSearch.getText();
        if (search.isEmpty()) {
            try {
                loadTableData();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to search").show();
            }

        }else {
            try {
                ArrayList<DeliveryDTO> deliveryList = deliveryBO.search(search);
                tblDelivery.setItems(FXCollections.observableArrayList(
                        deliveryList.stream()
                                .map(deliveryDto -> new DeliveryTM(
                                        deliveryDto.getDeliveryId(),
                                        deliveryDto.getOrderId(),
                                        deliveryDto.getAddress(),
                                        deliveryDto.getStatus()
                                )).toList()
                ));

            }catch(Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to search").show();
            }
        }
    }
}
