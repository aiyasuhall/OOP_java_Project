package dao;

import connection.MyConnection;
import model.User;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserDao extends Base_DAO<User> {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    //get user table max row
    public int getMaxRow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("Select max(uid) from user");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    //check email already exists
    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("select * from user where uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //check phone number already exists
    public boolean isPhoneExist(String phone) {
        try {
            ps = con.prepareStatement("select * from user where uphone = ?");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    //insert data into user table
    public void insert(User user) {
        String sql = "insert into user values(?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getSecurityQuestion());
            ps.setString(7, user.getAnswer());
            ps.setString(8, user.getAddress());
            int result = ps.executeUpdate();
            if (result <= 0) {
                throw new SQLException("User added failed, no rows affected.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to insert user: " + ex.getMessage(), ex);
        }
    }

    //update user data
    @Override
    public void update(User user) {
        String sql = "update user set uname = ?, uemail = ?, upassword = ?, uphone = ?, usecqus = ?, uans = ?, uaddress = ? where uid = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getSecurityQuestion());
            ps.setString(6, user.getAnswer());
            ps.setString(7, user.getAddress());
            ps.setInt(8, user.getId());
            int result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to update user: " + ex.getMessage(), ex);
        }
    }

    //delete user data
    @Override
    public void delete(int id) {
        try {
            ps = con.prepareStatement("delete from user where uid = ?");
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException("Delete failed, no rows affected.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to delete user: " + ex.getMessage(), ex);
        }
    }

    //get user value
    public String[] getUserValue(int id) {
        String[] value = new String[8];
        try {
            ps = con.prepareStatement("select * from user where uid = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
                value[5] = rs.getString(6);
                value[6] = rs.getString(7);
                value[7] = rs.getString(8);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    //get user id
    public int getUserId(String email) {
        int id = 0;
        try {
            ps = con.prepareStatement("select uid from user where uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    //get users data
    public void getUsersValue(JTable table, String search) {
        String sql = "select * from user where concat(uid, uname, uemail) like ? order by uid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[8];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validateUser(String email, String password) {
        try {
            Connection con = MyConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE uemail=? AND upassword=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();  // Return true if user found
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
