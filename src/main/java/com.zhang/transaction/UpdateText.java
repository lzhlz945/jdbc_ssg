package com.zhang.transaction;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static com.zhang.transaction.TxCommonsUpdate.txCommonsUpdate;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.transaction
 * @date:2020/12/3
 */
public class UpdateText {

    @Test
    public void test01(){
        Connection con =null;
        try {
            con = JDBCUtils.getJdbcConnection();
            System.out.println(con.getAutoCommit());
            con.setAutoCommit(false);
            String sq1="UPDATE user_table set balance=balance-100 WHERE `user`=?";
            txCommonsUpdate(con,sq1,"AA");
          System.out.println(10/0);
            String sq2="UPDATE user_table set balance=balance+100 WHERE `user`=?";
            txCommonsUpdate(con,sq2,"BB");

            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JDBCUtils.closeJdbc(null,null,con);
        }


    }
}
