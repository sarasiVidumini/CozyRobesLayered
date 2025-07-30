package lk.ijse.cozy_robes_leyerd.dao.Impl;

import javafx.scene.control.Alert;
import lk.ijse.cozy_robes_leyerd.dto.ProductDTO;
import lk.ijse.cozy_robes_leyerd.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductImpl {
    public String getNextProductId() throws SQLException {
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

    public boolean saveProduct(ProductDTO productDto) throws SQLException {
        return SQLUtil.execute(
                "insert into product values(?,?,?,?,?)",
                productDto.getProductId(),
                productDto.getName(),
                productDto.getQuantity(),
                productDto.getCategory(),
                productDto.getUnitPrice()
        );
    }

    public ArrayList<ProductDTO> getAllProduct() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from product");
        ArrayList<ProductDTO> productDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            ProductDTO productDto = new ProductDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            );
            productDtoArrayList.add(productDto);
        }

        return productDtoArrayList;
    }

    public boolean updateProduct(ProductDTO productDto) throws SQLException {
        System.out.println(productDto);
        return SQLUtil.execute(
                "update product set name = ? , quantity = ? , category = ? , unit_price = ? where product_id = ?",
                productDto.getName(),
                productDto.getQuantity(),
                productDto.getCategory(),
                productDto.getUnitPrice(),
                productDto.getProductId()
        );
    }

    public boolean deleteProduct(String product_id) throws SQLException {
        return SQLUtil.execute(
                "delete from product where product_id = ?" ,
                product_id

        );

    }

    public ArrayList<ProductDTO> searchProduct(String search) throws SQLException {
        ArrayList<ProductDTO> dtos = new ArrayList<>();
        String sql = "select * from product where product_id Like ? OR name LIKE ? OR quantity LIKE ? OR category LIKE ? OR unit_price LIKE ?";
        String pattern = "%" + search + "%";
        ResultSet resultSet = SQLUtil.execute(sql, pattern , pattern, pattern, pattern , pattern);
        while (resultSet.next()) {
            ProductDTO dto = new ProductDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            );
            dtos.add(dto);
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

    public String getProductNameById(String newVal) throws SQLException {
        try {
            ResultSet resultSet = SQLUtil.execute("select name from product where product_id = ?", newVal);
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load product name by ID...").show();
        }

        return null;
    }

    public boolean increaseQty(String productId , int quantity) throws SQLException {
        try {
            ResultSet resultSet = SQLUtil.execute(
                    "select quantity from product where product_id = ?",
                    productId
            );
            if (resultSet.next()) {
                int currentQty = resultSet.getInt("quantity");
                int newQty = currentQty + quantity;
                return SQLUtil.execute(
                        "update product set quantity = ? where product_id = ?",
                        newQty,
                        productId
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error increasing product quantity...").show();
        }
        return false;
    }
}
