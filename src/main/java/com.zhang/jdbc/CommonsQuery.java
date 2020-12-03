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
    @Test
    public void test03(){
        String sql="select order_id as orderId,order_name as orderName,order_date as orderDate from `order`";
        CommonsQuery<Order> orderCommonsQuery=new CommonsQuery<>();
        Order order=new Order();
        List<Order> query = orderCommonsQuery.getCommonsQuery(sql, order);
        for (Order order1 : query) {
            System.out.println(order1);
        }


    }


}

@Data
class Order{
    private int orderId;
    private String orderName;
    private Date orderDate;


}
