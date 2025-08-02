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
import lk.ijse.cozy_robes_leyerd.bo.custom.CustomerBO;
import lk.ijse.cozy_robes_leyerd.dto.CustomerDTO;
import lk.ijse.cozy_robes_leyerd.entity.Customer;
import lk.ijse.cozy_robes_leyerd.viewTm.CustomerTM;
import lk.ijse.cozy_robes_leyerd.dao.Impl.CustomerDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ManageCustomerFormController implements Initializable {

    public Label lblCustomerId;
    public TextField txtCustName;
    public TextField txtCustPhone;
    public TextField txtCustEmail;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn<CustomerTM, String> colId;
    public TableColumn<CustomerTM, String> colName;
    public TableColumn<CustomerTM, String> colPhone;
    public TableColumn<CustomerTM, String> colMail;
    public AnchorPane ancCustomerPage;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public Button btnReset;
    public TextField txtSearch;

//   private final CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    private CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            loadTableData();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to initialize data!").show();
        }

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void loadTableData() throws Exception {
        List<CustomerDTO> customers = customerBO.getAll();

        if (customers != null) {
            List<CustomerTM> tmList = customers.stream()
                    .map(c -> new CustomerTM(c.getCustomerId(), c.getName(), c.getPhone(), c.getEmail()))
                    .collect(Collectors.toList());

            tblCustomer.setItems(FXCollections.observableArrayList(tmList));
        }
    }

    private void loadNextId() throws Exception {
        String nextId = customerBO.getNextId();
        lblCustomerId.setText(nextId);
    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtCustName.clear();
            txtCustPhone.clear();
            txtCustEmail.clear();
            tblCustomer.getSelectionModel().clearSelection();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong while resetting!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();
        String name = txtCustName.getText();
        String phone = txtCustPhone.getText();
        String email = txtCustEmail.getText();

        if (customerId.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Empty fields, please fill all the fields").show();
            return;
        }

        if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid email address").show();
            return;
        }

        if (!phone.matches("^\\d{10}$")) {
            new Alert(Alert.AlertType.ERROR, "Phone number must be 10 digits").show();
            return;
        }



        try {
            boolean isSaved = customerBO.save(new CustomerDTO(customerId,name,phone,email));
            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Saved successfully!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save customer.").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");

        Optional<ButtonType> response = alert.showAndWait();
        if (response.isPresent() && response.get() == ButtonType.YES) {
            String customerId = lblCustomerId.getText();
            try {
                boolean isDeleted = customerBO.delete(customerId);
                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Deleted successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete customer").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete customer").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();
        String name = txtCustName.getText();
        String phone = txtCustPhone.getText();
        String email = txtCustEmail.getText();

        if (customerId.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Empty fields, please fill all the fields").show();
            return;
        }

        if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid email address").show();
            return;
        }

        if (!phone.matches("^\\d{10}$")) {
            new Alert(Alert.AlertType.ERROR, "Phone number must be 10 digits").show();
            return;
        }


        try {
            boolean isUpdated = customerBO.update(new CustomerDTO(customerId, name, phone, email));
            if (isUpdated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update customer").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update customer").show();
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickedTable(MouseEvent mouseEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblCustomerId.setText(selectedItem.getCustomerId());
            txtCustName.setText(selectedItem.getName());
            txtCustPhone.setText(selectedItem.getPhone());
            txtCustEmail.setText(selectedItem.getEmail());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    public void goToDashboard(MouseEvent mouseEvent) throws IOException {
        ancCustomerPage.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/DashBoardPage.fxml"));
        ancCustomerPage.getChildren().add(load);
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
        } else {
            try {
                List<CustomerDTO> customerList = customerBO.search(search);

                List<CustomerTM> tmList = customerList.stream()
                        .map(dto -> new CustomerTM(dto.getCustomerId(), dto.getName(), dto.getPhone(), dto.getEmail()))
                        .collect(Collectors.toList());
                tblCustomer.setItems(FXCollections.observableArrayList(tmList));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to search").show();
            }
        }
    }
}
