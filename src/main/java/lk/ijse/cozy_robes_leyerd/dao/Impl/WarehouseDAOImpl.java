package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.WarehouseDAO;
import lk.ijse.cozy_robes_leyerd.dto.WarehouseDTO;
import lk.ijse.cozy_robes_leyerd.entity.Warehouse;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseDAOImpl implements WarehouseDAO {

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    public boolean save(Warehouse entity) throws SQLException {
        return SQLUtil.execute(
                "insert into warehouse values (?,?,?,?)",
                entity.getSectionId(),
                entity.getProductId(),
                entity.getCapacity(),
                entity.getLocation()
        );
    }

    public ArrayList<Warehouse> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from warehouse");
        ArrayList<Warehouse> warehouseDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
           warehouseDtoArrayList.add(new Warehouse(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4)));
        }
        return warehouseDtoArrayList;
    }


    public boolean  update(Warehouse entity) throws SQLException {
        return SQLUtil.execute(
                "update warehouse set product_id = ? , capacity = ? , location = ? where section_id = ?",
                entity.getProductId(),
                entity.getCapacity(),
                entity.getLocation(),
                entity.getSectionId()
        );
    }

    public boolean delete(String section_id) throws SQLException{
        return SQLUtil.execute(
                "delete from warehouse where section_id = ?",
                section_id
        );
    }

    public ArrayList<Warehouse> search(String search) throws SQLException {
        ArrayList<Warehouse> dtos = new ArrayList<>();
        String sql = "select * from warehouse where section_id LIKE ? OR product_id LIKE ? OR capacity LIKE ? OR location LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern,pattern,pattern,pattern);
        while (resultSet.next()) {
           dtos.add(new Warehouse(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4)));
        }
        return dtos;
    }

}
