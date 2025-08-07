package lk.ijse.cozy_robes_leyerd.dao.custom;

import lk.ijse.cozy_robes_leyerd.dao.CrudDAO;
import lk.ijse.cozy_robes_leyerd.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {
    public ArrayList<String> getAllOrderIds() throws ClassNotFoundException, SQLException;
    public boolean saveNewOrderDetails(String orderDetailId , String orderId , String productId , int cartQty , double priceAtPurchase) throws ClassNotFoundException, SQLException;

}
