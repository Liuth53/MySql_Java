package com.lth.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Properties;

public class Druid_ {
    public static void main(String[] args) {

    }
    @Test
    public void Druid_01() throws Exception {
        //创建Properties对象，读取配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\druid.properties"));
        //创建指定参数的连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        dataSource.getConnection();

        System.out.println("连接成功");
        long start = System.currentTimeMillis();
        for (int i = 0;i < 500000; i++){
            Connection connection = dataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time:"+(end-start));

    }
}
