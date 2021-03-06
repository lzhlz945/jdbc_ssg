package com.zhang.jdbc;

import com.zhang.utils.JdbcUtils;
import org.junit.jupiter.api.Test;


import java.sql.Connection;

import java.sql.PreparedStatement;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Insert01 {

    @Test
    public void  insert01()throws Exception{


        Connection connection =JdbcUtils.getConnection();
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
       JdbcUtils.close(ps,connection);

    }
}
