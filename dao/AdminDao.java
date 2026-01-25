package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminDao {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public boolean validateAdmin(String email, String password) {
        try {
            Connection con = MyConnection.getConnection();
            String sql = "select * from admin where email = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Return true if a record is found
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
