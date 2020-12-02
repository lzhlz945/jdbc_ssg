package com.zhang.jdbc;

import com.zhang.utils.JdbcUtils;
import lombok.Data;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
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

    public static List<User> getCommonsQuery(String sql,Object ...args){

        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        List<User> list=new ArrayList<>();
        try {

            con = JdbcUtils.getConnection();
            ps=con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs=ps.executeQuery();
            ResultSetMetaData metaData = ps.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()){
                User user=new User();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValues = rs.getObject(i + 1);
                    String columnName = metaData.getColumnName(i + 1);
                    Class uClass=User.class;
                    Constructor constructor = uClass.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    Field field = uClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(user,columnValues);
                }
                    list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ps,con);
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Test
    public void test02(){
        String sql="select id,name,password,address,phone from user ";
        User user=new User();
        List<User>  list= getCommonsQuery(sql);
        for (User user1 : list) {
            System.out.println(user1);
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
