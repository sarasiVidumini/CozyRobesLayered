package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.OrdersDAO;
import lk.ijse.cozy_robes_leyerd.dto.OrdersDTO;
import lk.ijse.cozy_robes_leyerd.entity.Customer;
import lk.ijse.cozy_robes_leyerd.entity.Orders;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {

    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        char tableCharacter = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            int lastIdNumber = Integer.parseInt(lastId.substring(1));
            return String.format(tableCharacter + "%03d", lastIdNumber + 1);
        }
        return tableCharacter + "001";
    }

    public boolean save(Orders entity) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO orders (order_id, customer_id, order_date, status, product_id) VALUES (?, ?, ?, ?, ?)",
                entity.getOrderId(),
                entity.getCustomerId(),
                entity.getOrderDate(),
                entity.getStatus(),
                entity.getProductId()
        );
    }

    public boolean update(Orders entity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE orders SET customer_id = ?, order_date = ?, status = ?, product_id = ? WHERE order_id = ?",
                entity.getCustomerId(),
                entity.getOrderDate(),
                entity.getStatus(),
                entity.getProductId(),
                entity.getOrderId()
        );
    }

    public boolean delete(String order_id) throws SQLException {
        return SQLUtil.execute(
                "DELETE FROM orders WHERE order_id = ?",
                order_id
        );
    }

    public ArrayList<Orders> search(String search) throws SQLException {
        ArrayList<Orders> dtos = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE order_id LIKE ? OR customer_id LIKE ? OR order_date LIKE ? OR status LIKE ? OR product_id LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern, pattern, pattern, pattern, pattern);
        while (resultSet.next()) {
           dtos.add(new Orders(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
        }
        return dtos;
    }

    public ArrayList<Orders> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orders");
        ArrayList<Orders> orderDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
           orderDtoArrayList.add(new Orders(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
        }
        return orderDtoArrayList;
    }


    public ArrayList<String> getAllCustomerIds() throws SQLException{
        ArrayList<String> customerIds = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM customer");
        while (resultSet.next()) {
            customerIds.add(resultSet.getString("customer_id"));
        }
        return customerIds;
    }

    public ArrayList<String> getAllProductIds() throws SQLException{
        ArrayList<String> productIds = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT product_id FROM product");
        while (resultSet.next()) {
            productIds.add(resultSet.getString("product_id"));
        }
        return productIds;
    }

    public boolean saveNewOrder(String orderId , String customerId , String orderDate , String status , String productId)   {
        try {
            return SQLUtil.execute(
                    "insert into orders(order_id , customer_id , order_date , status , product_id) values (?,?,?,?,?)",
                    orderId,
                    customerId,
                    orderDate,
                    status,
                    productId
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
