package com.zhang.dbcp;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.dbcp
 * @date:2020/12/4
 */
public class DBCPTest {

    @Test
    public void test(){
        try {
            FileInputStream fis=new FileInputStream("E:\\jdbc_ssg\\src\\main\\java\\com.zhang\\dbcp\\dbcp.properties");
            Properties properties=new Properties();
            properties.load(fis);
            DataSource source = BasicDataSourceFactory.createDataSource(properties);
            Connection con = source.getConnection();
            System.out.println(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
