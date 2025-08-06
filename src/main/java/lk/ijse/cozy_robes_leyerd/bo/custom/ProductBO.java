package lk.ijse.cozy_robes_leyerd.bo.custom;

import lk.ijse.cozy_robes_leyerd.bo.SuperBO;
import lk.ijse.cozy_robes_leyerd.dto.ProductDTO;
import lk.ijse.cozy_robes_leyerd.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {

    public String getNextId() throws SQLException;
    public boolean save(ProductDTO productDTO) throws SQLException;
    public ArrayList<ProductDTO> getAll() throws SQLException;
    public boolean update(ProductDTO productDTO) throws SQLException;
    public boolean delete(String product_id) throws SQLException;
    public ArrayList<ProductDTO> search(String search) throws SQLException;
    public ArrayList<String> getAllProductIds() throws SQLException;
    public ProductDTO getProductByIds(String productId);
    public boolean reduceQty(String product_id , int cartQty) throws SQLException;

}
