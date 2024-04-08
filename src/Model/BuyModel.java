package Model;

import Database.Database;
import Entity.Buy;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuyModel extends MainModel{

    private Database database;

    public BuyModel(){
        this.database = Database.getInstance();
    }

    public ArrayList<Buy> search(Object object, String[] searchFields, String searchTerm){
        ResultSet result = super.find(object, searchFields, searchTerm);
        ArrayList<Buy> buys = new ArrayList<>();

        try{

            while (result.next()){
                Buy buy = new Buy();
                buy.setId(result.getInt("id"));
                buy.setBuy_date(result.getString("buy_date"));
                buy.setAmount(result.getInt("amount"));
                buy.setId_customer(result.getInt("id_customer"));
                buy.setId_product(result.getInt("id_product"));
                buys.add(buy);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while reading the data... " + e.getMessage());
        }

        this.database.disconnect();
        return buys;
    }

    public ArrayList<Buy> searchByProduct(int product_id){
        ResultSet result = super.customQuery("SELECT b.id, b.buy_date, b.amount, c.customer_name as 'customer', p.product_name as 'product' FROM buys b "
                + "INNER JOIN products p ON p.id = b.id_product "
                + "INNER JOIN customers c ON c.id = b.id_customer "
                + "WHERE b.id_product = " + product_id
        );
        ArrayList<Buy> buys = new ArrayList<>();

        try{

            while (result.next()){
                Buy buy = new Buy();
                buy.setId(result.getInt("id"));
                buy.setBuy_date(result.getString("buy_date"));
                buy.setAmount(result.getInt("amount"));
                buy.setId_customer(result.getInt("id_customer"));
                buy.setId_product(result.getInt("id_product"));
                buy.setStr_customer(result.getString("customer"));
                buy.setStr_product(result.getString("product"));
                buys.add(buy);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while reading the data... " + e.getMessage());
        }

        this.database.disconnect();
        return buys;
    }

    public Buy searchById(Object object, int id){
        ResultSet result = super.findById(object, id);
        Buy buy = null;

        try{

            while (result.next()){
                buy = new Buy();
                buy.setId(result.getInt("id"));
                buy.setBuy_date(result.getString("buy_date"));
                buy.setAmount(result.getInt("amount"));
                buy.setId_customer(result.getInt("id_customer"));
                buy.setId_product(result.getInt("id_product"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while reading the data... " + e.getMessage());
        }

        return buy;
    }

}
