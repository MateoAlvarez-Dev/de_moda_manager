package Model;

import Database.Database;
import Entity.Shop;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopModel extends MainModel{

    private Database database;

    public ShopModel(){
        this.database = Database.getInstance();
    }
    public String getShops(Object object){
        ResultSet result = super.find(object, null, null);
        String shopList = "Shop List:\n";

        try{

            while (result.next()){
                shopList += result.getInt("id") + " - "
                        + result.getString("shop_name") + " - "
                        + result.getString("location") + "\n";
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while reading the data... " + e.getMessage());
        }

        this.database.disconnect();
        return shopList;
    }

    public Shop searchById(Object object, int id){
        ResultSet result = super.findById(object, id);
        Shop shop = null;

        try{

            while (result.next()){
                shop = new Shop();
                shop.setId(result.getInt("id"));
                shop.setShop_name(result.getString("shop_name"));
                shop.setLocation(result.getString("location"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while reading the data... " + e.getMessage());
        }

        this.database.disconnect();
        return shop;
    }
}
