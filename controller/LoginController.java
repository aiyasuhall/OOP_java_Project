package controller;

import dao.SupplierDao;
import dao.UserDao;
import dao.AdminDao;

public class LoginController {

    private UserDao userDao;
    private SupplierDao supplierDao;
    private AdminDao adminDao;

    public LoginController() {
        userDao = new UserDao();
        supplierDao = new SupplierDao();
        adminDao = new AdminDao();
    }

    public boolean loginUser(String email, String password) {
        return userDao.validateUser(email, password);
    }

    public boolean loginSupplier(String email, String password) {
        return supplierDao.validateSupplier(email, password);
    }

    public boolean loginAdmin(String email, String password) {
        return adminDao.validateAdmin(email, password);
    }
}
