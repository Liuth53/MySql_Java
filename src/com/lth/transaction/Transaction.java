/**

* @Description:    Transaction

* @Author:         Lth

* @CreateDate:     2021/5/22 12:41

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/22 12:41

* @UpdateRemark:

* @Version:        1.8

*/
package com.lth.transaction;

import com.lth.jdbcUtils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    public static void main(String[] args) {

    }
    @Test
    public void noTransaction(){
        Connection connection = null;
        String sql1 = "update Users set account = account-100 where id = 1";
        String sql2 = "update Users set account = account+100 where id = 2";
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }finally {
            JDBCUtils.close(null,preparedStatement,connection);
        }
    }

    @Test
    public void Transaction(){
        Connection connection = null;
        String sql1 = "update Users set account = account-100 where id = 1";
        String sql2 = "update Users set account = account+100 where id = 2";
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);//开启事务
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            //回滚
            System.out.println("Rollback");
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,preparedStatement,connection);
        }
    }
}
