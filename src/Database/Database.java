package Database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance;
    private Connection connection;
    private Database(){};

    public Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection(
                    ConfigDB.URL.getValue(),
                    ConfigDB.USERNAME.getValue(),
                    ConfigDB.PASSWORD.getValue()
            );

            return connection;

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ERROR -> " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while connecting... " + e.getMessage());
        }

        return this.connection;
    }

}
