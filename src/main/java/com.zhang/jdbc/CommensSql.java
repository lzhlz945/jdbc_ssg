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
public class CommensSql {

    public static void commensSql(String sql,Object ...args){
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con = JdbcUtils.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {

              ps.setObject(i+1,args[i]);
            }
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ps,con);
        }
    }

    @Test
    public void testDelete(){
        String sql="delete from customers where id=?";
        commensSql(sql,20);

    }
    @Test
    public void test01(){

        int sum = sum(10, 10, 20);
        System.out.println(sum);

    }
    public int sum(int ...args){
        int sum=0;
        for (int i = 0; i < args.length; i++) {
            sum+=args[i];
        }
        return sum;
    }
}
