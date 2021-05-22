/**

* @Description:    Prepare Statement

* @Author:         Lth

* @CreateDate:     2021/5/19 11:16

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/19 11:16

* @UpdateRemark:

* @Version:        1.8

*/
/**

* @Description:    PreparedStatement

* @Author:         Lth

* @CreateDate:     2021/5/12 19:46

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/12 19:46

* @UpdateRemark:

* @Version:        1.8

*/
package com.lth.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatement_Test {
    public static void main(String[] args) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);

        //管理员输入账号密码
        System.out.println("Please Input Your Name:");
        String admin_name=scanner.nextLine();
        System.out.println("Please Input Your PassWord:");
        String admin_password=scanner.nextLine();

        //增加配置文件，让连接更加灵活
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql2.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
//        Class.forName("com.mysql.jdbc.Driver");
        //得到连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //查询
        String sql = "select UserName , PassWord  from admin where UserName =? and PassWord =?";
        //得到PrepareStatement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //获取占位符参数
        preparedStatement.setString(1,admin_name);
        preparedStatement.setString(2,admin_password);
        //导出筛选后的数据表 （查询结果executeQuery()）
        ResultSet resultSet = preparedStatement.executeQuery();//此处不写sql
        //若存在匹配该用户则成功登陆
        if(resultSet.next()){
            System.out.println("Load Successfully！");
        }
        else{
            System.out.println("Load Fail");
        }
        //关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
