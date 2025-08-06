package lk.ijse.cozy_robes_leyerd.bo.impl;

import lk.ijse.cozy_robes_leyerd.bo.BOFactory;
import lk.ijse.cozy_robes_leyerd.bo.custom.PaymentBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.PaymentDAO;
import lk.ijse.cozy_robes_leyerd.dto.PaymentDTO;
import lk.ijse.cozy_robes_leyerd.entity.Payment;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    private PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public String getNextId() throws SQLException {
       return paymentDAO.getNextId();
    }

    public boolean save(PaymentDTO paymentDTO) throws SQLException {
       return paymentDAO.save(new Payment(paymentDTO.getPaymentId(),paymentDTO.getOrderId(),paymentDTO.getPaymentMethod(),paymentDTO.getTotalAmount()));
    }

    public ArrayList<PaymentDTO> getAll() throws SQLException {
        ArrayList<Payment>paymentList = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDtoArrayList = new ArrayList<>();
        for (Payment payment : paymentList) {
            paymentDtoArrayList.add(new PaymentDTO(payment.getPaymentId(),payment.getOrderId(),payment.getPaymentMethod(),payment.getTotalAmount()));
        }
        return paymentDtoArrayList;
    }


    public boolean  update(PaymentDTO paymentDTO) throws SQLException {
        return paymentDAO.update(new Payment(paymentDTO.getPaymentId(),paymentDTO.getOrderId(),paymentDTO.getPaymentMethod(),paymentDTO.getTotalAmount()));
    }

    public boolean delete(String payment_id) throws SQLException {
       return paymentDAO.delete(payment_id);
    }

    public ArrayList<PaymentDTO> search(String search) throws SQLException {
        ArrayList<Payment>paymentList = paymentDAO.search(search);
        ArrayList<PaymentDTO> dtos = new ArrayList<>();
        for (Payment payment : paymentList) {
            dtos.add(new PaymentDTO(payment.getPaymentId(),payment.getOrderId(),payment.getPaymentMethod(),payment.getTotalAmount()));
        }
        return dtos;
    }

}
