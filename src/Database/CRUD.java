package Database;

import java.sql.ResultSet;
import java.util.List;

public interface CRUD {

    ResultSet find(Object obj, String[] searchFields, String searchTerm);
    boolean delete(Object obj, int id);
    boolean update(Object obj, int id);
    Object create(Object obj);

}
