package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.UserDAO;
import lk.ijse.cozy_robes_leyerd.dto.UserDTO;
import lk.ijse.cozy_robes_leyerd.entity.User;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select user_id from user order by user_id desc limit 1");
        char tableCharacter = 'U';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int latIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = latIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNumber);
            return nextIdString;
        }

        return tableCharacter + "001";
    }

    public boolean save(User entity) throws SQLException {
        return SQLUtil.execute(
                "insert into user values(?,?,?,?,?)",
                entity.getUserId(),
                entity.getRole(),
                entity.getName(),
                entity.getContact(),
                entity.getPassword()

        );

    }

    public  boolean update(User entity) throws SQLException {
        return SQLUtil.execute(
                "update user set role=? , name = ? , contact =? , password = ? where user_id = ?",
                entity.getRole(),
                entity.getName(),
                entity.getContact(),
                entity.getPassword(),
                entity.getUserId()

        );
    }

    public boolean delete(String userId) throws SQLException {
        return SQLUtil.execute(
                "delete from user where user_id = ?",
                userId
        );
    }

    public ArrayList<User> search(String search) throws SQLException {
        ArrayList<User> dtos = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE user_id LIKE ? OR name LIKE ? OR contact LIKE ? OR role LIKE ? OR password LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern, pattern, pattern, pattern, pattern);

        while (resultSet.next()) {
          dtos.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
        }
        return dtos;
    }


    public ArrayList<User> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from user");
        ArrayList<User> userDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
           userDtoArrayList.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
        }

        return userDtoArrayList;

    }

}
