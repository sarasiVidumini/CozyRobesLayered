package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.QuickcheckDAO;
import lk.ijse.cozy_robes_leyerd.dto.QuickcheckDTO;
import lk.ijse.cozy_robes_leyerd.entity.Quickcheck;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuickcheckDAOImpl implements QuickcheckDAO {
    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT check_id FROM quick_check ORDER BY check_id DESC LIMIT 1");
        String tableCharacter = "QC";
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(tableCharacter.length());
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableCharacter + "001";
    }


    public boolean save(Quickcheck quickcheckDto) throws SQLException {
        return SQLUtil.execute(
                "insert into quick_check values (?,?,?,?)",
                quickcheckDto.getCheckId(),
                quickcheckDto.getMaintenanceId(),
                quickcheckDto.getCheckType(),
                quickcheckDto.getStatus()
        );
    }

    public ArrayList<Quickcheck> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from quick_check");
        ArrayList<Quickcheck> quickcheckList = new ArrayList<>();
        while (resultSet.next()) {
           quickcheckList.add(new Quickcheck(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
        }
        return quickcheckList;
    }


    public boolean  update(Quickcheck quickcheckDto) throws SQLException {
        return SQLUtil.execute(
                "update quick_check set maintenance_id = ? , check_type = ? , status = ? where check_id = ?",
                quickcheckDto.getMaintenanceId(),
                quickcheckDto.getCheckType(),
                quickcheckDto.getStatus(),
                quickcheckDto.getCheckId()
        );
    }

    public boolean delete(String check_id) throws SQLException {
        return SQLUtil.execute(
                "delete from quick_check where check_id = ?",
                check_id
        );
    }

    public ArrayList<Quickcheck> search(String search) throws SQLException {
        ArrayList<Quickcheck> dtos = new ArrayList<>();
        String sql = "select * from quick_check where check_id LIKE ? OR maintenance_id LIKE ? OR check_type LIKE ? OR status LIKE ? ";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern,pattern,pattern,pattern);
        while (resultSet.next()) {
            dtos.add(new Quickcheck(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
        }
        return dtos;
    }

}
