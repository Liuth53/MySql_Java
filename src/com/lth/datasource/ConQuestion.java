package com.lth.datasource;

import com.lth.jdbcUtils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConQuestion {
    public void testCon(){
        for (int i = 0;i < 5000; i++){
            Connection connection = JDBCUtils.getConnection();
            JDBCUtils.close(null,null,connection);
        }
    }
    public void ConnectionPool(){

    }
}
