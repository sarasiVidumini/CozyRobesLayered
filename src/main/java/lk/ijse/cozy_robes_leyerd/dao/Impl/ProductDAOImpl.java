package lk.ijse.cozy_robes_leyerd.dao.Impl;

import javafx.scene.control.Alert;
import lk.ijse.cozy_robes_leyerd.dao.custom.ProductDAO;
import lk.ijse.cozy_robes_leyerd.dto.ProductDTO;
import lk.ijse.cozy_robes_leyerd.entity.Product;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {

    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select product_id from product order by product_id desc limit 1");
        char tableCharacter = 'P';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdNumberString = String.format(tableCharacter+"%03d", nextIdNumber);
            return nextIdNumberString;
        }
        return tableCharacter +"001";
    }

    public boolean save(Product entity) throws SQLException {
        return SQLUtil.execute(
                "insert into product values(?,?,?,?,?)",
                entity.getProductId(),
                entity.getName(),
                entity.getQuantity(),
                entity.getCategory(),
                entity.getUnitPrice()
        );
    }

    public ArrayList<Product> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from product");
        ArrayList<Product> productDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            productDtoArrayList.add(new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getDouble(5)));
        }

        return productDtoArrayList;
    }

        public boolean update(Product entity) throws SQLException {
        return SQLUtil.execute(
                "update product set name = ? , quantity = ? , category = ? , unit_price = ? where product_id = ?",
                entity.getName(),
                entity.getQuantity(),
                entity.getCategory(),
                entity.getUnitPrice(),
                entity.getProductId()
        );
    }

    public boolean delete(String product_id) throws SQLException {
        return SQLUtil.execute(
                "delete from product where product_id = ?" ,
                product_id

        );

    }

    public ArrayList<Product> search(String search) throws SQLException {
        ArrayList<Product> dtos = new ArrayList<>();
        String sql = "select * from product where product_id Like ? OR name LIKE ? OR quantity LIKE ? OR category LIKE ? OR unit_price LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern , pattern, pattern, pattern , pattern);
        while (resultSet.next()) {
            dtos.add(new Product(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getDouble(5)));
        }
        return dtos;
    }


    public ArrayList<String> getAllProductIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select product_id from product");
        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            list.add(id);
        }
        return list;
    }

    public ProductDTO getProductByIds(String productId){
        try {
            ResultSet resultSet = SQLUtil.execute("select * from product where product_id = ?", productId);
            if (resultSet.next()) {
                return new ProductDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load products by Product ID...").show();
        }
        return null;
    }


    public boolean reduceQty(String product_id , int cartQty) throws SQLException {
        try {
            ResultSet resultSet = SQLUtil.execute(
                    "select quantity from product where product_id = ?",
                    product_id
            );
            if (resultSet.next()) {
                int currentQty = resultSet.getInt("quantity");
                if (currentQty >= cartQty) {
                    int newQty = currentQty - cartQty;
                    return SQLUtil.execute(
                            "update product set quantity = ? where product_id=?",
                            newQty,
                            product_id
                    );
                }else {
                    new Alert(Alert.AlertType.ERROR, "Insufficient stock for product id :  " + product_id).show();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error reducing product quantity...").show();
        }
        return false;
    }

}
