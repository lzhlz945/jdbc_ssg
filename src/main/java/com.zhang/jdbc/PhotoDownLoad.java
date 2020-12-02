package com.zhang.jdbc;

import com.zhang.utils.JdbcUtils;
import lombok.Data;
import org.junit.jupiter.api.Test;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * @author: create by zhl
 * @version: v1.0
 * @description: com.zhang.jdbc
 * @date:2020/12/2
 */
public class PhotoDownLoad {

    @Test
    public void test(){
        try {
            Connection connection = JdbcUtils.getConnection();
            String sql="select id,name,email,birth,photo from customers where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,50);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Customers customers = new Customers();
                customers.setId(rs.getInt("id"));
                customers.setName(rs.getString("name"));
                customers.setEmail(rs.getString("email"));
                customers.setBirth(rs.getDate(4));

                Blob photo = rs.getBlob("photo");
                InputStream fis = photo.getBinaryStream();
                FileOutputStream fos=new FileOutputStream("liqin.jpg");
                byte[] bytes=new byte[1024*4];
                int len;
                while ((len=fis.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
@Data
class Customers{
    private int id;
    private String name;
    private String email;
    private Date birth;
}
