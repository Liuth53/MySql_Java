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
import java.sql.SQLException;

public class jdbcUtils_Use {
    public static void main(String[] args) {

    }
    @Test
    public void Test_DML(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into admin values(null,?,?)";
            try {
                connection = JDBCUtils.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,"a");
                preparedStatement.setString(2,"a");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //工具类关闭资源
                JDBCUtils.close(null, preparedStatement, connection);
            }

    }
}
