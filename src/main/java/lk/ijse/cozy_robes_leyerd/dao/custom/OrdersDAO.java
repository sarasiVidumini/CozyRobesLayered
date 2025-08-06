package lk.ijse.cozy_robes_leyerd.dao.custom;

import lk.ijse.cozy_robes_leyerd.dao.CrudDAO;
import lk.ijse.cozy_robes_leyerd.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersDAO extends CrudDAO<Orders> {

    public  ArrayList<String> getAllCustomerIds() throws SQLException;
    public ArrayList<String> getAllProductIds() throws SQLException;
    public boolean saveNewOrder(String orderId , String customerId , String orderDate , String status , String productId);


}
