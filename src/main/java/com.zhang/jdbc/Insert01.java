package com.zhang.jdbc;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Insert01 {

    @Test
    public void  insert01()throws Exception{

        FileInputStream fis=new FileInputStream("src/jdbc.properties");
        Properties properties=new Properties();
        properties.load(fis);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        String sql="insert into customers(`name`,email,birth) values (?,?,?)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,"sss");
        ps.setString(2,"zhang@qq.com");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("1993-09-04");
        ps.setDate(3,new java.sql.Date(date.getTime()));

        ps.execute();
        ps.close();
        connection.close();
        fis.close();

    }
}
