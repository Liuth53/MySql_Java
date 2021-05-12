/**

* @Description:    First JDBC Program

* @Author:         Lth

* @CreateDate:     2021/5/12 14:19

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/12 14:19

* @UpdateRemark:

* @Version:        11.0

*/
package com.lth.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC01 {
    public static void main(String[] args) throws SQLException {
        //创建驱动
        Driver driver = new Driver();
        //连接数据库
        //localhost:可以使IP地址
        String url = "jdbc:mysql://localhost:3306/db01";
        Properties properties = new Properties();
        //指定用户名和密码
        properties.setProperty("user","root");
        properties.setProperty("password","lth");
        //获取连接
        Connection connect = driver.connect(url, properties);
        //执行SQL语句
        String sql = "insert into users values(4,'赵六','天津')";
        //执行静态SQL语句并返回生成结果的对象
        Statement statement = connect.createStatement();
        //如果是dml语句，则返回影响行数，成功返回1，失败0
        int rows = statement.executeUpdate(sql);
        System.out.println(rows > 0 ? "成功":"失败");

        //关闭资源
        statement.close();
        connect.close();
    }
}
