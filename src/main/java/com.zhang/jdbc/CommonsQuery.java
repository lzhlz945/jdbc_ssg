package com.zhang.jdbc;

import com.zhang.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

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
public class CommonsQuery <T>{
    public  List<T> getCommonsQuery(String sql, T obj, Object ...args){

        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<T> list=new ArrayList<>();
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
                obj= (T) new User();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValues = rs.getObject(i + 1);
                    String columnName = metaData.getColumnName(i + 1);
                    Class uClass=obj.getClass();
                    Field field = uClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj,columnValues);
                }
                list.add(obj);
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
        CommonsQuery<User> userCommonsQuery = new CommonsQuery<User>();
        User user=new User();
        List<User> uList = userCommonsQuery.getCommonsQuery(sql, user);


        for (User user1 : uList) {
            System.out.println(user1);
        }



    }
}
