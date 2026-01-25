package controller;

import javax.swing.JTable;
import dao.Base_DAO;
import dao.UserDao;
import model.User;
import dao.Statistics;

public class UserController {

    // Use polymorphism for basic CRUD
    private final Base_DAO<User> userDao = new UserDao();
    private final Statistics statistics = new Statistics();

    // For methods not in Base_DAO, cast to UserDao
    private UserDao getUserDao() {
        return (UserDao) userDao;
    }

    public int getMaxRow() {
        return getUserDao().getMaxRow();
    }

    public boolean isEmailExist(String email) {
        return getUserDao().isEmailExist(email);
    }

    public boolean isPhoneExist(String phone) {
        return getUserDao().isPhoneExist(phone);
    }

    public boolean insert(int id, String username, String email, String password, String phone, String seq, String ans, String address) {
        User u = new User(id, username, email, password, phone, seq, ans, address);
        try {
            userDao.insert(u);
            return true;
        } catch (Exception ex) {
            // Let the View handle the message, or log if needed
            throw ex;
        }
    }

    public boolean update(int id, String username, String email, String password, String phone, String seq, String ans, String address) {
        User u = new User(id, username, email, password, phone, seq, ans, address);
        try {
            userDao.update(u);
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean delete(int id) {
        try {
            userDao.delete(id);
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String[] getUserValue(int id) {
        return getUserDao().getUserValue(id);
    }

    public int getUserId(String email) {
        return getUserDao().getUserId(email);
    }

    public void getUsersValue(JTable table, String search) {
        getUserDao().getUsersValue(table, search);
    }

    public void loadUserStats(int id) {
        statistics.user(id);
    }
}
