/**

* @Description:    Apache-DBUtils + Druid

* @Author:         Lth

* @CreateDate:     2021/5/23 12:20

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/23 13:20

* @UpdateRemark:

* @Version:        1.8

*/
package com.lth.jdbcUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtils_Use {
    public static void main(String[] args) {}
    //Apache-DBUtils + Druid
    //返回多行
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

    //返回单行
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

    //返回某行某列
    @Test
    public void testScalar() throws SQLException {
        //返回单行单列（Object）
        String sql = "select * from users where id >= ?";
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        Object obj = queryRunner.query(connection, sql, new ScalarHandler<>(), 4);
        System.out.println(obj);

        JDBCUtilsByDruid.close(null,null,connection);
    }

    //DML操作
    @Test
    public void testDML() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        //查询(把ID为4的用户的name改成OldDriver)
        String sql1 = "select * from users where id >= ?";
        //修改
        String sql2 = "update users set name = ? where id = ?";
        //插入
        String sql3 ="insert into users values(null,'张三','福州',1000)";
        //删除
        String sql4 ="delete from users where id = 5 ";
        //返回受影响的行数
        int affectedRow = queryRunner.update(connection, sql2, "OldDriver", 4);
        queryRunner.update(connection, sql3);
        queryRunner.update(connection, sql4);
        System.out.println(affectedRow > 0 ? "Success":"Fail");

        JDBCUtilsByDruid.close(null,null,connection);
    }
}
