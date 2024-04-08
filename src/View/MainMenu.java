package View;

import Model.ProductModel;

import javax.swing.*;

public class MainMenu {

    private CustomerMenu customerMenu;
    private BuyMenu buyMenu;
    private ProductMenu productMenu;

    public MainMenu(){
        this.customerMenu = new CustomerMenu();
        this.buyMenu = new BuyMenu();
        this.productMenu = new ProductMenu();
    }

    public void render(){
        while(true){
            String option = JOptionPane.showInputDialog("""
                    Welcome to the De Moda manager
                    What you want to do today?
                    
                    1) Customer Manager
                    2) Product Manager
                    3) Buy Manager
                    6) Exit
                    
                    """);

            switch (option){
                case "1":
                    this.customerMenu.render();
                    break;

                case "2":
                    this.productMenu.render();
                    break;

                case "3":
                    this.buyMenu.render();
                    break;

                case "6":
                    return;

            }
        }
    }

}
