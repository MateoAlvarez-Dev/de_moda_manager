package Database;

public enum ConfigDB {
    USERNAME("root"),
    PASSWORD("Rlwl2023."),
    URL("jdbc:mysql://localhost:3306/de_moda");

    private String value;

    ConfigDB(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }


}
