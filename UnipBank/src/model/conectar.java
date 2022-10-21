package model;
import java.sql.*;

public class conectar {
    public conectar(){
    }
    public Connection  getconnection(){
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "1234sql@");
        }
        catch (Exception ex){
        
        }
        return con;
    }
}
