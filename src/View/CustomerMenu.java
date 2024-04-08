package View;

import Controller.CustomerController;

import javax.swing.*;

public class CustomerMenu {

    private CustomerController customerController;

    public  CustomerMenu(){
        this.customerController = new CustomerController();
    }

    public void render(){
        while(true){
            String option = JOptionPane.showInputDialog("""
                    Welcome to the De Moda manager
                    What you want to do today?
                    
                    1) Create Customer
                    2) Update Customer
                    3) Search Customers
                    4) Delete Customers
                    6) Back
                    
                    """);

            switch (option){
                case "1":
                    this.customerController.create();
                    break;

                case "2":
                    this.customerController.update();
                    break;

                case "3":
                    this.customerController.search(false);
                    break;

                case "4":
                    this.customerController.delete();
                    break;

                case "6":
                    return;

            }
        }
    }

}
