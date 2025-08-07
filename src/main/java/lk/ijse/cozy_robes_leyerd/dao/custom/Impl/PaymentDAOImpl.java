package lk.ijse.cozy_robes_leyerd.dao.custom.Impl;

import lk.ijse.cozy_robes_leyerd.dao.custom.PaymentDAO;
import lk.ijse.cozy_robes_leyerd.entity.Payment;
import lk.ijse.cozy_robes_leyerd.controller.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select payment_id from payment order by payment_id desc limit 1");
        String tableCharacter = "PA";
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastNumberString  = lastId.substring(tableCharacter.length());
            int lastIdNumber = Integer.parseInt(lastNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextNumberString = String.format(tableCharacter + "%03d" ,nextIdNumber);
            return nextNumberString;
        }
        return tableCharacter + "001";
    }

    public boolean save(Payment entity) throws SQLException {
        return SQLUtil.execute(
                "insert into payment values (?,?,?,?)",
                entity.getPaymentId(),
                entity.getOrderId(),
                entity.getPaymentMethod(),
                entity.getTotalAmount()
        );
    }

    public ArrayList<Payment> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from payment");
            ArrayList<Payment> paymentDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            paymentDtoArrayList.add(new Payment(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4)));
        }
        return paymentDtoArrayList;
    }


    public boolean  update(Payment entity) throws SQLException {
        return SQLUtil.execute(
                "update payment set order_id = ? , payment_method = ? , total_amount = ? where payment_id = ?",
                entity.getOrderId(),
                entity.getPaymentMethod(),
                entity.getTotalAmount(),
                entity.getPaymentId()
        );
    }

    public boolean delete(String payment_id) throws SQLException {
        return SQLUtil.execute(
                "delete from payment where payment_id = ?",
                payment_id
        );
    }

    public ArrayList<Payment> search(String search) throws SQLException {
        ArrayList<Payment> dtos = new ArrayList<>();
        String sql = "select * from payment where payment_id LIKE ? OR order_id LIKE ? OR payment_method LIKE ? OR total_amount LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern,pattern,pattern,pattern);
        while (resultSet.next()) {
           dtos.add(new Payment(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4)));
        }
        return dtos;
    }

}
