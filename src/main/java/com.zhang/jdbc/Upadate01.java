package com.zhang.jdbc;

import com.zhang.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.jdbc
 * @date:2020/12/2
 */
public class Upadate01 {
    @Test
    public void update01() throws SQLException {

        Connection connection = JdbcUtils.getConnection();
        String sql="update customers set name=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1,"ss2s");
        ps.setInt(2,20);
        ps.execute();

        JdbcUtils.close(ps,connection);

    }
}
