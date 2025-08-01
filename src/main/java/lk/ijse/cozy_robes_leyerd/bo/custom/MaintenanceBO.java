package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.MaintenanceDTO;
import lk.ijse.cozy_robes_leyerd.entity.Maintenance;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaintenanceBO extends SuperBO {
    public String getNextId() throws SQLException;
    public boolean save( MaintenanceDTO maintenanceDto) throws SQLException;
    public ArrayList<MaintenanceDTO> getAll() throws SQLException;
    public boolean  update(MaintenanceDTO maintenanceDto) throws SQLException;
    public boolean delete(String maintenance_id) throws SQLException;
    public ArrayList<MaintenanceDTO> search(String search) throws SQLException;
}
