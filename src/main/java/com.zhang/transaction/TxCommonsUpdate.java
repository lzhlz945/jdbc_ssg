package com.zhang.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.transaction
 * @date:2020/12/3
 */
public class TxCommonsUpdate {
    
    public static void txCommonsUpdate(Connection con,String sql, Object ...args)  {
        PreparedStatement ps =null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);

            }
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeJdbc(ps,null);
        }
    }
}
