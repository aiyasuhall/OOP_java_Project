package dao;

import connection.MyConnection;
import model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SupplierDao extends Base_DAO<Supplier> {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    //get supplier table max row
    public int getMaxRow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("Select max(sid) from supplier");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    //check email already exists
    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("select * from supplier where semail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //check phone number already exists
    public boolean isPhoneExist(String phone) {
        try {
            ps = con.prepareStatement("select * from supplier where sphone = ?");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //check supplier username already exists
    public boolean isUsernameExist(String name) {
        try {
            ps = con.prepareStatement("select * from supplier where sname = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void insert(Supplier supplier) {
        String sql = "insert into supplier values(?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, supplier.getId());
            ps.setString(2, supplier.getUsername());
            ps.setString(3, supplier.getEmail());
            ps.setString(4, supplier.getPassword());
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getAddress());

            int result = ps.executeUpdate();
            if (result <= 0) {
                throw new SQLException("Insert supplier failed.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to insert supplier: " + ex.getMessage(), ex);
        }
    }

    //get suppliers data
    public void getSupplierValue(JTable table, String search) {
        String sql = "select * from supplier where concat(sid, sname, semail) like ? order by sid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Supplier supplier) {
        String sql = "update supplier set sname = ?, semail = ?, spassword = ?, sphone = ?, saddress = ? where sid = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, supplier.getUsername());
            ps.setString(2, supplier.getEmail());
            ps.setString(3, supplier.getPassword());
            ps.setString(4, supplier.getPhone());
            ps.setString(5, supplier.getAddress());
            ps.setInt(6, supplier.getId());
            int result = ps.executeUpdate();
            if (result <= 0) {
                throw new SQLException("Update supplier failed.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to update supplier: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            ps = con.prepareStatement("delete from supplier where sid = ?");
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result <= 0) {
                throw new SQLException("Delete supplier failed.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to delete supplier: " + ex.getMessage(), ex);
        }
    }

    //get supplier id
    public int getSupplierId(String email) {
        int id = 0;
        try {
            ps = con.prepareStatement("select sid from supplier where semail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    //get supplier username
    public String getSupplierName(String email) {
        String supplierName = "";
        try {
            ps = con.prepareStatement("select sname from supplier where semail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                supplierName = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supplierName;
    }

    //get supplier value
    public String[] getSupplierValue(int id) {
        String[] value = new String[6];
        try {
            ps = con.prepareStatement("select * from supplier where sid = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
                value[5] = rs.getString(6);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public int countSuppliers() {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as 'total' from supplier");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public String[] getSuppliers() {
        String[] suppliers = new String[countSuppliers()];
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from supplier");
            int i = 0;
            while (rs.next()) {
                suppliers[i] = rs.getString(2);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suppliers;
    }

    public boolean validateSupplier(String email, String password) {
        try {
            Connection con = MyConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM supplier WHERE semail=? AND spassword=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Return true if found
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
