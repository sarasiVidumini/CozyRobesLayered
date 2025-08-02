package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.WarehouseDTO;
import lk.ijse.cozy_robes_leyerd.entity.Warehouse;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WarehouseBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(WarehouseDTO entity) throws SQLException;
    public ArrayList<WarehouseDTO> getAll() throws SQLException;
    public boolean  update(WarehouseDTO entity) throws SQLException;
    public boolean delete(String section_id) throws SQLException;
    public ArrayList<WarehouseDTO> search(String search) throws SQLException;

}
