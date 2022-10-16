package Paket16_GuıBitirme.Model;

import Paket16_GuıBitirme.Helper.DBconnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class visitor {
    private int id;
    private int id_quene;
    private String name;
    private String country;
    private String tc;

    public visitor(int id, int id_quene, String name, String country, String tc) {
        this.id = id;
        this.id_quene = id_quene;
        this.name = name;
        this.country = country;
        this.tc = tc;
    }

    public visitor() {}

    public static ArrayList<visitor> getList(){
        String query="select * from visitor";
        ArrayList<visitor> visitors=new ArrayList<>();
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                visitor v=new visitor();
                v.setCountry(rs.getString("country"));
                v.setId(rs.getInt("id"));
                v.setName(rs.getString("name"));
                v.setId_quene(rs.getInt("id_quene"));
                v.setTc(rs.getString("tc"));
                visitors.add(v);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  visitors;
    }

    public static boolean add(int id_quene, String name, String country, String tc){
        String query="insert into visitor (id_quene,name,country,tc) values (?,?,?,?)";

        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);


            pr.setInt(1,id_quene);
            pr.setString(2,name);
            pr.setString(3,country);
            pr.setString(4,tc);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  true;
    }

    public static boolean delete(int id){
        String query="delete from visitor where id=?";
        try {
            PreparedStatement pr=DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_quene() {
        return id_quene;
    }

    public void setId_quene(int id_quene) {
        this.id_quene = id_quene;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }
}
