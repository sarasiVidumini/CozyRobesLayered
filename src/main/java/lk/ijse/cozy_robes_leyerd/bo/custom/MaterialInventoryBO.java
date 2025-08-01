package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.MaterialInventoryDTO;
import lk.ijse.cozy_robes_leyerd.entity.MaterialInventory;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialInventoryBO extends SuperBO {
    public String getNextId() throws SQLException;
    public boolean save(MaterialInventoryDTO materialInventoryDto) throws SQLException;
    public ArrayList<MaterialInventoryDTO> getAll() throws SQLException;
    public boolean update(MaterialInventoryDTO materialInventoryDto) throws SQLException;
    public boolean delete(String material_id) throws SQLException;
    public ArrayList<MaterialInventoryDTO> search(String search) throws SQLException;
}
