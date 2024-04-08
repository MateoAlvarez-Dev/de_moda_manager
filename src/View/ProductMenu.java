package View;

import Controller.ProductController;

import javax.swing.*;

public class ProductMenu {

    private ProductController productController;

    public  ProductMenu(){
        this.productController = new ProductController();
    }

    public void render(){
        while(true){
            String option = JOptionPane.showInputDialog("""
                    Welcome to the De Moda manager
                    What you want to do today?
                    
                    1) Create Product
                    2) Update Product
                    3) Search Products
                    4) Delete Products
                    6) Back
                    
                    """);

            switch (option){
                case "1":
                    this.productController.create();
                    break;

                case "2":
                    this.productController.update();
                    break;

                case "3":
                    this.productController.search(false);
                    break;

                case "4":
                    this.productController.delete();
                    break;

                case "6":
                    return;

            }
        }
    }

}