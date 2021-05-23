/**

* @Description:    JavaBean

* @Author:         Lth

* @CreateDate:     2021/5/23 14:14

* @UpdateUser:     Lth

* @UpdateDate:     2021/5/23 14:14

* @UpdateRemark:

* @Version:        1.8

*/


package com.lth.basicDAO.javaBean;
public class Users {

    private Integer id;
    private String name;
    private String address;
    private int account;
    public Users() {}

    public Users(Integer id, String name, String address, int account) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.account = account;
    }

    @Override
    public String toString() {
        return "\nUsers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", account=" + account +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
}
