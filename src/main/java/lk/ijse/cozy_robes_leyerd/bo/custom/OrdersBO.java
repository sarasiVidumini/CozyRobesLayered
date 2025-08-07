package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(OrdersDTO ordersDTO) throws SQLException;
    public boolean update(OrdersDTO ordersDTO) throws SQLException;
    public boolean delete(String order_id) throws SQLException;
    public ArrayList<OrdersDTO> search(String search) throws SQLException;
    public ArrayList<OrdersDTO> getAll() throws SQLException;
    public ArrayList<String> getAllCustomerIds() throws SQLException;
    public ArrayList<String> getAllProductIds() throws SQLException;
    public boolean saveNewOrder(String orderId , String customerId , String orderDate , String status , String productId);

}
