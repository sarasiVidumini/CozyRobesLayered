package lk.ijse.cozy_robes_leyerd.bo.custom.impl;

import lk.ijse.cozy_robes_leyerd.bo.custom.ProductBO;
import lk.ijse.cozy_robes_leyerd.dao.DAOFactory;
import lk.ijse.cozy_robes_leyerd.dao.custom.ProductDAO;
import lk.ijse.cozy_robes_leyerd.dto.ProductDTO;
import lk.ijse.cozy_robes_leyerd.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {

    private ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);

    public String getNextId() throws SQLException {
        return productDAO.getNextId();
    }

    public boolean save(ProductDTO productDTO) throws SQLException {
       return productDAO.save(new Product(productDTO.getProductId(),productDTO.getName(),productDTO.getQuantity(),productDTO.getCategory(),productDTO.getUnitPrice()));
    }

    public ArrayList<ProductDTO> getAll() throws SQLException {
        ArrayList<Product>products = productDAO.getAll();
        ArrayList<ProductDTO> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(new ProductDTO(product.getProductId(),product.getName(),product.getQuantity(),product.getCategory(),product.getUnitPrice()));
        }

        return productDtos;
    }

    public boolean update(ProductDTO productDTO) throws SQLException {
       return productDAO.update(new Product(productDTO.getProductId(),productDTO.getName(),productDTO.getQuantity(),productDTO.getCategory(),productDTO.getUnitPrice()));
    }

    public boolean delete(String product_id) throws SQLException {
       return productDAO.delete(product_id);
    }

    public ArrayList<ProductDTO> search(String search) throws SQLException {
        ArrayList<Product>products = productDAO.search(search);
        ArrayList<ProductDTO> dtos = new ArrayList<>();
        for (Product product : products) {
            dtos.add(new ProductDTO(product.getProductId(),product.getName(),product.getQuantity(),product.getCategory(),product.getUnitPrice()));
        }
        return dtos;
    }

    public ArrayList<String> getAllProductIds() throws SQLException {
        return productDAO.getAllProductIds();
    }

    public ProductDTO getProductByIds(String productId){
       return productDAO.getProductByIds(productId);
    }

    public boolean reduceQty(String product_id , int cartQty) throws SQLException {
        return productDAO.reduceQty(product_id,cartQty);
    }


}
