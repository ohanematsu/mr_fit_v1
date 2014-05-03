
package com.example.mr_fit_v1.databaseManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccessor {
    private String jdbcDriver;
    private String databaseName;
    private String user;
    private String password;
    private String databaseUrl;

    private Connection connection = null;

    public DatabaseAccessor(String jdbcDriver, String databaseName, String user, String password, String url) {
        this.jdbcDriver = jdbcDriver;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
        this.databaseUrl = url + databaseName;
        
        if (!loadDriver()) {
            System.out.println("Load JDBC Driver fail!");
        }
    }
     
    public void closeConnection() {
        try {  
            connection.close(); 
        } catch (SQLException e) {
            System.out.println("close connection exeception!");
            e.printStackTrace();  
        }
    }
    
    /* access data from database */
    public String executeRetrieve(String query, String column) {
        if (!connectDatabase()) {
            return null;
        }
        
        /* validate argument */
        if ((query.isEmpty()) || (query == null)) {
            System.out.println("invalid SQL query!");
            return null;
        }
        
        /* validate connection */
        if (connection == null) {
            System.out.println("invalid connection!");
            return null;
        }
        
        /* get data from database */
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet == null) {
                System.out.println("no result!");
                statement.close();
                return null;
            }
            
            String result = "";
            if (resultSet.next()) {
                result = resultSet.getString(column);
                if (result == null) {
                    result = "";
                }
            }
            
            resultSet.close();
            statement.close();
            closeConnection();
            return result;
            
        } catch (SQLException e) {
            System.out.println("get data from database fail!");
            e.printStackTrace();
            return null;
        }
    }
    
    /* insert, update or delete data in database */
    public int executUpdate(String query) {
        if (!connectDatabase()) {
            return -1;
        }
        
        if ((query.isEmpty()) || (query == null)) {
            System.out.println("invalid SQL query!");
            return -1;
        }
        
        if (connection == null) {
            System.out.println("invalid connection!");
            return -1;
        }
        
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(query);
            statement.close();
            closeConnection();
            return result;
        } catch (SQLException e) {
            System.out.println("get data from database fail!");
            e.printStackTrace();
            return -1;
        }
    }
    
    /* load JDBC driver */
    private boolean loadDriver() {
        try {  
            Class.forName(jdbcDriver); 
            return true;
        } catch (Exception e) {
            System.out.println("Load JDBC driver fail!");
            e.printStackTrace();
            return false;
        } 
    }
    
    /* connect to database */
    private boolean connectDatabase() {
        try {  
            connection = DriverManager.getConnection(databaseUrl, user, password);
            return true;
        } catch (Exception e) {
            System.out.println("Connect database fail!");
            e.printStackTrace();
            return false;
        } 
    }
}
