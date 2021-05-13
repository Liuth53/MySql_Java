/**

* @Description:    5 ways create connection

* @Author:         Lth

* @CreateDate:     2021/5/12 16:25

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/12 16:25

* @UpdateRemark:   

* @Version:        1.8

*/
package com.lth.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnect {

    @Test
    public void connect01() throws SQLException {
        Driver driver = new Driver();
        //url = "jdbc:mysql://IP地址:端口/表名";
        String url = "jdbc:mysql://localhost:3306/db01";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","lth");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    @Test
    public void connect02() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //使用反射加载Driver类
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        //url = "jdbc:mysql://IP地址:端口/表名";
        String url = "jdbc:mysql://localhost:3306/db01";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","lth");
        Connection connect = driver.connect(url, properties);
        System.out.println("Way2="+connect);
    }

    @Test
    public void connect03() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //使用反射加载Driver类
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        String url = "jdbc:mysql://localhost:3306/db01";
        String user = "root";
        String password = "lth";
        //注册DriverManager驱动
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Way3="+connection);
    }

    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        //使用反射加载Driver类
//        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db01";
        String user = "root";
        String password = "lth";
        //使用Class。forName自动完成注册驱动
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Way4="+connection);
    }

    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {
        //增加配置文件，让连接更加灵活
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/lth/jdbc/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
//        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Way5="+connection);
    }
}