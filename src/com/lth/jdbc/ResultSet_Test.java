/**

* @Description:    ResultSet_Test

* @Author:         Lth

* @CreateDate:     2021/5/12 18:55

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/12 18:55

* @UpdateRemark:

* @Version:        1.8

*/
package com.lth.jdbc;

import javax.activation.MailcapCommandMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ResultSet_Test {
    public static void main(String[] args) throws IOException, SQLException {
        //增加配置文件，让连接更加灵活
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/lth/jdbc/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
//        Class.forName("com.mysql.jdbc.Driver");
        //得到连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //得到statement
        Statement statement = connection.createStatement();
        String sql = "select id, name, address from Users";
        //执行SQL语句，返回ResultSet对象（表）
        ResultSet resultSet = statement.executeQuery(sql);
        //使用while循环取出数据
        while (resultSet.next()){
            Object id = resultSet.getObject(1);
//            int id = resultSet.getInt(1);//获取第一列数据
            String name = resultSet.getString(2);
            String address = resultSet.getString("address");
            System.out.println(id+"\t"+name+"\t"+address+"\t");
        }
        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
