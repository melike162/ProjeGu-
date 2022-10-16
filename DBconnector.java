package Paket16_GuıBitirme.Helper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
    public Connection connector=null;
    private final String DB_URL="jdbc:mysql://localhost/turizm";
    private final String DB_USERNAME="root";
    public final String DB_PASSWORD="159357m";

    public Connection connetDB(){
        try {
            this.connector= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.connector;
    }

    public static Connection getInstance(){
        DBconnector con=new DBconnector();
        return con.connetDB();
    }
}
