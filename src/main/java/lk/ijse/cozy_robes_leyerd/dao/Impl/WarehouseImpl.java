package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dto.WarehouseDTO;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseImpl {
    public boolean saveWarehouse(WarehouseDTO warehouseDto) throws SQLException {
        return SQLUtil.execute(
                "insert into warehouse values (?,?,?,?)",
                warehouseDto.getSectionId(),
                warehouseDto.getProductId(),
                warehouseDto.getCapacity(),
                warehouseDto.getLocation()
        );
    }

    public ArrayList<WarehouseDTO> getAllWarehouses() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from warehouse");
        ArrayList<WarehouseDTO> warehouseDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            WarehouseDTO warehouseDto = new WarehouseDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4)
            );
            warehouseDtoArrayList.add(warehouseDto);
        }

        return warehouseDtoArrayList;
    }


    public boolean  updateWarehouse(WarehouseDTO warehouseDto) throws SQLException {
        return SQLUtil.execute(
                "update warehouse set product_id = ? , capacity = ? , location = ? where section_id = ?",
                warehouseDto.getProductId(),
                warehouseDto.getCapacity(),
                warehouseDto.getLocation(),
                warehouseDto.getSectionId()
        );
    }

    public boolean deleteWarehouse(String section_id) throws SQLException {
        return SQLUtil.execute(
                "delete from warehouse where section_id = ?",
                section_id
        );
    }

    public ArrayList<WarehouseDTO> searchWarehouse(String search) throws SQLException {
        ArrayList<WarehouseDTO> dtos = new ArrayList<>();
        String sql = "select * from warehouse where section_id LIKE ? OR product_id LIKE ? OR capacity LIKE ? OR location LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern,pattern,pattern,pattern);
        while (resultSet.next()) {
            WarehouseDTO dto = new WarehouseDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4)
            );
            dtos.add(dto);
        }
        return dtos;
    }

    public ArrayList<String> getAllSectionIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select section_id from warehouse");
        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            list.add(id);
        }
        return list;
    }
}
