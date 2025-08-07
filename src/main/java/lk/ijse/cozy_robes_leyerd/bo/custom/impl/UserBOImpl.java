package lk.ijse.cozy_robes_leyerd.bo.custom.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.UserBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.UserDAO;
import lk.ijse.cozy_robes_leyerd.dto.UserDTO;
import lk.ijse.cozy_robes_leyerd.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    private UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    public String getNextId() throws SQLException {
       return userDAO.getNextId();
    }

    public boolean save(UserDTO entity) throws SQLException {
        return userDAO.save(new User(entity.getUserId(),entity.getRole(),entity.getName(),entity.getContact(),entity.getPassword()));
    }

    public  boolean update(UserDTO entity) throws SQLException {
        return userDAO.update(new User(entity.getUserId(),entity.getRole(),entity.getName(),entity.getContact(),entity.getPassword()));
    }

    public boolean delete(String userId) throws SQLException {
         return userDAO.delete(userId);
    }

    public ArrayList<UserDTO> search(String search) throws SQLException {
        ArrayList<User> userList = userDAO.search(search);
        ArrayList<UserDTO> dtos = new ArrayList<>();
       for (User user : userList) {
           dtos.add(new UserDTO(user.getUserId(),user.getRole(),user.getName(),user.getContact(),user.getPassword()));
       }
        return dtos;
    }


    public ArrayList<UserDTO> getAll() throws SQLException {
        ArrayList<User> userList = userDAO.getAll();
        ArrayList<UserDTO> userDtoArrayList = new ArrayList<>();
        for (User user : userList) {
         userDtoArrayList.add(new UserDTO(user.getUserId(),user.getRole(),user.getName(),user.getContact(),user.getPassword()));
        }
        return userDtoArrayList;

    }


}