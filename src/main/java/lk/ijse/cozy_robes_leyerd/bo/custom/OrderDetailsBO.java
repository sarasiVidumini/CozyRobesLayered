package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.OrderDetailsDTO;
import lk.ijse.cozy_robes_leyerd.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(OrderDetailsDTO dtos) throws SQLException;
    public boolean update(OrderDetailsDTO dtos) throws SQLException;
    public boolean delete(String orderDetail_id) throws SQLException;
    public ArrayList<OrderDetailsDTO> search(String search) throws SQLException;
    public ArrayList<OrderDetailsDTO> getAll() throws  SQLException;
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException;
    public boolean saveNewOrderDetails(String orderDetailId , String orderId , String productId , int cartQty , double priceAtPurchase) throws SQLException, ClassNotFoundException;

}
