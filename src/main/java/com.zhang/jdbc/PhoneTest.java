package com.zhang.jdbc;

import com.zhang.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.jdbc
 * @date:2020/12/2
 */
public class PhoneTest {

    @Test
    public void test01(){

        Connection con = JdbcUtils.getConnection();
        String sql="insert into customers (id,name,email,birth,photo) values (?,?,?,?,?)";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,50);
            ps.setString(2,"lqin");
            ps.setString(3,"liqin@qq.com");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse("1994-09-07");
            ps.setDate(4,new java.sql.Date(date.getTime()));
            FileInputStream fis=new FileInputStream("timg.jpg");
            ps.setBlob(5,fis);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
        JdbcUtils.close(ps,con);
        }



    }


}
