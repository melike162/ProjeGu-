package Paket16_GuıBitirme.Model;

import Paket16_GuıBitirme.Helper.DBconnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class room {
    private int id;
    private int feature_id;
    private int roomNumber;
    private int badNumber;
    private int television;
    private int bar;
    private int console;
    private int m2;
    private int safe;
    private int projection;
    private String type;

    public room(int id, int feature_id, int roomNumber, int badNumber, int television, int bar, int console, int m2, int safe, int projection, String type) {
        this.id = id;
        this.feature_id = feature_id;
        this.roomNumber = roomNumber;
        this.badNumber = badNumber;
        this.television = television;
        this.bar = bar;
        this.console = console;
        this.m2 = m2;
        this.safe = safe;
        this.projection = projection;
        this.type = type;
    }

    public room(){}

    public static ArrayList<room> getList(){
        String query="select * from room";
        ArrayList<room> rooms=new ArrayList<>();
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                room r=new room();
                r.setBadNumber(rs.getInt("badNumber"));
                r.setConsole(rs.getInt("console"));
                r.setBar(rs.getInt("bar"));
                r.setFeature_id(rs.getInt("feature_id"));
                r.setM2(rs.getInt("m2"));
                r.setId(rs.getInt("id"));
                r.setProjection(rs.getInt("projection"));
                r.setSafe(rs.getInt("safe"));
                r.setRoomNumber(rs.getInt("roomNumber"));
                r.setTelevision(rs.getInt("television"));
                r.setType(rs.getString("type"));
                rooms.add(r);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rooms;
    }

    public static boolean add(int feature_id, int roomNumber, int badNumber, int television, int bar, int console, int m2, int safe, int projection, String type){
        String query="insert into room (feature_id,roomNumber,badNumber,television,bar,console,m2,safe,projection,type) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,feature_id);
            pr.setInt(2,roomNumber);
            pr.setInt(3,badNumber);
            pr.setInt(4,television);
            pr.setInt(5,bar);
            pr.setInt(6,console);
            pr.setInt(7,m2);
            pr.setInt(8,safe);
            pr.setInt(9,projection);
            pr.setString(10,type);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean update(int id, int feature_id, int roomNumber, int badNumber, int television, int bar, int console, int m2, int safe, int projection, String type){
        String query="update room set feature_id=?,roomNumber=?,badNumber=?,television=?,bar=?,console=?,m2=?, safe=?,projection=?,type=? where id=?";
        try {
            PreparedStatement pr=DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,feature_id);
            pr.setInt(2,roomNumber);
            pr.setInt(3,badNumber);
            pr.setInt(4,television);
            pr.setInt(5,bar);
            pr.setInt(6,console);
            pr.setInt(7,m2);
            pr.setInt(8,safe);
            pr.setInt(9,projection);
            pr.setString(10,type);
            pr.setInt(11,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static room getFetch(int feature_id){
        String query="select * from room where feature_id=?";
        room r=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,feature_id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                r=new room();
                r.setBadNumber(rs.getInt("badNumber"));
                r.setConsole(rs.getInt("console"));
                r.setBar(rs.getInt("bar"));
                r.setFeature_id(rs.getInt("feature_id"));
                r.setM2(rs.getInt("m2"));
                r.setId(rs.getInt("id"));
                r.setProjection(rs.getInt("projection"));
                r.setSafe(rs.getInt("safe"));
                r.setRoomNumber(rs.getInt("roomNumber"));
                r.setTelevision(rs.getInt("television"));
                r.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return r;
    }

    public static room getFetch2(int id){
        String query="select * from room where id=?";
        room r=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                r=new room();
                r.setBadNumber(rs.getInt("badNumber"));
                r.setConsole(rs.getInt("console"));
                r.setBar(rs.getInt("bar"));
                r.setFeature_id(rs.getInt("feature_id"));
                r.setM2(rs.getInt("m2"));
                r.setId(rs.getInt("id"));
                r.setProjection(rs.getInt("projection"));
                r.setSafe(rs.getInt("safe"));
                r.setRoomNumber(rs.getInt("roomNumber"));
                r.setTelevision(rs.getInt("television"));
                r.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return r;
    }

    public static room getFetch(int feature_id,String type){
        String query="select * from room where feature_id=? and type=?";
        room r=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,feature_id);
            pr.setString(2,type);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                r=new room();
                r.setBadNumber(rs.getInt("badNumber"));
                r.setConsole(rs.getInt("console"));
                r.setBar(rs.getInt("bar"));
                r.setFeature_id(rs.getInt("feature_id"));
                r.setM2(rs.getInt("m2"));
                r.setId(rs.getInt("id"));
                r.setProjection(rs.getInt("projection"));
                r.setSafe(rs.getInt("safe"));
                r.setRoomNumber(rs.getInt("roomNumber"));
                r.setTelevision(rs.getInt("television"));
                r.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return r;
    }

    public static ArrayList<room> getListFea(int feature_id){
        String query="select * from room where feature_id=?";
        ArrayList<room> rooms=new ArrayList<>();
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,feature_id);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                room r=new room();
                r.setBadNumber(rs.getInt("badNumber"));
                r.setConsole(rs.getInt("console"));
                r.setBar(rs.getInt("bar"));
                r.setFeature_id(rs.getInt("feature_id"));
                r.setM2(rs.getInt("m2"));
                r.setId(rs.getInt("id"));
                r.setProjection(rs.getInt("projection"));
                r.setSafe(rs.getInt("safe"));
                r.setRoomNumber(rs.getInt("roomNumber"));
                r.setTelevision(rs.getInt("television"));
                r.setType(rs.getString("type"));
                rooms.add(r);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rooms;
    }
    public static boolean delete(int id){
        String query="delete from room where id=?";
        date.delete(date.getList(id).getId());
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

    public int getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(int feature_id) {
        this.feature_id = feature_id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getBadNumber() {
        return badNumber;
    }

    public void setBadNumber(int badNumber) {
        this.badNumber = badNumber;
    }

    public int getTelevision() {
        return television;
    }

    public void setTelevision(int television) {
        this.television = television;
    }

    public int getBar() {
        return bar;
    }

    public void setBar(int bar) {
        this.bar = bar;
    }

    public int getConsole() {
        return console;
    }

    public void setConsole(int console) {
        this.console = console;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public int getSafe() {
        return safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }

    public int getProjection() {
        return projection;
    }

    public void setProjection(int projection) {
        this.projection = projection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
