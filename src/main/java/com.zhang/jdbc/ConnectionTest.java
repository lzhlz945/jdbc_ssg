package com.zhang.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.jdbc
 * @date:2020/12/1
 */
public class ConnectionTest {
    @Test
    public void test01() throws SQLException {
        //方式一
        Driver driver = new Driver();
        //String url, Properties info
        String url="jdbc:mysql://localhost:3306/test";
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","888888");

        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }
    @Test
    public void test02() throws Exception {
        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver instance = (Driver)aClass.newInstance();
        String url="jdbc:mysql://localhost:3306/test";
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","888888");

        Connection connect = instance.connect(url, info);
        System.out.println(connect);


    }

    @Test
    public void test03() throws Exception {

        Class<?> driver = Class.forName("com.mysql.jdbc.Driver");
        Driver driver1 = (Driver) driver.newInstance();
        DriverManager.registerDriver(driver1);
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "888888");
        System.out.println(con);


    }

    @Test
    public void test04() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "888888");
        System.out.println(con);


    }
    //最终版1
    @Test
    public void test05() throws Exception {

        try (FileInputStream fis=new FileInputStream("src/jdbc.properties")){
            Properties properties=new Properties();
            properties.load(fis);
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            //1.加载驱动
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    }

