package lk.ijse.cozy_robes_leyerd.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.cozy_robes_leyerd.bo.custom.ProductBO;
import lk.ijse.cozy_robes_leyerd.dto.CustomerDTO;
import lk.ijse.cozy_robes_leyerd.dto.ProductDTO;
import lk.ijse.cozy_robes_leyerd.viewTm.CustomerTM;
import lk.ijse.cozy_robes_leyerd.viewTm.MaintenanceTM;
import lk.ijse.cozy_robes_leyerd.viewTm.ProductTM;
import lk.ijse.cozy_robes_leyerd.dao.Impl.ProductDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageProductFormController implements Initializable {

    public AnchorPane ancProductPage;
    public Label lblProductId;
    public TextField txtProductName;
    public TextField txtProductQty;
    public TextField txtCategory;
    public TextField txtUnitPrice;

//    private final ProductModel productModel = new ProductModel();

//    private ProductDAOImpl productDAO = new ProductDAOImpl();

    private ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PRODUCT);

    public TableView<ProductTM> tblProduct;
    public TableColumn<ProductTM , String> colProductId;
    public TableColumn<ProductTM , String> colProductName;
    public TableColumn <ProductTM, Integer> colQty;
    public TableColumn <ProductTM, String> colCategory;
    public TableColumn <ProductTM , Double> colUnitPrice;

    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public Button btnReset;
    public TextField txtSearch;


//    public void btnGoDshBoardOnAction(ActionEvent actionEvent) throws IOException {
//        ancProductPage.getChildren().clear();
//        Parent load = FXMLLoader.load(getClass().getResource("/view/DashBoardPage.fxml"));
//        ancProductPage.getChildren().add(load);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        try {
            loadTableData();
            loadNextId();
            resetPage();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    private void loadNextId() throws SQLException {
        String nextId = productBO.getNextId();
        lblProductId.setText(nextId);
    }

    private void loadTableData() throws SQLException {
//        ArrayList<ProductDto> productDtoArrayList = productModel.getAllProduct();
//        ObservableList<ProductDto> productTMS = FXCollections.observableArrayList();
//
//        for (ProductDto productDto : productDtoArrayList) {
//                ProductTM productTM = new ProductTM(
//                        productDto.getProductId(),
//                        productDto.getName(),
//                        productDto.getQuantity(),
//                        productDto.getCategory(),
//                        productDto.getUnitPrice()
//            );
//
//                productTMS.add(productTM);
//        }
//
//        tblProduct.setItems(productTMS);

        tblProduct.setItems(FXCollections.observableArrayList(
                productBO.getAll().stream()
                        .map(productDto -> new ProductTM(
                                productDto.getProductId(),
                                productDto.getName(),
                                productDto.getQuantity(),
                                productDto.getCategory(),
                                productDto.getUnitPrice()
                        )).toList()
        ));
    }


    private void resetPage(){
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtProductName.setText("");
            txtProductQty.setText("");
            txtCategory.setText("");
            txtUnitPrice.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {

            String productId = lblProductId.getText();
            try {
                boolean isDeleted = productBO.delete(productId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Product deleted successfully!").show();
                    loadTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete product.").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to delete product").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String product_id = lblProductId.getText();
        String name = txtProductName.getText();
        int quantity = Integer.parseInt(txtProductQty.getText());
        String category = txtCategory.getText();
        double unit_price = Double.parseDouble(txtUnitPrice.getText());



        try {
            boolean isUpdated = productBO.update(new ProductDTO(product_id, name, quantity, category, unit_price));

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION,"Product updated successfully!").show();
                loadTableData();
                resetPage();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail to update product.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to update product").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String product_id = lblProductId.getText();
        String name = txtProductName.getText();
        int quantity = Integer.parseInt(txtProductQty.getText());
        String category = txtCategory.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());


        try {
            boolean isSaved = productBO.save(new ProductDTO(product_id, name, quantity, category, unitPrice));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION,"Product saved successfully!").show();
                loadTableData();
                resetPage();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fail to save product").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to save product").show();
        }
    }


    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickTable(MouseEvent mouseEvent) {
        ProductTM selectedItem = tblProduct.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblProductId.setText(selectedItem.getProductId());
            txtProductName.setText(selectedItem.getName());
            txtProductQty.setText(String.valueOf(selectedItem.getQuantity()));
            txtCategory.setText(selectedItem.getCategory());
            txtUnitPrice.setText(String.valueOf(selectedItem.getUnitPrice()));


            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void goToDashboard(MouseEvent mouseEvent) throws IOException {
        ancProductPage.getChildren().clear();
        Parent load = FXMLLoader.load(getClass().getResource("/view/DashBoardPage.fxml"));
        ancProductPage.getChildren().setAll(load);
    }

    public void search(KeyEvent keyEvent) {
        String search = txtSearch.getText();
        if (search.isEmpty()){
            try {
                loadTableData();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        }else {
            try {
                ArrayList<ProductDTO> productList  = productBO.search(search);
                tblProduct.setItems(FXCollections.observableArrayList(
                        productList.stream()
                                .map(productDto -> new ProductTM(
                                        productDto.getProductId(),
                                        productDto.getName(),
                                        productDto.getQuantity(),
                                        productDto.getCategory(),
                                        productDto.getUnitPrice()
                                )).toList()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        }
    }

}
