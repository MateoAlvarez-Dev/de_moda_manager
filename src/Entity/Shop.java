package Entity;

public class Shop implements EntityInterface{
    private int id;
    private String shop_name;
    private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStartIndex(){
        return 1;
    }

    @Override
    public String[] getGetters() {
        return new String[]{ "getId", "getShop_name", "getLocation" };
    }
}
