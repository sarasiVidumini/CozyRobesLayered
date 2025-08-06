package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.OrderDetailsDAO;
import lk.ijse.cozy_robes_leyerd.dto.OrderDetailsDTO;
import lk.ijse.cozy_robes_leyerd.entity.OrderDetails;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select orderDetail_id from order_details order by orderDetail_id desc limit 1");
        String tableCharacter = "OD";

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(tableCharacter.length());
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNumber);
            return nextIdString;
        }

        return tableCharacter + "001";
    }

    public boolean save(OrderDetails entity) throws SQLException {
        return SQLUtil.execute(
                "insert into order_details values(?,?,?,?,?)",
                entity.getOrderDetailId(),
                entity.getOrderId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getPriceAtPurchase()
        );
    }

    public boolean update(OrderDetails entity) throws SQLException {
        return SQLUtil.execute(
                "update order_details SET order_id = ? , product_id =? , quantity = ? , price_at_purchase = ?  where orderDetail_id = ?",
                entity.getOrderId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getPriceAtPurchase(),
                entity.getOrderDetailId()
        );
    }

    public boolean delete(String orderDetail_id) throws SQLException {
        return SQLUtil.execute(
                "delete from order_details where orderDetail_id = ?",
                orderDetail_id
        );
    }

    public ArrayList<OrderDetails> search(String search) throws SQLException {
        ArrayList<OrderDetails> dtos = new ArrayList<>();
        String sql = "select * from order_details where orderDetail_id LIKE ? OR order_id LIKE ? OR product_id LIKE ? OR quantity LIKE ? OR price_at_purchase LIKE ? OR update_price LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern,pattern,pattern,pattern,pattern,pattern);
        while (resultSet.next()) {
           dtos.add(new OrderDetails(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getDouble(5)));
        }
        return dtos;
    }

    public ArrayList<OrderDetails> getAll() throws  SQLException{
        ResultSet rst = SQLUtil.execute("SELECT * FROM order_details");
        ArrayList<OrderDetails> orderDetailsDtoArrayList = new ArrayList<>();

        while(rst.next()){
            orderDetailsDtoArrayList.add(new OrderDetails(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getDouble(5) ));
        }
        return orderDetailsDtoArrayList;
    }


    public ArrayList<String> getAllOrderIds() throws SQLException{
        ResultSet rst = SQLUtil.execute("SELECT order_id from orders");
        ArrayList<String> orderIds = new ArrayList<>();

        while(rst.next()){
            orderIds.add(rst.getString("order_id"));
        }
        return orderIds;
    }


    public boolean saveNewOrderDetails(String orderDetailId , String orderId , String productId , int cartQty , double priceAtPurchase)  {
        try {
            return SQLUtil.execute(
                    "insert into order_details(orderDetail_id , order_id , product_id , quantity , price_at_purchase) values (?,?,?,?,?)",
                    orderDetailId,
                    orderId,
                    productId,
                    cartQty,
                    priceAtPurchase
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
