/**

* @Description:    how to ues jdbcUtils

* @Author:         Lth

* @CreateDate:     2021/5/19 12:54

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/19 12:54

* @UpdateRemark:   

* @Version:        1.8

*/
package com.lth.jdbcUtils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcUtils_Use {
    public static void main(String[] args) {

    }
    @Test
    public void Test_DML(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;
        //执行DML语句
        String sql = "insert into admin values(null,?,?)";
        String sql2 = "select id, name, address from Users";
            try {
                connection = JDBCUtilsByDruid.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement.setString(1,"c");
                preparedStatement.setString(2,"c");
                int rows = preparedStatement.executeUpdate();
                System.out.println(rows > 0 ? "成功":"失败");
                resultSet = preparedStatement2.executeQuery();
                //使用while循环取出数据
                while (resultSet.next()){
                    Object id = resultSet.getObject(1);
//            int id = resultSet.getInt(1);//获取第一列数据
                    String name = resultSet.getString(2);
                    String address = resultSet.getString("address");
                    System.out.println(id+"\t"+name+"\t"+address+"\t");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //工具类关闭资源
                JDBCUtilsByDruid.close(resultSet, preparedStatement, connection);
                JDBCUtilsByDruid.close(resultSet, preparedStatement2, connection);
            }

    }
}
