package Model;

import Database.Database;
import Entity.Customer;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel extends MainModel{

    private Database database;

    public CustomerModel(){
        this.database = Database.getInstance();
    }

    public ArrayList<Customer> search(Object object, String[] searchFields, String searchTerm){
        ResultSet result = super.find(object, searchFields, searchTerm);
        ArrayList<Customer> customers = new ArrayList<>();

        try{

            while (result.next()){
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setCustomer_name(result.getString("customer_name"));
                customer.setCustomer_last_name(result.getString("customer_last_name"));
                customer.setEmail(result.getString("email"));
                customers.add(customer);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while reading the data... " + e.getMessage());
        }

        this.database.disconnect();
        return customers;
    }

    public Customer searchById(Object object, int id){
        ResultSet result = super.findById(object, id);
        Customer customer = null;

        try{

            while (result.next()){
                customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setCustomer_name(result.getString("customer_name"));
                customer.setCustomer_last_name(result.getString("customer_last_name"));
                customer.setEmail(result.getString("email"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while reading the data... " + e.getMessage());
        }

        this.database.disconnect();
        return customer;
    }

}