package com.zhang.dbcp;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.dbcp
 * @date:2020/12/4
 */
public class DBCPTest {

    //连接池不能频繁创建放在静态代码块中
    private static  DataSource source;
    static{
        try {
            FileInputStream fis=new FileInputStream("E:\\jdbc_ssg\\src\\main\\java\\com.zhang\\dbcp\\dbcp.properties");
            Properties properties=new Properties();
            properties.load(fis);
             source = BasicDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection test() throws SQLException {

            Connection con = source.getConnection();
            return con;

    }
}
