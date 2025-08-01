package lk.ijse.cozy_robes_leyerd.bo.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.MaintenanceBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.MaintenanceDAO;
import lk.ijse.cozy_robes_leyerd.dto.MaintenanceDTO;
import lk.ijse.cozy_robes_leyerd.entity.Maintenance;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaintenanceBOImpl implements MaintenanceBO {

    private MaintenanceDAO maintenanceDAO =(MaintenanceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MAINTENANCE);

    public String getNextId() throws SQLException {
       return maintenanceDAO.getNextId();
    }

    public boolean save( MaintenanceDTO maintenanceDto) throws SQLException {
        return maintenanceDAO.save(new Maintenance(maintenanceDto.getMaintenanceId(),maintenanceDto.getMaterialId(),maintenanceDto.getSectionId(),maintenanceDto.getMaintenanceDate(),maintenanceDto.getMaintenanceStatus(),maintenanceDto.getCost()));
    }

    public ArrayList<MaintenanceDTO> getAll() throws SQLException {
        ArrayList<Maintenance> maintenances = maintenanceDAO.getAll();
        ArrayList<MaintenanceDTO> maintenanceDtos = new ArrayList<>();
        for (Maintenance maintenance : maintenances) {
            maintenanceDtos.add(new MaintenanceDTO(maintenance.getMaintenanceId(),maintenance.getMaterialId(),maintenance.getSectionId(),maintenance.getMaintenanceDate(),maintenance.getMaintenanceStatus(),maintenance.getCost()));
        }
        return maintenanceDtos;
    }

    public boolean  update(MaintenanceDTO maintenanceDto) throws SQLException {
       return maintenanceDAO.update(new Maintenance(maintenanceDto.getMaintenanceId(),maintenanceDto.getMaterialId(),maintenanceDto.getSectionId(),maintenanceDto.getMaintenanceDate(),maintenanceDto.getMaintenanceStatus(),maintenanceDto.getCost()));
    }

    public boolean delete(String maintenance_id) throws SQLException {
        return SQLUtil.execute(
                "delete from maintenance where maintenance_id = ?",
                maintenance_id
        );
    }

    public ArrayList<MaintenanceDTO> search(String search) throws SQLException {
        ArrayList<Maintenance> maintenances = maintenanceDAO.search(search);
        ArrayList<MaintenanceDTO> dtos = new ArrayList<>();
       for (Maintenance maintenance : maintenances) {
           dtos.add(new MaintenanceDTO(maintenance.getMaintenanceId(),maintenance.getMaterialId(),maintenance.getSectionId(),maintenance.getMaintenanceDate(),maintenance.getMaintenanceStatus(),maintenance.getCost()));
       }
        return dtos;
    }

}
