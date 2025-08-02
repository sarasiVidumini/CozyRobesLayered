package lk.ijse.cozy_robes_leyerd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DashBoardPageController {

    public AnchorPane ancDashBoardPage;

    public void btnGoCustomerPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Customer.fxml");
    }

    public void btnGoUserViewOnAction(ActionEvent actionEvent)  {
        navigateTo("/view/User.fxml");
    }

    public void btnGoEmployeePageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Employee.fxml");
    }

    public void btnGoOrderPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Orders.fxml");
    }

    public void btnGoSupplierViewOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Supplier.fxml");
    }

    public void btnGoProductPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Product.fxml");
    }

    public void btnGoDeliveryPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Delivery.fxml");
    }

    public void btnGoPaymentPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Payment.fxml");
    }

    public void btnGoOrderDetailsPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/OrderDetails.fxml");
    }

    public void btnGoWarehouseViewOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Warehouse.fxml");
    }

    public void btnGoMaterialInventoryViewOnAction(ActionEvent actionEvent) {
        navigateTo("/view/MaterialInventory.fxml");
    }

    public void btnGoMaintenanceViewOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Maintenance.fxml");
    }

    public void btnGoQuickcheckPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Quickcheck.fxml");
    }

    public void GoToLoginPage(MouseEvent mouseEvent) {
        navigateTo("/view/Login.fxml");
    }

    public void btnGoCartOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Cart.fxml");
    }

    private void navigateTo(String path) {
        try {
            ancDashBoardPage.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancDashBoardPage.widthProperty());
            anchorPane.prefHeightProperty().bind(ancDashBoardPage.heightProperty());

            ancDashBoardPage.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }
}
