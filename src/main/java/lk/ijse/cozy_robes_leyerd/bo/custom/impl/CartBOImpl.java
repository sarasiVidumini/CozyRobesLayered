package lk.ijse.cozy_robes_leyerd.bo.custom.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.CartBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.*;
import lk.ijse.cozy_robes_leyerd.dto.PaymentDTO;
import lk.ijse.cozy_robes_leyerd.dto.ProductDTO;
import lk.ijse.cozy_robes_leyerd.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class CartBOImpl implements CartBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);

    public String getNextOrderId()throws SQLException{
        return ordersDAO.getNextId();
    }

    public String getNextPaymentId()throws SQLException{
        return paymentDAO.getNextId();
    }

    public String getNextOrderDetailsId()throws SQLException{
        return orderDetailsDAO.getNextId();
    }

    public boolean savePayment(PaymentDTO paymentDTO)throws SQLException{
        return paymentDAO.save(new Payment(paymentDTO.getPaymentId(),paymentDTO.getOrderId(),paymentDTO.getPaymentMethod(),paymentDTO.getTotalAmount()));
    }

    public ArrayList<String> getAllProductIds() throws SQLException {
       return productDAO.getAllProductIds();
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException {
        return customerDAO.getAllCustomerIds();
    }

    public ProductDTO getProductByIds(String productId){
        return productDAO.getProductByIds(productId);
    }

    public String getCustomerIdByContact(String contact) throws SQLException {
        return customerDAO.getCustomerIdByContact(contact);
    }

    public String getCustomerNameById(String customerId) throws SQLException {
        return customerDAO.getCustomerNameById(customerId);
    }

    public boolean reduceQty(String product_id , int cartQty) throws SQLException {
        return productDAO.reduceQty(product_id, cartQty);
    }

    public boolean saveNewOrder(String orderId , String customerId , String orderDate , String status , String productId) {
        return ordersDAO.saveNewOrder(orderId, customerId, orderDate, status, productId);
    }

    public boolean saveNewOrderDetails(String orderDetailId , String orderId , String productId , int cartQty , double priceAtPurchase) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.saveNewOrderDetails(orderDetailId,orderId,productId,cartQty,priceAtPurchase);
    }


}
