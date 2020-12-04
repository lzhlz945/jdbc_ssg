package com.zhang.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
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

    //c3p0使用配置文件链接
    private static ComboPooledDataSource cpds=new ComboPooledDataSource("hellpc3p0");

    public static Connection getConnectionByc3p0() throws SQLException {
        Connection connection = cpds.getConnection();
        return connection;
    }
//------------------------------------------------------------------------------------
    //使用druid连接
    private static DataSource duridSource;
    static {
        try {
            FileInputStream fis=new FileInputStream("E:\\jdbc_ssg\\src\\main\\java\\com.zhang\\druid\\druid.properties");
            Properties properties=new Properties();
            properties.load(fis);
            duridSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnectionByDurid() throws SQLException {
        Connection connection = duridSource .getConnection();
        return connection;
    }
//---------------------------------------------------------------------------------
//连接池不能频繁创建放在静态代码块中
private static  DataSource dbcpSource;
    static{
        try {
            FileInputStream fis=new FileInputStream("E:\\jdbc_ssg\\src\\main\\java\\com.zhang\\dbcp\\dbcp.properties");
            Properties properties=new Properties();
            properties.load(fis);
            dbcpSource = BasicDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection test() throws SQLException {

        Connection con = dbcpSource.getConnection();
        return con;

    }

// ---------------------------------------------------------------------------------
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
