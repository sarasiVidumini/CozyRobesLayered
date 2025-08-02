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
import lk.ijse.cozy_robes_leyerd.bo.custom.SupplierBO;
import lk.ijse.cozy_robes_leyerd.dto.SupplierDTO;
import lk.ijse.cozy_robes_leyerd.entity.Supplier;
import lk.ijse.cozy_robes_leyerd.viewTm.SupplierTM;
import lk.ijse.cozy_robes_leyerd.dao.Impl.SupplierDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageSupplierFormController implements Initializable {
    public AnchorPane ancSupplierPage;
    public Label lblSupplierId;
    public TextField txtSPName;
    public TextField txtSPAddress;
    public TextField txtSPContact;
    public TableView<SupplierTM> tblSupplier;
    public TableColumn<SupplierTM, String> colSupplierId;
    public TableColumn<SupplierTM, String> colSupplierName;
    public TableColumn<SupplierTM, String> colSupplierAdd;
    public TableColumn<SupplierTM, String> colSupplierCnt;

//    private final SupplierModel supplierModel = new SupplierModel();

//    private final SupplierDAOImpl supplierDAO = new SupplierDAOImpl();

    private SupplierBO supplierBO =(SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSupplierCnt.setCellValueFactory(new PropertyValueFactory<>("contact"));

        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to initialize data").show();

            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    private void loadTableData() throws SQLException {
        var supplierList = supplierBO.getAll();

        if (supplierList != null) {
            tblSupplier.setItems(FXCollections.observableArrayList(
                    supplierList.stream()
                            .map(supplierDto -> new SupplierTM(
                                    supplierDto.getSupplierId(),
                                    supplierDto.getName(),
                                    supplierDto.getAddress(),
                                    supplierDto.getContact()
                            )).toList()
            ));
        }
    }

    private void loadNextId() throws Exception {
        String nextId = supplierBO.getNextId();
        lblSupplierId.setText(nextId);
    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtSPName.clear();
            txtSPAddress.clear();
            txtSPContact.clear();
            tblSupplier.getSelectionModel().clearSelection();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong while resetting").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String supplierId = lblSupplierId.getText();
        String name = txtSPName.getText();
        String address = txtSPAddress.getText();
        String contact = txtSPContact.getText();

        if (supplierId.isEmpty() || name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Empty fields, please fill all the fields.").show();
            return;
        }

        if (!contact.matches("^\\d{10}$")) {
            new Alert(Alert.AlertType.ERROR, "Phone number must be 10 digits").show();
            return;
        }


        try {
            boolean isSaved = supplierBO.save(new SupplierDTO(supplierId,name, address, contact));
            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Successfully saved supplier.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save supplier.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save supplier.").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String supplierId = lblSupplierId.getText();
        String name = txtSPName.getText();
        String address = txtSPAddress.getText();
        String contact = txtSPContact.getText();

        if (supplierId.isEmpty() || name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Empty fields. Please fill all fields.").show();
            return;
        }

        if (!contact.matches("^\\d{10}$")) {
            new Alert(Alert.AlertType.ERROR, "Phone number must be 10 digits").show();
            return;
        }


        try {
            boolean isUpdated = supplierBO.update(new SupplierDTO(supplierId,name, address, contact));
            if (isUpdated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Successfully updated supplier.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update supplier.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update supplier.").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Confirmation");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            String supplierId = lblSupplierId.getText();
            try {
                boolean isDeleted = supplierBO.delete(supplierId);
                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Successfully deleted Supplier").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete Supplier").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete supplier.").show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickedTable(MouseEvent mouseEvent) {
        SupplierTM selectedItem = tblSupplier.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblSupplierId.setText(selectedItem.getSupplierId());
            txtSPName.setText(selectedItem.getName());
            txtSPAddress.setText(selectedItem.getAddress());
            txtSPContact.setText(selectedItem.getContact());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void goToDashboard(MouseEvent mouseEvent) throws IOException {
        ancSupplierPage.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/DashBoardPage.fxml"));
        ancSupplierPage.getChildren().setAll(load);
    }

    public void search(KeyEvent keyEvent) {
        String search = txtSearch.getText();
        if (search.isEmpty()) {
            try {
                loadTableData();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load data").show();
            }
        } else {
            try {
                ArrayList<SupplierDTO> supplierList = supplierBO.search(search);
                tblSupplier.setItems(FXCollections.observableArrayList(
                        supplierList.stream()
                                .map(supplierDto -> new SupplierTM(
                                        supplierDto.getSupplierId(),
                                        supplierDto.getName(),
                                        supplierDto.getAddress(),
                                        supplierDto.getContact()
                                )).toList()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to search").show();
            }
        }
    }

}
