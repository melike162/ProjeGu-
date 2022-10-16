package Paket16_GuıBitirme.Model;

import Paket16_GuıBitirme.Helper.DBconnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pansiyonFeature {
     private int id;
     private String name;
     private String City;
     private String region;
     private String adress;
     private String mail;
     private String phone;
     private int stare;

    public pansiyonFeature(int id, String name, String adress,String City,String region, String mail, String phone, int stare) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.mail = mail;
        this.phone = phone;
        this.stare = stare;
        this.City=City;
        this.region=region;
    }
    public pansiyonFeature(){}

    public static ArrayList<pansiyonFeature> getList(){
        ArrayList<pansiyonFeature> arrayList=new ArrayList<>();
        String query="select * from pansiyonfeature";
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                arrayList.add(new pansiyonFeature(rs.getInt("id"),rs.getString("name"),rs.getString("adress"),rs.getString("City"),rs.getString("region"),rs.getString("mail"),rs.getString("phone"),rs.getInt("stare")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arrayList;
    }

    public static pansiyonFeature getFetch(String name,int id){
        String query="select * from pansiyonfeature where name=?";
        pansiyonFeature feature=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                if (rs.getInt("id")!=id){
                    feature=new pansiyonFeature();
                    feature.setName(rs.getString("name"));
                    feature.setId(rs.getInt("id"));
                    feature.setAdress(rs.getString("adress"));
                    feature.setMail(rs.getString("mail"));
                    feature.setPhone(rs.getString("phone"));
                    feature.setStare(rs.getInt("stare"));
                    feature.setCity(rs.getString("City"));
                    feature.setRegion(rs.getString("region"));
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return feature;
    }

    public static pansiyonFeature getFetch(int id){
        String query="select * from pansiyonfeature where id=?";
        pansiyonFeature feature=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                feature=new pansiyonFeature();
                feature.setName(rs.getString("name"));
                feature.setId(rs.getInt("id"));
                feature.setAdress(rs.getString("adress"));
                feature.setMail(rs.getString("mail"));
                feature.setPhone(rs.getString("phone"));
                feature.setStare(rs.getInt("stare"));
                feature.setCity(rs.getString("City"));
                feature.setRegion(rs.getString("region"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return feature;
    }

    public static boolean add(String name, String adress,String City,String region, String mail, String phone, int stare){
        String query="insert into pansiyonfeature (name,adress,City,region,mail,phone,stare) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr=DBconnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,adress);
            pr.setString(3,City);
            pr.setString(4,region);
            pr.setString(5,mail);
            pr.setString(6,phone);
            pr.setInt(7,stare);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean update(int id,String name,String adress,String City,String region,String mail,String phone,int stare){
        String query="update pansiyonfeature set name=?,adress=?,mail=?,phone=?,stare=?,City=?,region=? where id=?";
        try {
            PreparedStatement pr=DBconnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,adress);
            pr.setString(3,mail);
            pr.setString(4,phone);
            pr.setInt(5,stare);
            pr.setString(6,City);
            pr.setString(7,region);
            pr.setInt(8,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean delete(int id){
        String query="delete from pansiyonfeature where id=?";
        for (feature fea: feature.getListPansiyon(id)){
            feature.delete(fea.getId());
        }
        try {
            PreparedStatement pr=DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStare() {
        return stare;
    }

    public void setStare(int stare) {
        this.stare = stare;
    }

}
