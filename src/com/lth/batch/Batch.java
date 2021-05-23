package com.lth.batch;

import com.lth.jdbcUtils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch {
    public static void main(String[] args) {

    }
    @Test
    public void noBatch(){

        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin values(null,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            long start=System.currentTimeMillis();
            for (int i = 0; i < 5000; i++){
                preparedStatement.setString(1,"a"+i);
                preparedStatement.setString(2,"b"+i);
                preparedStatement.executeUpdate();
            }
            long end=System.currentTimeMillis();
            System.out.println("time:"+(end-start));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,preparedStatement,connection);
        }

    }
    @Test
    public void Batch(){

        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin values(null,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            long start=System.currentTimeMillis();
            for (int i = 0; i < 5000; i++){
                preparedStatement.setString(1,"a"+i);
                preparedStatement.setString(2,"b"+i);
                //加入批处理包
                preparedStatement.addBatch();
                //当记录有1000条时统一发送
                if ((i+1)%1000 == 0){
                    preparedStatement.executeBatch();
                    //清空
                    preparedStatement.clearBatch();
                }
            }
            long end=System.currentTimeMillis();
            System.out.println("time:"+(end-start));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,preparedStatement,connection);
        }

    }

}
