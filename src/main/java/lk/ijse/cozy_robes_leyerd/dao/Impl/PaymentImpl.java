package lk.ijse.cozy_robes_leyerd.dao.Impl;

import lk.ijse.cozy_robes_leyerd.dto.PaymentDTO;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentImpl {
    public String getNextPaymentId() throws ClassNotFoundException, SQLException {
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

    public boolean savePayment(PaymentDTO paymentDto) throws SQLException {
        return SQLUtil.execute(
                "insert into payment values (?,?,?,?)",
                paymentDto.getPaymentId(),
                paymentDto.getOrderId(),
                paymentDto.getPaymentMethod(),
                paymentDto.getTotalAmount()
        );
    }

    public ArrayList<PaymentDTO> getAllPayment() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from payment");
        ArrayList<PaymentDTO> paymentDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            PaymentDTO paymentDto = new PaymentDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
            paymentDtoArrayList.add(paymentDto);
        }
        return paymentDtoArrayList;
    }


    public boolean  updatePayment(PaymentDTO paymentDto) throws SQLException {
        return SQLUtil.execute(
                "update payment set order_id = ? , payment_method = ? , total_amount = ? where payment_id = ?",
                paymentDto.getOrderId(),
                paymentDto.getPaymentMethod(),
                paymentDto.getTotalAmount(),
                paymentDto.getPaymentId()
        );
    }

    public boolean deletePayment(String payment_id) throws SQLException {
        return SQLUtil.execute(
                "delete from payment where payment_id = ?",
                payment_id
        );
    }

    public ArrayList<PaymentDTO> searchPayment(String search) throws SQLException {
        ArrayList<PaymentDTO> dtos = new ArrayList<>();
        String sql = "select * from payment where payment_id LIKE ? OR order_id LIKE ? OR payment_method LIKE ? OR total_amount LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern,pattern,pattern,pattern);
        while (resultSet.next()) {
            PaymentDTO paymentDto = new PaymentDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
            dtos.add(paymentDto);
        }
        return dtos;
    }

    public ArrayList<String> getAllOrderIds() throws SQLException {
        ArrayList<String> orderIds = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("select order_id from orders");
        while (resultSet.next()) {
            orderIds.add(resultSet.getString("order_id"));
        }
        return orderIds;
    }
}
