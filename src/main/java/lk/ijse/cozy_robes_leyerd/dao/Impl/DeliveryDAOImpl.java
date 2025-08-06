package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.DeliveryDAO;
import lk.ijse.cozy_robes_leyerd.dto.DeliveryDTO;
import lk.ijse.cozy_robes_leyerd.entity.Delivery;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl implements DeliveryDAO {
    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT delivery_id FROM delivery ORDER BY delivery_id DESC LIMIT 1");
        char tableCharacter = 'D';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C001"
            String lastIdNumberString = lastId.substring(1); // "001"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
            int nextIdNUmber = lastIdNumber + 1; // 2
            // "C002"
            return String.format(tableCharacter + "%03d", nextIdNUmber);
        }

        return tableCharacter + "001";
    }

    public boolean save(Delivery deliveryDto) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO delivery VALUES(?,?,?,?)",
                deliveryDto.getDeliveryId(),
                deliveryDto.getOrderId(),
                deliveryDto.getAddress(),
                deliveryDto.getStatus()

        );
    }

    public boolean update(Delivery deliveryDto) throws SQLException {
        return SQLUtil.execute(
                "update delivery set order_id = ? , address = ? , status = ? where delivery_id = ? ",
                deliveryDto.getOrderId(),
                deliveryDto.getAddress(),
                deliveryDto.getStatus(),
                deliveryDto.getDeliveryId()
        );
    }

    public boolean delete(String delivery_id) throws SQLException {
        return SQLUtil.execute(
                "delete from delivery where delivery_id = ?",
                delivery_id
        );
    }

    public ArrayList<Delivery> search(String search) throws SQLException {
        ArrayList<Delivery> dtos = new ArrayList<>();
        String sql = "select * from delivery where delivery_id LIKE ? OR order_id LIKE ? OR address LIKE ? OR status LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern, pattern, pattern, pattern);
        while (resultSet.next()) {
           dtos.add(new Delivery(resultSet.getString("delivery_id"), resultSet.getString("order_id"), resultSet.getString("address"), resultSet.getString("status")));
        }
        return dtos;
    }

    public ArrayList<Delivery> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from delivery");
        ArrayList<Delivery> delivery = new ArrayList<>();
        while (resultSet.next()) {
            delivery.add(new Delivery(resultSet.getString("delivery_id"), resultSet.getString("order_id"), resultSet.getString("address"), resultSet.getString("status")));
        }
        return delivery;
    }


}
