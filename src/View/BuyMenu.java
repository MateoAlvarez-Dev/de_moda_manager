package View;

import Controller.BuyController;

import javax.swing.*;

public class BuyMenu {

    private BuyController buyController;

    public  BuyMenu(){
        this.buyController = new BuyController();
    }

    public void render(){
        while(true){
            String option = JOptionPane.showInputDialog("""
                    Welcome to the De Moda manager
                    What you want to do today?
                    
                    1) Create Buy
                    2) Update Buy
                    3) Search Buys
                    4) Delete Buys
                    6) Back
                    
                    """);

            switch (option){
                case "1":
                    this.buyController.create();
                    break;

                case "2":
                    this.buyController.update();
                    break;

                case "3":
                    this.buyController.search(false);
                    break;

                case "4":
                    this.buyController.delete();
                    break;

                case "6":
                    return;

            }
        }
    }

}