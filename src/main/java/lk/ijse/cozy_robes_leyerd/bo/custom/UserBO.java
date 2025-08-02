package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.UserDTO;
import lk.ijse.cozy_robes_leyerd.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(UserDTO entity) throws SQLException;
    public  boolean update(UserDTO entity) throws SQLException;
    public boolean delete(String userId) throws SQLException;
    public ArrayList<UserDTO> search(String search) throws SQLException;
    public ArrayList<UserDTO> getAll() throws SQLException;

}
