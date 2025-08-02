package lk.ijse.cozy_robes_leyerd.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cozy_robes_leyerd.bo.BOFactory;
import lk.ijse.cozy_robes_leyerd.bo.custom.WarehouseBO;
import lk.ijse.cozy_robes_leyerd.dto.WarehouseDTO;
import lk.ijse.cozy_robes_leyerd.entity.Warehouse;
import lk.ijse.cozy_robes_leyerd.viewTm.WarehouseTM;
import lk.ijse.cozy_robes_leyerd.dao.Impl.WarehouseDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageWarehouseFormController implements Initializable{

    public AnchorPane ancWarehousePage;

//    private final WarehouseModel warehouseModel = new WarehouseModel();
//     private WarehouseDAOImpl warehouseDAO = new WarehouseDAOImpl();

    private WarehouseBO warehouseBO = (WarehouseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.WAREHOUSE);

    public TableView<WarehouseTM> tblWarehouse;
    public TableColumn<WarehouseTM , String> colSectionId;
    public TableColumn<WarehouseTM , String> colProductId;
    public TableColumn<WarehouseTM , Integer> colCapacity;
    public TableColumn<WarehouseTM , String> colLocation;
    public ComboBox<String> cmbProductPlatform;
    public TextField txtCapacity;
    public TextField txtLocation;
    public Button btnBack;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public Button btnReset;
    public TextField txtSearch;
    public ComboBox<String> cmbSectionId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSectionId.setCellValueFactory(new PropertyValueFactory<>("sectionId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        cmbProductPlatform.setItems(FXCollections.observableArrayList( "P001" , "P002"));
        cmbSectionId.setItems(FXCollections.observableArrayList(" ", "Section_A" , "Section_B"));

        try {
            loadTableData();
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to load data!").show();
        }
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }


    private void loadTableData() throws SQLException {
        tblWarehouse.setItems(FXCollections.observableArrayList(
                warehouseBO.getAll().stream()
                        .map(warehouseDto -> new WarehouseTM(
                                warehouseDto.getSectionId(),
                                warehouseDto.getProductId(),
                                warehouseDto.getCapacity(),
                                warehouseDto.getLocation()
                        )).toList()
        ));
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this warehouse section ?",
                ButtonType.YES, ButtonType.NO
        );
        alert.setTitle("Confirm Delete");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            String sectionId = cmbSectionId.getValue();
            try {
                boolean isDeleted = warehouseBO.delete(sectionId);
                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Warehouse deleted successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Error deleting warehouse!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Failed to delete section").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String sectionId = cmbSectionId.getValue();
        String productId = cmbProductPlatform.getValue();
        int capacity = Integer.parseInt(txtCapacity.getText());
        String location = txtLocation.getText();

        if(sectionId.isEmpty() ||  capacity==0 || location.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill out all fields!").show();
            return;
        }


        try {
            boolean isUpdated = warehouseBO.update(new WarehouseDTO(sectionId, productId, capacity, location));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Warehouse updated successfully!").show();
                resetPage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to update warehouse!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String sectionId = cmbSectionId.getValue();
        String productId = cmbProductPlatform.getValue();
        String location = txtLocation.getText();
        int capacity;


        if (sectionId == null || productId == null || location.isEmpty() || txtCapacity.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill out all fields!").show();
            return;
        }

        try {
            capacity = Integer.parseInt(txtCapacity.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Capacity must be a number!").show();
            return;
        }


        boolean sectionExists = tblWarehouse.getItems().stream()
                .anyMatch(item -> item.getSectionId().equals(sectionId));
        if (sectionExists) {
            new Alert(Alert.AlertType.ERROR, "This section already exists! Use update instead.").show();
            return;
        }


        try {
            boolean isSaved = warehouseBO.save(new WarehouseDTO(sectionId, productId, capacity, location));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Warehouse saved successfully!").show();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save warehouse!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while saving the warehouse.").show();
        }
    }


    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }
    private void resetPage() {
        try {
            loadTableData();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            cmbProductPlatform.setValue(null);
            txtCapacity.clear();
            txtLocation.clear();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to load data!").show();
        }
    }

    public void onClickedTable(MouseEvent mouseEvent) {
        WarehouseTM warehouseTM = tblWarehouse.getSelectionModel().getSelectedItem();
        if (warehouseTM != null) {
            cmbSectionId.setValue(warehouseTM.getSectionId());
            cmbProductPlatform.setValue(warehouseTM.getProductId());
            txtCapacity.setText(String.valueOf(warehouseTM.getCapacity()));
            txtLocation.setText(warehouseTM.getLocation());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }


    public void goToDashboard(MouseEvent mouseEvent) throws IOException {
        ancWarehousePage.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/DashBoardPage.fxml"));
        ancWarehousePage.getChildren().add(load);

    }

    public void search(KeyEvent keyEvent) {
        String search = txtSearch.getText();
        if(search.isEmpty()) {
            try {
                loadTableData();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Failed to search").show();
            }
        }else {
            try {
                ArrayList<WarehouseDTO> sectionList = warehouseBO.search(search);
                tblWarehouse.setItems(FXCollections.observableArrayList(
                        sectionList.stream()
                                .map(warehouseDto -> new WarehouseTM(
                                        warehouseDto.getSectionId(),
                                        warehouseDto.getProductId(),
                                        warehouseDto.getCapacity(),
                                        warehouseDto.getLocation()
                                )).toList()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Failed to search").show();
            }
        }
    }
}
