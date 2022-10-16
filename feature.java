package Paket16_GuıBitirme.Model;

import Paket16_GuıBitirme.Helper.DBconnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class feature {
    private int id;
    private int freePark;
    private int freeWF;
    private int swimPool;
    private int fitnessCenter;
    private int hotelConcierge;
    private int SPA;
    private int roomService;
    private int pansiyon_id;
    private String type;

    public feature(int id, int freePark, int freeWF, int swimPool, int fitnessCenter, int hotelConcierge, int SPA, int roomService, int pansiyon_id, String type) {
        this.id = id;
        this.freePark = freePark;
        this.freeWF = freeWF;
        this.swimPool = swimPool;
        this.fitnessCenter = fitnessCenter;
        this.hotelConcierge = hotelConcierge;
        this.SPA = SPA;
        this.roomService = roomService;
        this.pansiyon_id = pansiyon_id;
        this.type = type;
    }

    public feature(){}

    public static boolean add(int freePark, int freeWF, int swimPool, int fitnessCenter, int hotelConcierge, int SPA, int roomService, int pansiyon_id, String type){
        String query="insert into feature (freePark,freeWF,swimPool,fitnessCenter,hotelConcierge,SPA,roomService,pansiyon_id,type) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr=DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,freePark);
            pr.setInt(2,freeWF);
            pr.setInt(3,swimPool);
            pr.setInt(4,fitnessCenter);
            pr.setInt(5,hotelConcierge);
            pr.setInt(6,SPA);
            pr.setInt(7,roomService);
            pr.setInt(8,pansiyon_id);
            pr.setString(9,type);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    public static boolean update(int id,int freePark, int freeWF, int swimPool, int fitnessCenter, int hotelConcierge, int SPA, int roomService, int pansiyon_id, String type){
        String query="update feature set freePark=?, freeWF=?, swimPool=?, fitnessCenter=?, hotelConcierge=?, SPA=?, roomService=?,pansiyon_id=?, type=? where id=?";
        try {
            PreparedStatement pr=DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,freePark);
            pr.setInt(2,freeWF);
            pr.setInt(3,swimPool);
            pr.setInt(4,fitnessCenter);
            pr.setInt(5,hotelConcierge);
            pr.setInt(6,SPA);
            pr.setInt(7,roomService);
            pr.setInt(8,pansiyon_id);
            pr.setString(9,type);
            pr.setInt(10,id);
            return pr.executeUpdate()!=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static feature getFetch2(int id){
        String query="select * from feature where id=?";
        feature fea=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                fea=new feature();
                fea.setFreePark(rs.getInt("freePark"));
                fea.setFitnessCenter(rs.getInt("fitnessCenter"));
                fea.setFreeWF(rs.getInt("freeWF"));
                fea.setId(rs.getInt("id"));
                fea.setHotelConcierge(rs.getInt("hotelConcierge"));
                fea.setPansiyon_id(rs.getInt("pansiyon_id"));
                fea.setSPA(rs.getInt("SPA"));
                fea.setRoomService(rs.getInt("roomService"));
                fea.setSwimPool(rs.getInt("swimPool"));
                fea.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fea;
    }

    public static feature getFetch(int id){
        String query="select * from feature where pansiyon_id=?";
        feature fea=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                fea=new feature();
                fea.setFreePark(rs.getInt("freePark"));
                fea.setFitnessCenter(rs.getInt("fitnessCenter"));
                fea.setFreeWF(rs.getInt("freeWF"));
                fea.setId(rs.getInt("id"));
                fea.setHotelConcierge(rs.getInt("hotelConcierge"));
                fea.setPansiyon_id(rs.getInt("pansiyon_id"));
                fea.setSPA(rs.getInt("SPA"));
                fea.setRoomService(rs.getInt("roomService"));
                fea.setSwimPool(rs.getInt("swimPool"));
                fea.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fea;
    }
    public static feature getFetchT(int id,String type){
        String query="select * from feature where pansiyon_id=? and type=?";
        feature fea=null;
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            pr.setString(2,type);
            ResultSet rs=pr.executeQuery();
            if (rs.next()){
                fea=new feature();
                fea.setFreePark(rs.getInt("freePark"));
                fea.setFitnessCenter(rs.getInt("fitnessCenter"));
                fea.setFreeWF(rs.getInt("freeWF"));
                fea.setId(rs.getInt("id"));
                fea.setHotelConcierge(rs.getInt("hotelConcierge"));
                fea.setPansiyon_id(rs.getInt("pansiyon_id"));
                fea.setSPA(rs.getInt("SPA"));
                fea.setRoomService(rs.getInt("roomService"));
                fea.setSwimPool(rs.getInt("swimPool"));
                fea.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fea;
    }

    public static ArrayList<feature> getListPansiyon(int pansiyon_id){
        String query="select * from feature where pansiyon_id=?";
        ArrayList<feature> features=new ArrayList<>();
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            pr.setInt(1,pansiyon_id);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                feature fea=new feature();
                fea.setFreePark(rs.getInt("freePark"));
                fea.setFitnessCenter(rs.getInt("fitnessCenter"));
                fea.setFreeWF(rs.getInt("freeWF"));
                fea.setId(rs.getInt("id"));
                fea.setHotelConcierge(rs.getInt("hotelConcierge"));
                fea.setPansiyon_id(rs.getInt("pansiyon_id"));
                fea.setSPA(rs.getInt("SPA"));
                fea.setRoomService(rs.getInt("roomService"));
                fea.setSwimPool(rs.getInt("swimPool"));
                fea.setType(rs.getString("type"));
                features.add(fea);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  features;
    }

    public static ArrayList<feature> getList(){
        String query="select * from feature";
        ArrayList<feature> features=new ArrayList<>();
        try {
            PreparedStatement pr= DBconnector.getInstance().prepareStatement(query);
            ResultSet rs=pr.executeQuery();
            while (rs.next()){
                feature fea=new feature();
                fea.setFreePark(rs.getInt("freePark"));
                fea.setFitnessCenter(rs.getInt("fitnessCenter"));
                fea.setFreeWF(rs.getInt("freeWF"));
                fea.setId(rs.getInt("id"));
                fea.setHotelConcierge(rs.getInt("hotelConcierge"));
                fea.setPansiyon_id(rs.getInt("pansiyon_id"));
                fea.setSPA(rs.getInt("SPA"));
                fea.setRoomService(rs.getInt("roomService"));
                fea.setSwimPool(rs.getInt("swimPool"));
                fea.setType(rs.getString("type"));
                features.add(fea);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  features;
    }

    public static boolean delete(int id){
        String query="delete from feature where id=?";
        for(room r: room.getListFea(id)){
            room.delete(r.getId());
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFreePark() {
        return freePark;
    }

    public void setFreePark(int freePark) {
        this.freePark = freePark;
    }

    public int getFreeWF() {
        return freeWF;
    }

    public void setFreeWF(int freeWF) {
        this.freeWF = freeWF;
    }

    public int getSwimPool() {
        return swimPool;
    }

    public void setSwimPool(int swimPool) {
        this.swimPool = swimPool;
    }

    public int getFitnessCenter() {
        return fitnessCenter;
    }

    public void setFitnessCenter(int fitnessCenter) {
        this.fitnessCenter = fitnessCenter;
    }

    public int getHotelConcierge() {
        return hotelConcierge;
    }

    public void setHotelConcierge(int hotelConcierge) {
        this.hotelConcierge = hotelConcierge;
    }

    public int getSPA() {
        return SPA;
    }

    public void setSPA(int SPA) {
        this.SPA = SPA;
    }

    public int getRoomService() {
        return roomService;
    }

    public void setRoomService(int roomService) {
        this.roomService = roomService;
    }

    public int getPansiyon_id() {
        return pansiyon_id;
    }

    public void setPansiyon_id(int pansiyon_id) {
        this.pansiyon_id = pansiyon_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
