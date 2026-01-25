package controller;

import dao.Base_DAO;
import dao.SupplierDao;
import dao.Statistics;
import model.Supplier;
import javax.swing.JTable;

public class SupplierController {

    // Use polymorphism for basic CRUD
    private final Base_DAO<Supplier> supplierDao = new SupplierDao();
    private final Statistics statistics = new Statistics();

    // For methods not in Base_DAO, cast to SupplierDao
    private SupplierDao getSupplierDao() {
        return (SupplierDao) supplierDao;
    }

    public int getMaxRow() {
        return getSupplierDao().getMaxRow();
    }

    public boolean isEmailExist(String email) {
        return getSupplierDao().isEmailExist(email);
    }

    public boolean isPhoneExist(String phone) {
        return getSupplierDao().isPhoneExist(phone);
    }

    public boolean isUsernameExist(String username) {
        return getSupplierDao().isUsernameExist(username);
    }

    public boolean addSupplier(int id, String username, String email, String password, String phone, String address) {
        Supplier supplier = new Supplier(id, username, email, password, phone, address);
        try {
            supplierDao.insert(supplier);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateSupplier(int id, String username, String email, String password, String phone, String address) {
        Supplier supplier = new Supplier(id, username, email, password, phone, address);
        try {
            supplierDao.update(supplier);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void getSupplierValues(JTable table, String search) {
        getSupplierDao().getSupplierValue(table, search);
    }

    public boolean deleteSupplier(int id) {
        try {
            supplierDao.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public int getSupplierId(String email) {
        return getSupplierDao().getSupplierId(email);
    }

    public String getSupplierName(String email) {
        return getSupplierDao().getSupplierName(email);
    }

    public String[] getSupplierValue(int id) {
        return getSupplierDao().getSupplierValue(id);
    }

    public int countSuppliers() {
        return getSupplierDao().countSuppliers();
    }

    public String[] getSuppliers() {
        return getSupplierDao().getSuppliers();
    }

    public void loadSupplierStats(String name) {
        statistics.supplier(name);
    }
}
