package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductDao {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    //get product table max row
    public int getMaxRow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("Select max(pid) from product");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    public int countCategories() {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as 'total' from category");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public String[] getCat() {
        String[] categories = new String[countCategories()];
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from category");
            int i = 0;
            while (rs.next()) {
                categories[i] = rs.getString(2);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    //check product id already exists
    public boolean isIdExist(int id) {
        try {
            ps = con.prepareStatement("select * from product where pid = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //check product and category existed
    public boolean isProCatExist(String pro, String cat) {
        try {
            ps = con.prepareStatement("select * from product where pname = ? and cname = ?");
            ps.setString(1, pro);
            ps.setString(2, cat);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //insert data into product table
    public void insert(int id, String pname, String cname, int qty, double price) {
        String sql = "insert into product values(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, pname);
            ps.setString(3, cname);
            ps.setInt(4, qty);
            ps.setDouble(5, price);
            int result = ps.executeUpdate();
            if (result <= 0) {
                throw new SQLException("Failed to add product.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to add product: " + ex.getMessage(), ex);
        }
    }

    //update product data
    public void update(int id, String pname, String cname, int qty, double price) {
        String sql = "update product set pname = ?, cname = ?, pqty = ?, pprice = ? where pid = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pname);
            ps.setString(2, cname);
            ps.setInt(3, qty);
            ps.setDouble(4, price);
            ps.setInt(5, id);
            int result = ps.executeUpdate();
            if (result <= 0) {
                throw new SQLException("Failed to update product.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to update product: " + ex.getMessage(), ex);
        }
    }

    //delete product data
    public void delete(int id) {
        try {
            ps = con.prepareStatement("delete from product where pid = ?");
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result <= 0) {
                throw new SQLException("Failed to delete product.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Failed to delete product: " + ex.getMessage(), ex);
        }
    }

    //get product data
    public void getProductsValue(JTable table, String search) {
        String sql = "select * from product where concat(pid, pname, cname) like ? order by pid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getDouble(5);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
