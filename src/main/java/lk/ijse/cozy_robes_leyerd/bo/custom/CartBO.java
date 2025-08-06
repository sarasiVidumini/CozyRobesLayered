package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.PaymentDTO;
import lk.ijse.cozy_robes_leyerd.dto.ProductDTO;
import lk.ijse.cozy_robes_leyerd.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CartBO extends SuperBO {

    public String getNextOrderId()throws SQLException;
    public String getNextPaymentId()throws SQLException;
    public String getNextOrderDetailsId()throws SQLException;
    public boolean savePayment(PaymentDTO paymentDTO)throws SQLException;
    public ArrayList<String> getAllProductIds() throws SQLException;
    public ArrayList<String> getAllCustomerIds() throws SQLException;
    public ProductDTO getProductByIds(String productId);
    public String getCustomerIdByContact(String contact) throws SQLException;
    public String getCustomerNameById(String customerId) throws SQLException;
    public boolean reduceQty(String product_id , int cartQty) throws SQLException;
    public boolean saveNewOrder(String orderId , String customerId , String orderDate , String status , String productId);
    public boolean saveNewOrderDetails(String orderDetailId , String orderId , String productId , int cartQty , double priceAtPurchase) throws SQLException, ClassNotFoundException;

}
