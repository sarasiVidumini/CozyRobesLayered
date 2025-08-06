package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.PaymentDTO;
import lk.ijse.cozy_robes_leyerd.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(PaymentDTO paymentDTO) throws SQLException;
    public ArrayList<PaymentDTO> getAll() throws SQLException;
    public boolean  update(PaymentDTO paymentDTO) throws SQLException;
    public boolean delete(String payment_id) throws SQLException;
    public ArrayList<PaymentDTO> search(String search) throws SQLException;

}
