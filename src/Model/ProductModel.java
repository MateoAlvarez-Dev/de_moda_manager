package Model;

import Database.Database;
import Entity.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel extends MainModel{

    private Database database;

    public ProductModel(){
        this.database = Database.getInstance();
    }

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

        this.database.disconnect();
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

        this.database.disconnect();
        return product;
    }

    public boolean decreaseStock(Product product, int amount){
        if((product.getStock() - amount) < 0) return false;
        Connection connection = this.database.connect();
        boolean result = false;

        try{

            String sql = "UPDATE products SET stock = ? WHERE id = ?";
            PreparedStatement prepared = connection.prepareStatement(sql);
            prepared.setInt(2, product.getId());
            prepared.setInt(1, product.getStock() - amount);

            int rowsAffected = prepared.executeUpdate();
            if(rowsAffected > 0){
                result = true;
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot update the stock :(");
        }

        this.database.disconnect();
        return result;
    }

}