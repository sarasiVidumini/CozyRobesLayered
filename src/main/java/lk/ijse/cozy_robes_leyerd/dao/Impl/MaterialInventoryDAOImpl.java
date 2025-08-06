package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.MaterialInventoryDAO;
import lk.ijse.cozy_robes_leyerd.dto.MaterialInventoryDTO;
import lk.ijse.cozy_robes_leyerd.entity.MaterialInventory;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialInventoryDAOImpl implements MaterialInventoryDAO {
    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select material_id from material_inventory order by material_id desc limit 1");
        char tableCharacter = 'M';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNUmber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNUmber); // "C002"
            return nextIdString;
        }
        return tableCharacter + "001";
    }

    public boolean save(MaterialInventory materialInventoryDto) throws SQLException {
        return SQLUtil.execute(
                "insert into material_inventory values (?,?,?,?)",
                materialInventoryDto.getMaterialId(),
                materialInventoryDto.getSupplierId(),
                materialInventoryDto.getMaterialName(),
                materialInventoryDto.getQuantity()
        );
    }

    public ArrayList<MaterialInventory> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from material_inventory");
        ArrayList<MaterialInventory> materialInventoryDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
           materialInventoryDtoArrayList.add(new MaterialInventory(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
        }
        return materialInventoryDtoArrayList;
    }

    public boolean update(MaterialInventory materialInventoryDto) throws SQLException {
        return SQLUtil.execute(
                "update material_inventory set supplier_id=? , material_name =? , quantity=? where material_id= ?",
                materialInventoryDto.getSupplierId(),
                materialInventoryDto.getMaterialName(),
                materialInventoryDto.getQuantity(),
                materialInventoryDto.getMaterialId()
        );

    }

    public boolean delete(String material_id) throws SQLException {
        return SQLUtil.execute(
                "delete from material_inventory where material_id = ?",
                material_id
        );
    }

    public ArrayList<MaterialInventory> search(String search) throws SQLException {
        ArrayList<MaterialInventory> dtos = new ArrayList<>();
        String sql = "select * from material_inventory where material_id LIKE ? OR supplier_id LIKE ? OR material_name LIKE ? OR quantity LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern , pattern, pattern , pattern);
        while (resultSet.next()) {
           dtos.add(new MaterialInventory(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
        }
        return dtos;
    }

}
