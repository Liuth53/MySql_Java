/**

* @Description:    Connect And Release SQL Source

* @Author:         Lth

* @CreateDate:     2021/5/19 12:29

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/19 12:29

* @UpdateRemark:

* @Version:        1.8

*/
package com.lth.jdbcUtils;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class JDBCUtils {
    //属性：URL，User，Password，Driver
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\mysql.properties2"));

            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");

        } catch (IOException e) {
            //将编译异常转成运行异常，调用者可以捕获异常，也可选择默认处理
            throw  new RuntimeException(e);
//            e.printStackTrace();
        }
    }
    //连接数据库，返回Connection
    public static Connection getConnection(){

        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //关闭资源
    public static void close(ResultSet set, Statement statement,Connection connection){
        //判断是否为null
        try {
            if (set != null){
                set.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
