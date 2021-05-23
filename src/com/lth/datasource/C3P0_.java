package com.lth.datasource;

import com.lth.jdbcUtils.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class C3P0_ {
    public static void main(String[] args) {

    }
    @Test
    public void C3P0_01() throws Exception {
        //创建数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //通过配置文件获取信息
        //定义相关的属性(4个), 因为只需要一份，因此，我们做出static
        String user; //用户名
        String password; //密码
        String url; //url
        String driver; //驱动名
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
        driver = properties.getProperty("driver");

        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        //初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        //最大连接
        comboPooledDataSource.setMaxPoolSize(50);

        for (int i = 0;i < 5000; i++){
            //获取连接
            Connection connection = comboPooledDataSource.getConnection();
//            System.out.println("连接成功"+i);
            connection.close();
        }
    }

    //通过配置文件模板完成
    @Test
    public void C3P0_02() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("lth");
        long start = System.currentTimeMillis();
        for (int i = 0;i < 500000; i++){
            Connection connection = comboPooledDataSource.getConnection();
//            System.out.println("连接成功");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time:"+(end-start));
    }

}
