package sensorcontroller;
import java.sql.*;
import javax.swing.JOptionPane;

public class DBConnection {
    
    public static Connection con;
    private static final String driver = "com.postgresql.jdbc.Driver";
    private static final String user = "admin";
    private static final String pass = "desk123";
    private static final String url = "jdbc:postgresql://localhost:5432/UserLogin";
    
    public Connection connect() {
        con = null;
        try {
            con = (Connection) DriverManager.getConnection(url, user, pass);
            
            if (con != null) {  
                
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString() );
        }
        return con;
    } 
}