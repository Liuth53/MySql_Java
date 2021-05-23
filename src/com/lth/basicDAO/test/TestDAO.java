package com.lth.basicDAO.test;

import com.lth.basicDAO.dao.UsersDAO;
import com.lth.basicDAO.javaBean.Users;
import org.junit.Test;

import java.util.List;

public class TestDAO {
    @Test
    public void testUsersDAO(){
        UsersDAO usersDAO = new UsersDAO();
        //查询
        List<Users> usersList = usersDAO.queryMultiply("select * from users where id >= ?", Users.class, 1);
        for (Users users : usersList){
            System.out.println(users);
        }


        Users users1 = usersDAO.querySingle("select * from users where id >= ?", Users.class, 1);
        System.out.println(users1);

        Object o = usersDAO.queryScalar("select name from users where id >= ?", 1);
        System.out.println(o);

        //DML
        int i = usersDAO.update("insert into users values(null,?,?,?)", "B", "西安", "50000");
        System.out.println(i>0? "Success":"Fail");

    }
}
