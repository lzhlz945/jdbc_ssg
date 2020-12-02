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
public class PiLiangInsert {

    @Test
    public void test(){

        Connection con =null;
        PreparedStatement ps =null;
        try {
            con = JdbcUtils.getConnection();
            con.setAutoCommit(false); //关闭自动提交
            String sql="insert into testinsert(name) values(?)";
            Long start=System.currentTimeMillis();
            ps = con.prepareStatement(sql);
            for (int i = 0; i <= 1000000; i++) {
                ps.setObject(1,"name"+i);
                    ps.addBatch();
                if(i % 500==0){
                    ps.executeBatch();
                    ps.clearBatch();
                }

            }
            con.commit();
            Long end=System.currentTimeMillis();
            System.out.println(end-start);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(ps,con);
        }

    }

}
