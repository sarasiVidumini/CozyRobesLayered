package lk.ijse.cozy_robes_leyerd.bo.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.OrderDetailsBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.OrderDetailsDAO;
import lk.ijse.cozy_robes_leyerd.dto.OrderDetailsDTO;
import lk.ijse.cozy_robes_leyerd.entity.OrderDetails;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    private OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    public String getNextId() throws SQLException {
      return orderDetailsDAO.getNextId();
    }

    public boolean save(OrderDetailsDTO dtos) throws SQLException {
       return orderDetailsDAO.save(new OrderDetails(dtos.getOrderDetailId(),dtos.getOrderId(),dtos.getProductId(),dtos.getQuantity(),dtos.getPriceAtPurchase()));
    }

    public boolean update(OrderDetailsDTO dtos) throws SQLException {
       return orderDetailsDAO.update(new OrderDetails(dtos.getOrderDetailId(),dtos.getOrderId(),dtos.getProductId(),dtos.getQuantity(),dtos.getPriceAtPurchase()));
    }

    public boolean delete(String orderDetail_id) throws SQLException {
       return orderDetailsDAO.delete(orderDetail_id);
    }

    public ArrayList<OrderDetailsDTO> search(String search) throws SQLException {
        ArrayList<OrderDetails> orderDetailList = orderDetailsDAO.search(search);
        ArrayList<OrderDetailsDTO> dtos = new ArrayList<>();
        for (OrderDetails orderDetail : orderDetailList) {
            dtos.add(new OrderDetailsDTO(orderDetail.getOrderDetailId(), orderDetail.getOrderId(), orderDetail.getProductId(), orderDetail.getQuantity(), orderDetail.getPriceAtPurchase()));
        }
        return dtos;
    }

    public ArrayList<OrderDetailsDTO> getAll() throws  SQLException{
        ArrayList<OrderDetails> orderDetailList = orderDetailsDAO.getAll();
        ArrayList<OrderDetailsDTO> orderDetailsDtoArrayList = new ArrayList<>();
        for (OrderDetails orderDetail : orderDetailList) {
            orderDetailsDtoArrayList.add(new OrderDetailsDTO(orderDetail.getOrderDetailId(),orderDetail.getOrderId(),orderDetail.getProductId(),orderDetail.getQuantity(),orderDetail.getPriceAtPurchase()));
        }
        return orderDetailsDtoArrayList;
    }

    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException {
       return orderDetailsDAO.getAllOrderIds();
    }


    public boolean saveNewOrderDetails(String orderDetailId , String orderId , String productId , int cartQty , double priceAtPurchase) throws SQLException, ClassNotFoundException {
      return orderDetailsDAO.saveNewOrderDetails(orderDetailId, orderId, productId, cartQty, priceAtPurchase);
    }

}
