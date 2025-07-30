package lk.ijse.cozy_robes_leyerd.dao.cart.Impl;

import lk.ijse.cozy_robes_leyerd.dto.DeliveryDTO;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl {
    public String getNextDeliveryId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT delivery_id FROM delivery ORDER BY delivery_id DESC LIMIT 1");
        char tableCharacter = 'D'; // Use any character Ex:- customer table for C, item table for I
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C001"
            String lastIdNumberString = lastId.substring(1); // "001"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
            int nextIdNUmber = lastIdNumber + 1; // 2
            // "C002"
            return String.format(tableCharacter + "%03d", nextIdNUmber);
        }
        // No data recode in table so return initial primary key
        return tableCharacter + "001";
    }

    public boolean saveDelivery(DeliveryDTO deliveryDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO delivery VALUES(?,?,?,?)",
                deliveryDto.getDeliveryId(),
                deliveryDto.getOrderId(),
                deliveryDto.getAddress(),
                deliveryDto.getStatus()

        );
    }

    public boolean updateDelivery(DeliveryDTO deliveryDto) throws SQLException {
        return SQLUtil.execute(
                "update delivery set order_id = ? , address = ? , status = ? where delivery_id = ? ",
                deliveryDto.getOrderId(),
                deliveryDto.getAddress(),
                deliveryDto.getStatus(),
                deliveryDto.getDeliveryId()
        );
    }

    public boolean deleteDelivery(String delivery_id) throws SQLException {
        return SQLUtil.execute(
                "delete from delivery where delivery_id = ?",
                delivery_id
        );
    }

    public ArrayList<DeliveryDTO> searchDelivery(String search) throws SQLException {
        ArrayList<DeliveryDTO> dtos = new ArrayList<>();
        String sql = "select * from delivery where delivery_id LIKE ? OR order_id LIKE ? OR address LIKE ? OR status LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern, pattern, pattern, pattern);
        while (resultSet.next()) {
            DeliveryDTO deliveryDto = new DeliveryDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            dtos.add(deliveryDto);
        }
        return dtos;
    }

    public ArrayList<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM delivery");
        ArrayList<DeliveryDTO> deliveryDtos = new ArrayList<>();
        while (resultSet.next()) {
            DeliveryDTO deliveryDto = new DeliveryDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            deliveryDtos.add(deliveryDto);
        }
        return deliveryDtos;
    }
}
