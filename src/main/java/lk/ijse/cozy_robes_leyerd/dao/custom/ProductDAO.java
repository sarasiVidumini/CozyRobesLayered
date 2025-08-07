package lk.ijse.cozy_robes_leyerd.dao.custom;

import lk.ijse.cozy_robes_leyerd.dao.CrudDAO;
import lk.ijse.cozy_robes_leyerd.dto.ProductDTO;
import lk.ijse.cozy_robes_leyerd.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudDAO<Product> {

    public ArrayList<String> getAllProductIds() throws SQLException;
    public ProductDTO getProductByIds(String productId);
    public boolean reduceQty(String product_id , int cartQty) throws SQLException;

}
