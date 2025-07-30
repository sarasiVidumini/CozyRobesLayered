package lk.ijse.cozy_robes_leyerd.dao.cart.Impl;

import lk.ijse.cozy_robes_leyerd.dto.OrdersDTO;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersImpl {
    public String getNextOrderId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        char tableCharacter = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            int lastIdNumber = Integer.parseInt(lastId.substring(1));
            return String.format(tableCharacter + "%03d", lastIdNumber + 1);
        }
        return tableCharacter + "001";
    }

    public boolean saveOrder(OrdersDTO orderDto) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO orders (order_id, customer_id, order_date, status, product_id) VALUES (?, ?, ?, ?, ?)",
                orderDto.getOrderId(),
                orderDto.getCustomerId(),
                orderDto.getOrderDate(),
                orderDto.getStatus(),
                orderDto.getProductId()
        );
    }

    public boolean updateOrder(OrdersDTO orderDto) throws SQLException {
        return SQLUtil.execute(
                "UPDATE orders SET customer_id = ?, order_date = ?, status = ?, product_id = ? WHERE order_id = ?",
                orderDto.getCustomerId(),
                orderDto.getOrderDate(),
                orderDto.getStatus(),
                orderDto.getProductId(),
                orderDto.getOrderId()
        );
    }

    public boolean deleteOrder(String order_id) throws SQLException {
        return SQLUtil.execute(
                "DELETE FROM orders WHERE order_id = ?",
                order_id
        );
    }

    public ArrayList<OrdersDTO> searchOrder(String search) throws SQLException {
        ArrayList<OrdersDTO> dtos = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE order_id LIKE ? OR customer_id LIKE ? OR order_date LIKE ? OR status LIKE ? OR product_id LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern, pattern, pattern, pattern, pattern);
        while (resultSet.next()) {
            OrdersDTO orderDto = new OrdersDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            dtos.add(orderDto);
        }
        return dtos;
    }

    public ArrayList<OrdersDTO> getAllOrder() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orders");
        ArrayList<OrdersDTO> orderDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            OrdersDTO orderDto = new OrdersDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            orderDtoArrayList.add(orderDto);
        }
        return orderDtoArrayList;
    }

    public String getCustomerNameById(String customerId)  {
        try {
            ResultSet resultSet = SQLUtil.execute("SELECT name from customer WHERE customer_id = ?", customerId);
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
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
