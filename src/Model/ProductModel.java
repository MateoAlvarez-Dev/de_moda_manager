package Model;

import Entity.Product;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel extends MainModel{

    public ArrayList<Product> search(Object object, String[] searchFields, String searchTerm){
        ResultSet result = super.find(object, searchFields, searchTerm);
        ArrayList<Product> products = new ArrayList<>();

        try{

            while (result.next()){
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setProduct_name(result.getString("product_name"));
                product.setPrice(result.getDouble("price"));
                product.setId_shop(result.getInt("id_shop"));
                product.setStock(result.getInt("stock"));
                products.add(product);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while reading the data... " + e.getMessage());
        }

        return products;
    }

    public Product searchById(Object object, int id){
        ResultSet result = super.findById(object, id);
        Product product = null;

        try{

            while (result.next()){
                product = new Product();
                product.setId(result.getInt("id"));
                product.setProduct_name(result.getString("product_name"));
                product.setPrice(result.getDouble("price"));
                product.setId_shop(result.getInt("id_shop"));
                product.setStock(result.getInt("stock"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while reading the data... " + e.getMessage());
        }

        return product;
    }

}