/**

* @Description:    DML

* @Author:         Lth

* @CreateDate:     2021/5/19 11:17

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/19 11:17

* @UpdateRemark:

* @Version:        1.8

*/
package com.lth.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PrepareStatement_Update {
    public static void main(String[] args) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);

        //管理员输入账号密码
        System.out.println("Please Input new Name:");
        String admin_name=scanner.nextLine();
        System.out.println("Please Input new PassWord:");
        String admin_password=scanner.nextLine();

        //增加配置文件，让连接更加灵活
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//mysql2.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
//        Class.forName("com.mysql.jdbc.Driver");
        //得到连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //添加
        String sql1 = "insert into admin values(null,?,?)"; //得到PrepareStatement
        //更新
//        String sql2 = "update admin set PassWord = ? where UserName = ?"; //得到PrepareStatement
//        //删除
//        String sql3 = "delete from admin where UserName = ?"; //得到PrepareStatement

        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
//        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
//        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
        //获取占位符参数
        preparedStatement1.setString(1,admin_name);
        preparedStatement1.setString(2,admin_password);
//        preparedStatement2.setString(2,admin_name);
//        preparedStatement2.setString(1,admin_password);
//        preparedStatement3.setString(1,admin_name);
        // （查询结果executeUpdate()）
        int rows1 = preparedStatement1.executeUpdate();//此处不写sql
//        int rows2 = preparedStatement2.executeUpdate();//此处不写sql
//        int rows3 = preparedStatement3.executeUpdate();//此处不写sql
        System.out.println(rows1 > 0 ?"Successful":"Fail");
        //关闭连接
        preparedStatement1.close();
//        preparedStatement2.close();
//        preparedStatement3.close();
        connection.close();
    }

}
