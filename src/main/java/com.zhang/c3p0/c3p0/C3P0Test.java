package com.zhang.c3p0.c3p0;

import org.junit.jupiter.api.Test;
import com.mchange.v2.c3p0.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.c3p0.c3p0
 * @date:2020/12/4
 */
public class C3P0Test {
    @Test
    public void test01() throws PropertyVetoException, SQLException {

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost/test?useUnicode=true&amp;characterEncoding=utf-8;rewriteBatchedStatements=true" );
        cpds.setUser("root");
        cpds.setPassword("888888");

        //初始化连接池大小
        cpds.setInitialPoolSize(10);

        cpds.setMaxStatements(180);
        Connection con = cpds.getConnection();
        System.out.println(cpds);
        System.out.println(con);
        //关闭连接池
       // DataSources.destroy(cpds);

    }
}
