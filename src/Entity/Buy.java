package Entity;

public class Buy implements EntityInterface {
    private int id;
    private String buy_date;
    private int amount;
    private int id_customer;
    private int id_product;

    private String str_customer;
    private String str_product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(String buyDate) {
        this.buy_date = buyDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }


    public String getStr_customer() {
        return str_customer;
    }

    public void setStr_customer(String str_customer) {
        this.str_customer = str_customer;
    }

    public String getStr_product() {
        return str_product;
    }

    public void setStr_product(String str_product) {
        this.str_product = str_product;
    }

    public int getStartIndex(){
        return 2;
    }

    @Override
    public String[] getGetters() {
        return new String[]{ "getId", "getBuy_date", "getAmount", "getId_customer", "getId_product" };
    }
}
