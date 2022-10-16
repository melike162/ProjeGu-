package Paket16_GuıBitirme.Model;

import Paket16_GuıBitirme.Helper.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class date {
    private int id;
    private int room_id;
    private int d1;
    private int d2;
    private int d3;
    private int d4;

    public date(int id, int room_id, int d1, int d2, int d3, int d4) {
        this.id = id;
        this.room_id = room_id;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
    }

    public date() {}


    public static ArrayList<date> getList(){
        String query="select * from date";
        ArrayList<date> d=new ArrayList<>();
        date de=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                de=new date();
                de.setId(rs.getInt("id"));
                de.setRoom_id(rs.getInt("room_id"));
                de.setD1(rs.getInt("d1"));
                de.setD2(rs.getInt("d2"));
                de.setD3(rs.getInt("d3"));
                de.setD4(rs.getInt("d4"));
                d.add(de);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return d;
    }

    public static boolean update(int id, int room_id, int d1, int d2, int d3, int d4){
        String query="update date set room_id=?, d1=?, d2=?, d3=?, d4=? where id=?";
        try {
            PreparedStatement pr=DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            pr.setInt(2,d1);
            pr.setInt(3,d2);
            pr.setInt(4,d3);
            pr.setInt(5,d4);
            pr.setInt(6,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    public static date getList(int room_id){
        String query="select * from date where room_id=?";
        date de=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                de=new date();
                de.setId(rs.getInt("id"));
                de.setRoom_id(rs.getInt("room_id"));
                de.setD1(rs.getInt("d1"));
                de.setD2(rs.getInt("d2"));
                de.setD3(rs.getInt("d3"));
                de.setD4(rs.getInt("d4"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return de;
    }

    public static boolean add(int room_id,int d1,int d2,int d3,int d4){
        String query="insert into date (room_id,d1,d2,d3,d4) values (?,?,?,?,?)";
        try {
            PreparedStatement pr=DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            pr.setInt(2,d1);
            pr.setInt(3,d2);
            pr.setInt(4,d3);
            pr.setInt(5,d4);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean delete(int id){
        String query="delete from date where id=?";
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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getD1() {
        return d1;
    }

    public void setD1(int d1) {
        this.d1 = d1;
    }

    public int getD2() {
        return d2;
    }

    public void setD2(int d2) {
        this.d2 = d2;
    }

    public int getD3() {
        return d3;
    }

    public void setD3(int d3) {
        this.d3 = d3;
    }

    public int getD4() {
        return d4;
    }

    public void setD4(int d4) {
        this.d4 = d4;
    }
}
