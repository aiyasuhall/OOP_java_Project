package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForgotPasswordDao {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("select * from user where uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Return the security question (or whatever is needed) to the controller/view
                return true;
            } else {
                throw new RuntimeException("Email address does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Database error: " + ex.getMessage(), ex);
        }
    }

    public String getSecurityQuestion(String email) {
        try {
            ps = con.prepareStatement("select * from user where uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(6); // Security question
            } else {
                throw new RuntimeException("Email address does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Database error: " + ex.getMessage(), ex);
        }
    }

    public boolean getAns(String email, String newAns) {
        try {
            ps = con.prepareStatement("select * from user where uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                String oldAns = rs.getString(7);
                if (newAns.equals(oldAns)) {
                    return true;
                } else {
                    throw new RuntimeException("Security answer did not match");
                }
            } else {
                throw new RuntimeException("Email address does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Database error: " + ex.getMessage(), ex);
        }
    }

    public void setNewPassword(String email, String password) {
        String sql = "update user set upassword = ? where uemail = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, email);
            if (ps.executeUpdate() <= 0) {
                throw new RuntimeException("Password update failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Database error: " + ex.getMessage(), ex);
        }
    }
}
