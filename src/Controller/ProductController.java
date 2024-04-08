package Controller;

import Entity.Product;
import Model.ProductModel;

import javax.swing.*;
import java.util.ArrayList;

public class ProductController {
    private ProductModel productModel;

    public ProductController(){
        this.productModel = new ProductModel();
    }

    public void create(){
        Product product = new Product();

        String product_name = JOptionPane.showInputDialog(null, "Insert the product name: ");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Insert the product price: "));
        int id_shop = Integer.parseInt(JOptionPane.showInputDialog("Insert the shop ID: "));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Insert the amount of products in stock"));

        product.setProduct_name(product_name);
        product.setPrice(price);
        product.setId_shop(id_shop);
        product.setStock(stock);

        Object result = this.productModel.create(product);

        if(result != null){
            product = (Product) result;
            JOptionPane.showMessageDialog(null, "Product added! \n"
                    + "NAME: " + product.getProduct_name() + "\n"
                    + "PRICE: " + product.getPrice() + " $\n"
                    + "SHOP: " + product.getId_shop() + "\n"
                    + "STOCK: " + product.getStock()
            );
        }else{
            JOptionPane.showMessageDialog(null, "Error, product not created :(");
        }
    };

    public void update(){
        String productList = this.getList(this.search(true));

        int id = Integer.parseInt(JOptionPane.showInputDialog(productList + "Insert the id of the product to update: "));

        Product product = this.productModel.searchById(new Product(), id);

        if(product != null){
            String product_name = JOptionPane.showInputDialog(null, "Insert the new product name: ", product.getProduct_name());
            double price = Double.parseDouble(JOptionPane.showInputDialog("Insert the new product price: ", product.getPrice()));
            int id_shop = Integer.parseInt(JOptionPane.showInputDialog("Insert the new shop ID: ", product.getId_shop()));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Insert the new amount of products in stock", product.getStock()));

            product.setProduct_name(product_name);
            product.setPrice(price);
            product.setId_shop(id_shop);
            product.setStock(stock);

            boolean result = this.productModel.update(product, id);
            if(result) JOptionPane.showMessageDialog(null, "Product Updated!");
            else JOptionPane.showMessageDialog(null, "Cannot update the product :(");

        }else{
            JOptionPane.showMessageDialog(null, "Product not found");
        }
    }

    public ArrayList<Product> search(boolean isGettingAll){

        Product product = new Product();

        if(isGettingAll){
            return this.productModel.search(product, null, null);
        }

        String searchTerm = JOptionPane.showInputDialog(null, "Insert one search term"
                + "\n -You can let it empty to list all products available");

        String productList = this.getList(this.productModel.search(product, new String[]{ "id", "product_name", "price", "id_shop", "stock" }, searchTerm));
        JOptionPane.showMessageDialog(null, productList);

        return null;
    }

    public String getList(ArrayList<Product> products){
        String productList = "Product List: \n";
        for(Product product : products){
            productList += product.getId() + " - "
                    + product.getProduct_name() + " - "
                    + product.getPrice() + "$ - "
                    + product.getId_shop() + " - "
                    + product.getStock() + "\n";
        }
        return productList;
    }

    public void delete(){
        String productList = this.getList(this.search(true));

        int id = Integer.parseInt(JOptionPane.showInputDialog(productList + "Insert the id of the product to delete: "));

        Product product = this.productModel.searchById(new Product(), id);

        if(product != null){

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete product " + product.getProduct_name() + "?");

            if(confirm != 0){
                JOptionPane.showMessageDialog(null, "Operation cancelled");
                return;
            }

            boolean result = this.productModel.delete(product, id);

            if(result) JOptionPane.showMessageDialog(null, "Product Deleted!");
            else JOptionPane.showMessageDialog(null, "Cannot Delete the product :(");

        }else{
            JOptionPane.showMessageDialog(null, "Product not found");
        }
    }
}