package com.zhang.jdbc;

import com.zhang.utils.JdbcUtils;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.jdbc 泛型方法
 * @date:2020/12/2
 *
 * preparedStatement 预编译 and 就是and 编译好了防止sql拼接改变结构
 * 批量插入高效
 *
 *
 */
public class CommonsQuery1 {
    public static  <T> List<T> getCommonsQuery1(String sql, T obj, Object ...args){

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
                Class uClass=obj.getClass();
                Constructor constructor = uClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                obj = (T) constructor.newInstance();//就是new 一个对象
                for (int i = 0; i < columnCount; i++) {
                    Object columnValues = rs.getObject(i + 1);
                    //String columnName = metaData.getColumnName(i + 1);//获取表的列名
                    String columnName =metaData.getColumnLabel(i+1); //获取表的别名；

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


    public static  <T> List<T> getCommonsQuery1(String sql, Class<T> tClass, Object ...args){

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
                T t = tClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValues = rs.getObject(i + 1);
                    //String columnName = metaData.getColumnName(i + 1);//获取表的列名
                    String columnName =metaData.getColumnLabel(i+1); //获取表的别名；

                    Field field = tClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columnValues);
                }
                list.add(t);
            }
            return list;
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
        return null;
    }

    @Test
    public void test02(){
        String sql="select id,name,password,address,phone from user ";
        List<User> uList = getCommonsQuery1(sql, User.class);
        for (User user1 : uList) {
            System.out.println(user1);
        }
    }
}

@Data
class Order1{
    private int orderId;
    private String orderName;
    private Date orderDate;


}
