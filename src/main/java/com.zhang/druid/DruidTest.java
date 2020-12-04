package com.zhang.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.druid
 * @date:2020/12/4
 */
public class DruidTest {

    private static DataSource source;
    static {
        try {
            FileInputStream fis=new FileInputStream("E:\\jdbc_ssg\\src\\main\\java\\com.zhang\\druid\\druid.properties");
            Properties properties=new Properties();
            properties.load(fis);
            source= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Connection test01() throws SQLException {
        Connection connection = source.getConnection();
        return connection;

    }


}
