package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.DeliveryDTO;
import lk.ijse.cozy_robes_leyerd.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(DeliveryDTO deliveryDTO) throws SQLException;
    public ArrayList<DeliveryDTO> getAll() throws SQLException;
    public boolean update(DeliveryDTO deliveryDTO) throws SQLException;
    public boolean delete(String deliveryId) throws SQLException;
    public ArrayList<DeliveryDTO> search(String search) throws SQLException;
}
