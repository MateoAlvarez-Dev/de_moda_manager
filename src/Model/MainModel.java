package Model;

import Database.Database;
import Database.CRUD;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainModel implements CRUD{

    private Database database;

    protected MainModel(){
        this.database = Database.getInstance();
    }

    @Override
    public ResultSet find(Object obj, String[] searchFields, String searchTerm){
        Connection conn = this.database.connect();
        ResultSet result = null;

        try{

            String sql = "SELECT " + this.getOnlyFields(obj) + " FROM " + this.getTableName(obj);

            if(searchFields != null){
                sql += " WHERE ";
                for(int i = 0; i < searchFields.length; i++){
                    String searchField = searchFields[i];
                    sql += searchField + " LIKE " + "'%" + searchTerm + "%'";
                    if(i != searchFields.length - 1) sql += " OR ";
                }
            }

            PreparedStatement prepared = conn.prepareStatement(sql);
            prepared.executeQuery();
            result = prepared.getResultSet();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while obtaining the data... " + e.getMessage());
        }

        return result;
    }

    public ResultSet customQuery(String customSQL){
        Connection conn = this.database.connect();
        ResultSet result = null;

        try{

            String sql = customSQL;

            PreparedStatement prepared = conn.prepareStatement(sql);
            prepared.executeQuery();
            result = prepared.getResultSet();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while obtaining the data... " + e.getMessage());
        }

        return result;
    }

    public ResultSet findById(Object obj, int id){
        Connection conn = this.database.connect();
        ResultSet result = null;

        try{

            String sql = "SELECT " + this.getOnlyFields(obj) + " FROM " + this.getTableName(obj) + " WHERE id = ?";

            PreparedStatement prepared = conn.prepareStatement(sql);
            prepared.setInt(1, id);
            prepared.executeQuery();
            result = prepared.getResultSet();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while obtaining the data... " + e.getMessage());
        }

        return result;
    }

    @Override
    public boolean delete(Object obj, int id) {
        Connection conn = this.database.connect();
        boolean result = false;

        try{

            String sql = "DELETE FROM " + this.getTableName(obj) + " WHERE id = ?";
            PreparedStatement prepared = conn.prepareStatement(sql);
            prepared.setInt(1, id);
            int rowsAffected = prepared.executeUpdate();


            if(rowsAffected > 0) result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.database.disconnect();
        return result;
    }

    @Override
    public boolean update(Object obj, int id) {
        Connection connection = this.database.connect();
        boolean result = false;

        try {

            ArrayList<String> fields = invokeGetters(obj);
            String[] fieldNames = fields.get(0).split(",");
            String[] fieldValues = fields.get(1).split(",");
            String setFields = "";

            for(int i = 0; i < fieldNames.length; i++){
                setFields += fieldNames[i] + "=" + fieldValues[i];
                if(i != fieldNames.length - 1){
                    setFields += ",";
                }
            }

            String sql = "UPDATE " + this.getTableName(obj) + " SET " + setFields + " WHERE id = ?";
            PreparedStatement prepared = connection.prepareStatement(sql);
            prepared.setInt(1, id);
            int rowsAffected = prepared.executeUpdate();

            if(rowsAffected > 0) result = true;


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while creating the data... " + e.getMessage());
        }

        this.database.disconnect();
        return result;
    }

    @Override
    public Object create(Object obj) {
        Connection connection = this.database.connect();
        Object result = null;

        try {
            ArrayList<String> fields = invokeGetters(obj);

            String sql = "INSERT INTO " + this.getTableName(obj) + "(" + fields.get(0) + ") VALUES (" + fields.get(1) + ")";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            result = obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error while creating the data... " + e.getMessage());
        }

        this.database.disconnect();
        return result;
    }


    // UTILITIES :)

    private String getOnlyFields(Object obj){
        String fieldNames = "";

        try {

            String[] getterMethods = (String[]) obj.getClass().getMethod("getGetters").invoke(obj);
            for(int i = 0; i < getterMethods.length; i++){
                String fieldName = getterMethods[i].replace("get", "").toLowerCase();
                fieldNames += (i != getterMethods.length - 1) ? fieldName + "," : fieldName;
            }

        } catch (InvocationTargetException | IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, "Error while obtaining data from the Entity");
        } catch (NoSuchMethodException e) {
            JOptionPane.showMessageDialog(null, "The method does not exist");
        }

        return fieldNames;
    }

    private String getTableName(Object obj){
        return obj.getClass().getName().split("\\.")[1].toLowerCase() + "s";
    }

    private ArrayList<String> invokeGetters(Object obj){
        ArrayList<String> results = new ArrayList<>();

        String fieldNames = "";
        String fieldValues = "";

        try{
            String[] getterMethods = (String[]) obj.getClass().getMethod("getGetters").invoke(obj);
            int startIndex = (int) obj.getClass().getMethod("getStartIndex").invoke(obj);

            for(int i = startIndex; i < getterMethods.length; i++){

                String methodName = getterMethods[i];
                Method method = obj.getClass().getMethod(methodName);
                String fieldName = method.getName().replace("get", "").toLowerCase();

                String rawDataType = method.getReturnType().toString();
                String[] dataTypeSplit = rawDataType.split("\\.");
                String dataType = dataTypeSplit[dataTypeSplit.length - 1];

                String invokeResult = "" + method.invoke(obj); // parse the result to String

                fieldNames += fieldName;
                fieldValues += (dataType.equals("String")) ? "'" + invokeResult + "'" : invokeResult;

                if(i != getterMethods.length - 1){
                    fieldNames += ",";
                    fieldValues += ",";
                }

            }

            results.add(fieldNames);
            results.add(fieldValues);

        } catch (InvocationTargetException | IllegalAccessException e) {
            results = null;
            JOptionPane.showMessageDialog(null, "Error while obtaining data from the Entity");
        } catch (NoSuchMethodException e) {
            results = null;
            JOptionPane.showMessageDialog(null, "The method does not exist");
        }

        return results;
    }
}
