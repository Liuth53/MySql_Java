package com.lth.jdbcUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtils_Use {
    public static void main(String[] args) {

    }
    //Apache-DBUtils + Druid
    @Test
    public void testQueryMany() throws SQLException {
//        String sql = "select id,name from users where id >= ?";
        String sql = "select * from users where id >= ?";
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        //BeanListHandler<>(Users.class)返回多个对象并封装成集合

        List<Users>list = queryRunner.query(connection,sql,new BeanListHandler<>(Users.class),1);
        for (Users users :list){
            System.out.print(users);
        }

        JDBCUtilsByDruid.close(null,null,connection);
    }

    @Test
    public void testQuerySingle() throws SQLException {
        //        String sql = "select id,name from users where id >= ?";
        String sql = "select * from users where id >= ?";
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        //BeanHandler<>(Users.class)返回单个对象
        Users users = queryRunner.query(connection,sql,new BeanHandler<>(Users.class),1);
            System.out.print(users);
        JDBCUtilsByDruid.close(null,null,connection);
    }
}
