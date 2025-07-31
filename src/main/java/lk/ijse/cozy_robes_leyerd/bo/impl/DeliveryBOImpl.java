package lk.ijse.cozy_robes_leyerd.bo.impl;


import lk.ijse.cozy_robes_leyerd.bo.custom.DeliveryBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.Impl.DeliveryDAOImpl;
import lk.ijse.cozy_robes_leyerd.dao.custom.DeliveryDAO;
import lk.ijse.cozy_robes_leyerd.dto.CustomerDTO;
import lk.ijse.cozy_robes_leyerd.dto.DeliveryDTO;
import lk.ijse.cozy_robes_leyerd.entity.Customer;
import lk.ijse.cozy_robes_leyerd.entity.Delivery;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryBOImpl implements DeliveryBO {

    private DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DELIVERY);

    @Override
    public String getNextId() throws SQLException {
       return deliveryDAO.getNextId();

    }

    @Override
    public boolean save(DeliveryDTO deliveryDto) throws SQLException {
       return deliveryDAO.save(new Delivery(deliveryDto.getDeliveryId(),deliveryDto.getOrderId(),deliveryDto.getAddress(),deliveryDto.getStatus()));
    }

    @Override
    public boolean update(DeliveryDTO deliveryDto) throws SQLException {
       return deliveryDAO.update(new Delivery(deliveryDto.getDeliveryId(),deliveryDto.getOrderId(),deliveryDto.getAddress(),deliveryDto.getStatus()));
    }

    @Override
    public boolean delete(String deliveryId) throws SQLException {
       return deliveryDAO.delete(deliveryId);
    }

    @Override
    public ArrayList<DeliveryDTO> search(String search) throws SQLException {
        ArrayList<Delivery> deliveries = deliveryDAO.search(search);
        ArrayList<DeliveryDTO> dtos = new ArrayList<>();
        for (Delivery d : deliveries) {
            dtos.add(new DeliveryDTO(d.getDeliveryId(), d.getOrderId(), d.getAddress(), d.getStatus()));
        }
        return dtos;
    }

    @Override
    public ArrayList<DeliveryDTO> getAll() throws SQLException {
        ArrayList<Delivery> deliveries = deliveryDAO.getAll();
        ArrayList<DeliveryDTO> deliveryDTOS = new ArrayList<>();
        for (Delivery d : deliveries) {
            deliveryDTOS.add(new DeliveryDTO(d.getDeliveryId(), d.getOrderId(), d.getAddress(), d.getStatus()));
        }
        return deliveryDTOS;
    }

}
