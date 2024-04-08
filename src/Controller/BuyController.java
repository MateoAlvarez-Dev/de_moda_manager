package Controller;

import Entity.Buy;
import Entity.Customer;
import Entity.Product;
import Entity.Shop;
import Model.BuyModel;
import Model.CustomerModel;
import Model.ProductModel;
import Model.ShopModel;

import javax.swing.*;
import java.util.ArrayList;

public class BuyController {
    private BuyModel buyModel;
    private CustomerModel customerModel;
    private CustomerController customerController ;
    private ProductModel productModel;
    private ProductController productController;
    private ShopModel shopModel = new ShopModel();

    public BuyController(){
        this.buyModel = new BuyModel();
        this.customerModel = new CustomerModel();
        this.customerController = new CustomerController();
        this.productModel = new ProductModel();
        this.productController = new ProductController();
    }

    public void create(){
        Buy buy = new Buy();

        String productList = this.productController.getList(this.productController.search(true));
        String customerList = this.customerController.getList(this.customerController.search(true));

        int amount = Integer.parseInt(JOptionPane.showInputDialog(null,  "Insert the product amount: "));
        int id_customer = Integer.parseInt(JOptionPane.showInputDialog(null, customerList + "Insert the customer id: "));
        int id_product = Integer.parseInt(JOptionPane.showInputDialog(null, productList + "Insert the product id: "));

        Product product = this.productModel.searchById(new Product(), id_product);
        boolean isDecreased = this.productModel.decreaseStock(product, amount);

        if(product != null && isDecreased){
            buy.setAmount(amount);
            buy.setId_customer(id_customer);
            buy.setId_product(id_product);

            Object result = this.buyModel.create(buy);

            if(result != null){
                buy = (Buy) result;
                Customer customer = this.customerModel.searchById(new Customer(), id_customer);
                Shop shop = this.shopModel.searchById(new Shop(), product.getId_shop());

                double totalPrice = product.getPrice() * buy.getAmount();
                double iva = (totalPrice * 19) / 100;
                double total = totalPrice + iva;

                JOptionPane.showMessageDialog(null, "=== PRODUCT BILL === \n"
                        + "AMOUNT: " + buy.getAmount() + "\n"
                        + "CUSTOMER: " + customer.getCustomer_name() + " " + customer.getCustomer_last_name() + "\n"
                        + "PRODUCT: " + product.getProduct_name() + "\n"
                        + "SHOP: " + shop.getShop_name() + "\n"
                        + "LOCATION: " + shop.getLocation() + "\n"
                        + "TOTAL PRICE: " + total + " $"
                );
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error, the product could be out of stock :(");
        }
    };

    public void update(){
        String buyList = this.getList(this.search(true));

        String productList = this.productController.getList(this.productController.search(true));
        String customerList = this.customerController.getList(this.customerController.search(true));

        int id = Integer.parseInt(JOptionPane.showInputDialog(buyList + "Insert the id of the buy to update: "));

        Buy buy = this.buyModel.searchById(new Buy(), id);

        if(buy != null){
            int amount = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the new product amount: "));
            int id_customer = Integer.parseInt(JOptionPane.showInputDialog(null, customerList + "Insert the new customer id: "));
            int id_product = Integer.parseInt(JOptionPane.showInputDialog(null, productList + "Insert the new product id: "));

            Product product = this.productModel.searchById(new Product(), id_product);
            boolean isDecreased = this.productModel.decreaseStock(product, amount);

            if(isDecreased){
                buy.setAmount(amount);
                buy.setId_customer(id_customer);
                buy.setId_product(id_product);

                boolean result = this.buyModel.update(buy, id);
                if(result) JOptionPane.showMessageDialog(null, "Buy Updated!");
                else JOptionPane.showMessageDialog(null, "Cannot update the buy :(");
            }else{
                JOptionPane.showMessageDialog(null, "Error, the product could be out of stock :(");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Buy not found");
        }
    }

    public ArrayList<Buy> search(boolean isGettingAll){

        Buy buy = new Buy();

        if(isGettingAll){
            return this.buyModel.search(buy, null, null);
        }

        String searchTerm = JOptionPane.showInputDialog(null, "Insert one search term"
                + "\n -You can let it empty to list all buys available");

        String buyList = this.getList(this.buyModel.search(buy, new String[]{ "id", "buy_date", "amount", "id_customer", "id_customer" }, searchTerm));
        JOptionPane.showMessageDialog(null, buyList);

        return null;
    }

    public String getList(ArrayList<Buy> buys){
        String buyList = "Buy List: \n";
        for(Buy buy : buys){
            Product product = (Product) this.productModel.searchById(new Product(), buy.getId_product());
            Customer customer = (Customer) this.customerModel.searchById(new Customer(), buy.getId_customer());
            buyList += buy.getId() + " - "
                    +buy.getBuy_date() + " - "
                    +buy.getAmount() + " - "
                    +customer.getCustomer_name() + " - "
                    +product.getProduct_name() + "\n";
        }
        return buyList;
    }

    public void delete(){
        String buyList = this.getList(this.search(true));

        int id = Integer.parseInt(JOptionPane.showInputDialog(buyList + "Insert the id of the buy to delete: "));

        Buy buy = this.buyModel.searchById(new Buy(), id);

        if(buy != null){

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete buy " + buy.getId() + "?");

            if(confirm != 0){
                JOptionPane.showMessageDialog(null, "Operation cancelled");
                return;
            }

            boolean result = this.buyModel.delete(buy, id);

            if(result) JOptionPane.showMessageDialog(null, "Buy Deleted!");
            else JOptionPane.showMessageDialog(null, "Cannot Delete the buy :(");

        }else{
            JOptionPane.showMessageDialog(null, "Buy not found");
        }
    }
}