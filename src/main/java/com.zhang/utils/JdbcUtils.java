package com.zhang.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {

   public static Connection getConnection(){

       Connection connection=null;
       try {
           FileInputStream fis=new FileInputStream("src/jdbc.properties");
           Properties properties=new Properties();
           properties.load(fis);
           String user = properties.getProperty("user");
           String password = properties.getProperty("password");
           String url = properties.getProperty("url");
           String driver = properties.getProperty("driver");

           Class.forName(driver);

           connection = DriverManager.getConnection(url, user, password);

       } catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }


       return connection;
   }


    public static void close(Statement statement,Connection connection){


       if(statement != null){
           try {
               statement.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       if(connection !=null){
           try {
               connection.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }


   }

}
