package controller;

import dao.Base_DAO;
import dao.CategoryDao;

import model.Category;

import javax.swing.JTable;

public class CategoryController {

    private final Base_DAO<Category> cat = new CategoryDao();

    public boolean addCategory(int id, String cname, String desc) {
        Category category = new Category(id, cname, desc);
        try {
            cat.insert(category);
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean updateCategory(int id, String cname, String desc) {
        Category category = new Category(id, cname, desc);
        try {
            cat.update(category);
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean deleteCategory(int id) {
        try {
            cat.delete(id);
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int getMaxCategoryId() {
        // Need to cast to CategoryDao to access getMaxRow()
        return ((CategoryDao) cat).getMaxRow();
    }

    public boolean checkCategoryNameExists(String cname) {
        return ((CategoryDao) cat).isCategoryNameExist(cname);
    }

    public boolean checkCategoryIdExists(int id) {
        return ((CategoryDao) cat).isIdExist(id);
    }

    public void getCategoryById(JTable table, String search) {
        ((CategoryDao) cat).getCategoriesValue(table, search);
    }
}
