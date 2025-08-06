package lk.ijse.cozy_robes_leyerd.bo.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.OrdersBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.OrdersDAO;
import lk.ijse.cozy_robes_leyerd.dto.OrdersDTO;
import lk.ijse.cozy_robes_leyerd.entity.Orders;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {

    private OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);

    public String getNextId() throws SQLException {
       return ordersDAO.getNextId();
    }

    public boolean save(OrdersDTO ordersDTO) throws SQLException {
       return ordersDAO.save(new Orders(ordersDTO.getOrderId(),ordersDTO.getCustomerId(),ordersDTO.getOrderDate(),ordersDTO.getStatus(),ordersDTO.getProductId()));
    }

    public boolean update(OrdersDTO ordersDTO) throws SQLException {
        return ordersDAO.update(new Orders(ordersDTO.getOrderId(),ordersDTO.getCustomerId(),ordersDTO.getOrderDate(),ordersDTO.getStatus(),ordersDTO.getProductId()));
    }

    public boolean delete(String order_id) throws SQLException {
        return ordersDAO.delete(order_id);
    }

    public ArrayList<OrdersDTO> search(String search) throws SQLException {
        ArrayList<Orders> ordersList = ordersDAO.search(search);
        ArrayList<OrdersDTO> dtos = new ArrayList<>();
        for (Orders orders : ordersList) {
            dtos.add(new OrdersDTO(orders.getOrderId(),orders.getCustomerId(),orders.getOrderDate(),orders.getStatus(),orders.getProductId()));
        }
        return dtos;
    }

    public ArrayList<OrdersDTO> getAll() throws SQLException {
        ArrayList<Orders> ordersList = ordersDAO.getAll();
        ArrayList<OrdersDTO> orderDtoArrayList = new ArrayList<>();
        for (Orders orders : ordersList) {
            orderDtoArrayList.add(new OrdersDTO(orders.getOrderId(),orders.getCustomerId(),orders.getOrderDate(),orders.getStatus(),orders.getProductId()));
        }
        return orderDtoArrayList;
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException{
        return ordersDAO.getAllCustomerIds();
    }

    public ArrayList<String> getAllProductIds() throws SQLException{
        return ordersDAO.getAllProductIds();
    }

    public boolean saveNewOrder(String orderId , String customerId , String orderDate , String status , String productId){
      return ordersDAO.saveNewOrder(orderId, customerId, orderDate, status, productId);
    }



}
