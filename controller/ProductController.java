package controller;

import dao.ProductDao;
import javax.swing.*;

public class ProductController {

    private final ProductDao prod = new ProductDao();

    public boolean addProduct(int id, String pname, String cname, int qty, double price) {
        try {
            prod.insert(id, pname, cname, qty, price);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateProduct(int id, String pname, String cname, int qty, double price) {
        try {
            prod.update(id, pname, cname, qty, price);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteProduct(int id) {
        try {
            prod.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void loadProducts(JTable table, String keyword) {
        prod.getProductsValue(table, keyword);
    }

    public int getMaxProductId() {
        return prod.getMaxRow();
    }

    public int countCategories() {
        return prod.countCategories();
    }

    public String[] getCategories() {
        return prod.getCat();
    }

    public boolean checkProId(int id) {
        return prod.isIdExist(id);
    }

    public boolean checkProandCat(String pro, String cat) {
        return prod.isProCatExist(pro, cat);
    }

    public void ProductValues(JTable table, String search) {
        prod.getProductsValue(table, search);
    }
}
