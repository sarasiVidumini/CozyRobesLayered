package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dto.UserDTO;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserImpl {
    public String getNextUserId() throws SQLException {
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

    public boolean saveUser(UserDTO userDto) throws SQLException {
        return SQLUtil.execute(
                "insert into user values(?,?,?,?,?)",
                userDto.getUserId(),
                userDto.getRole(),
                userDto.getName(),
                userDto.getContact(),
                userDto.getPassword()

        );

    }

    public  boolean updateUser(UserDTO userDto) throws SQLException {
        return SQLUtil.execute(
                "update user set role=? , name = ? , contact =? , password = ? where user_id = ?",
                userDto.getRole(),
                userDto.getName(),
                userDto.getContact(),
                userDto.getPassword(),
                userDto.getUserId()

        );
    }

    public boolean deleteUser(String userId) throws SQLException {
        return SQLUtil.execute(
                "delete from user where user_id = ?",
                userId
        );
    }

    public ArrayList<UserDTO> searchUser(String search) throws SQLException {
        ArrayList<UserDTO> dtos = new ArrayList<>();
        String sql = "select * from user where user_id LIKE ? OR role LIKE ? OR name LIKE ? OR contact LIKE ? password LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern , pattern , pattern , pattern);
        while (resultSet.next()) {
            UserDTO userDto = new UserDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            dtos.add(userDto);
        }
        return dtos;
    }

    public ArrayList<String> getAllUserIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select user_id from user");
        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            list.add(id);
        }

        return list;

    }
    public ArrayList<UserDTO> getAllUser() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from user");

        ArrayList<UserDTO> userDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            UserDTO userDto = new UserDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );

            userDtoArrayList.add(userDto);
        }

        return userDtoArrayList;

    }
}
