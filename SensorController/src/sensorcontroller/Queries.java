package sensorcontroller;

import java.sql.*;
import javax.swing.JOptionPane;

public class Queries {

    public boolean saveUser(String username, String password, String email) {
        DBConnection db = new DBConnection();
        String sql = "INSERT INTO user_credentials(username, password, email) VALUES ('" + username + "', '" + password + "', '" + email + "')";
        Connection con = db.connect();
        Statement st;
        boolean isSuccess = false;

        try {
            st = con.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Saved successfully.");
            isSuccess = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Username or email already exists.");
        }
        return isSuccess;
    }

    public void userAccess(String email, String password) {
        DBConnection db = new DBConnection();
        String indicatedPassword = null;

        try {
            Connection conn = db.connect();
            // Usar prepared statement correctamente para evitar inyección SQL
            PreparedStatement pst = conn.prepareStatement("SELECT password FROM user_credentials WHERE email = ?");
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                indicatedPassword = rs.getString(1);
            }

            if (indicatedPassword != null && password.equals(indicatedPassword)) {

                System.out.println("Login successful for user: " + email);
            } else {
                if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please complete all fields.");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Incorrect user or password.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }
    }
}
