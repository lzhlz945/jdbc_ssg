package com.zhang.jdbc;

import com.zhang.utils.JdbcUtils;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.jdbc
 * @date:2020/12/2
 */
public class Query01 {

    @Test
    public void query01(){
        Connection conn=null;
        PreparedStatement ps =null;
        ResultSet rs =null;
        List<User> uList=new ArrayList<>();
        try {
            conn = JdbcUtils.getConnection();
            String sql="select id,name,password,address,phone from user ";
            ps = conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                /*user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAddress(rs.getString(4));
                user.setPhone(rs.getString(5));*/
                uList.add(user);
            }
            for (User user : uList) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ps,conn);
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
@Data
class User{
    private int id;
    private String name;
    private String password;
    private String address;
    private String phone;
}
