package Controller;

import Entity.Customer;
import Model.CustomerModel;

import javax.swing.*;
import java.util.ArrayList;

public class CustomerController {
    private CustomerModel customerModel;

    public CustomerController(){
        this.customerModel = new CustomerModel();
    }

    public void create(){
        Customer customer = new Customer();

        String name = JOptionPane.showInputDialog(null, "Insert the customer name: ");
        String last_name = JOptionPane.showInputDialog(null, "Insert the customer last name: ");
        String email = JOptionPane.showInputDialog(null, "Insert the customer email: ");

        customer.setCustomer_name(name);
        customer.setCustomer_last_name(last_name);
        customer.setEmail(email);

        Object result = this.customerModel.create(customer);

        if(result != null){
            customer = (Customer) result;
            JOptionPane.showMessageDialog(null, "Customer added! \n"
                    + "NAME: " + customer.getCustomer_name() + "\n"
                    + "LAST NAME: " + customer.getCustomer_last_name() + "\n"
                    + "EMAIL: " + customer.getEmail()
            );
        }else{
            JOptionPane.showMessageDialog(null, "Error, customer not created :(");
        }
    };

    public void update(){
        String customerList = this.getList(this.search(true));

        int id = Integer.parseInt(JOptionPane.showInputDialog(customerList + "Insert the id of the customer to update: "));

        Customer customer = this.customerModel.searchById(new Customer(), id);

        if(customer != null){
            String name = JOptionPane.showInputDialog(null, "Insert the new customer name: ", customer.getCustomer_name());
            String last_name = JOptionPane.showInputDialog(null, "Insert the new customer last name: ", customer.getCustomer_last_name());
            String email = JOptionPane.showInputDialog(null, "Insert the new customer email: ", customer.getEmail());

            customer.setCustomer_name(name);
            customer.setCustomer_last_name(last_name);
            customer.setEmail(email);

            boolean result = this.customerModel.update(customer, id);
            if(result) JOptionPane.showMessageDialog(null, "Customer Updated!");
            else JOptionPane.showMessageDialog(null, "Cannot update the customer :(");

        }else{
            JOptionPane.showMessageDialog(null, "Customer not found");
        }
    }

    public ArrayList<Customer> search(boolean isGettingAll){

        Customer customer = new Customer();

        if(isGettingAll){
            return this.customerModel.search(customer, null, null);
        }

        String searchTerm = JOptionPane.showInputDialog(null, "Insert one search term"
                + "\n -You can let it empty to list all customers available");

        String customerList = this.getList(this.customerModel.search(customer, new String[]{ "id", "customer_name", "customer_last_name", "email" }, searchTerm));
        JOptionPane.showMessageDialog(null, customerList);

        return null;
    }

    public String getList(ArrayList<Customer> customers){
        String customerList = "Customer List: \n";
        for(Customer customer : customers){
            customerList += customer.getId() + " - "
                    + customer.getCustomer_name() + " - "
                    + customer.getCustomer_last_name() + " - "
                    + customer.getEmail() + "\n";
        }
        return customerList;
    }

    public void delete(){
        String customerList = this.getList(this.search(true));

        int id = Integer.parseInt(JOptionPane.showInputDialog(customerList + "Insert the id of the customer to delete: "));

        Customer customer = this.customerModel.searchById(new Customer(), id);

        if(customer != null){

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete customer " + customer.getCustomer_name() + "?");

            if(confirm != 0){
                JOptionPane.showMessageDialog(null, "Operation cancelled");
                return;
            }

            boolean result = this.customerModel.delete(customer, id);

            if(result) JOptionPane.showMessageDialog(null, "Customer Deleted!");
            else JOptionPane.showMessageDialog(null, "Cannot Delete the customer :(");

        }else{
            JOptionPane.showMessageDialog(null, "Customer not found");
        }
    }
}
