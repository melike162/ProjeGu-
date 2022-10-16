package Paket16_GuıBitirme.Model;




import Paket16_GuıBitirme.Helper.DBconnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user {
    private int id;
    private String name;
    private String uname;
    private String password;
    private int type;

    public user(int id, String name, String uname, String password, int type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.type = type;
    }

    public static user getFetch(String uname,String password){
        String query="select * from user where uname=? and password=?";
        user user=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setString(1,uname);
            pr.setString(2,password);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                user=new user(rs.getInt("id"),rs.getString("name"),rs.getString("uname"),rs.getString("password"),rs.getInt("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
