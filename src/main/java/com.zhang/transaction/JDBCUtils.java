package com.zhang.transaction;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.transaction
 * @date:2020/12/3
 */
public class JDBCUtils {

    public static Connection getJdbcConnection() throws Exception{

        FileInputStream fis=new FileInputStream("src/jdbc.properties");
        Properties ps=new Properties();
        ps.load(fis);
        String user = ps.getProperty("user");
        String password = ps.getProperty("password");
        String url = ps.getProperty("url");
        String driver = ps.getProperty("driver");
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    public static void closeJdbc(ResultSet rs,Statement ps, Connection conn){

        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs !=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }


}
