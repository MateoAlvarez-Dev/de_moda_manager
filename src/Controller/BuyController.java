package Controller;

import Entity.Buy;
import Model.BuyModel;
import Model.CustomerModel;
import Model.ProductModel;

import javax.swing.*;
import java.util.ArrayList;

public class BuyController {
    private BuyModel buyModel;

    public BuyController(){
        this.buyModel = new BuyModel();
    }

    public void create(){
        Buy buy = new Buy();

        int amount = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the buy name: "));
        int id_customer = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the buy last name: "));
        int id_product = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the buy email: "));

        buy.setAmount(amount);
        buy.setId_customer(id_customer);
        buy.setId_product(id_product);

        Object result = this.buyModel.create(buy);

        if(result != null){
            buy = (Buy) result;
            JOptionPane.showMessageDialog(null, "Buy added! \n"
                    + "AMOUNT: " + buy.getAmount() + "\n"
                    + "CUSTOMER ID: " + buy.getId_customer() + "\n"
                    + "PRODUCT ID: " + buy.getId_product()
            );
        }else{
            JOptionPane.showMessageDialog(null, "Error, buy not created :(");
        }
    };

    public void update(){
        String buyList = this.getList(this.search(true));

        int id = Integer.parseInt(JOptionPane.showInputDialog(buyList + "Insert the id of the buy to update: "));

        Buy buy = this.buyModel.searchById(new Buy(), id);

        if(buy != null){
            int amount = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the new buy amount: "));
            int id_customer = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the new buy customer id: "));
            int id_product = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the new buy product id: "));

            buy.setAmount(amount);
            buy.setId_customer(id_customer);
            buy.setId_product(id_product);

            boolean result = this.buyModel.update(buy, id);
            if(result) JOptionPane.showMessageDialog(null, "Buy Updated!");
            else JOptionPane.showMessageDialog(null, "Cannot update the buy :(");

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
            buyList += buy.getId() + " - "
                    +buy.getBuy_date() + " - "
                    +buy.getAmount() + " - "
                    +buy.getId_customer() + " - "
                    +buy.getId_product() + " - ";
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