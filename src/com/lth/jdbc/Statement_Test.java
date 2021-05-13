/**

* @Description:    Statement injection

* @Author:         Lth

* @CreateDate:     2021/5/12 19:46

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/12 19:46

* @UpdateRemark:

* @Version:        1.8

*/
package com.lth.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Statement_Test {
    public static void main(String[] args) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);

        //管理员输入账号密码
        System.out.println("Please Input Your Name:");
        String admin_name=scanner.nextLine();
        System.out.println("Please Input Your PassWord:");
        String admin_password=scanner.nextLine();

        //增加配置文件，让连接更加灵活
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/lth/jdbc/mysql2.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
//        Class.forName("com.mysql.jdbc.Driver");
        //得到连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //得到statement
        Statement statement = connection.createStatement();
            String sql = "select ID, UserName, PassWord from admin where UserName='"
                    +admin_name+"' and PassWord = '"+admin_password+"' ";

        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            System.out.println("Load Successfully！");
        }
        else{
            System.out.println("Load Fail");
        }

        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
