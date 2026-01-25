package controller;

import dao.PurchaseDao;
import javax.swing.JTable;

public class PurchaseController {

    private final PurchaseDao purchase = new PurchaseDao();

    public void addPurchase(int id, int uid, String uName, String uPhone, int pid, String pname, int qty, double price, double total, String pDate, String address, String rDate, String supplier, String status) {
        purchase.insert(id, uid, uName, uPhone, pid, pname, qty, price, total, pDate, address, rDate, supplier, status);
    }

    public boolean refundPurchase(int id) {
        try {
            purchase.refund(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String[] getUserValues(String email) {
        return purchase.getUserValue(email);
    }

    public int getQty(int pId) {
        return purchase.getQty(pId);
    }

    public void updateQty(int productId, int qty) {
        purchase.qtyUpdate(productId, qty);
    }

    public int getMaxPurchaseRow() {
        return purchase.getMaxRow();
    }

    public String[] getUserData(String email) {
        return purchase.getUserValue(email);
    }

    public boolean setDateStatus(int id, String date, String status) {
        try {
            purchase.setDateStatus(id, date, status);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void getProductsValue(JTable table, String search, int uid) {
        purchase.getProductsValue(table, search, uid);
    }

    public void getProductsValue(JTable table, String search) {
        purchase.getProductsValue(table, search);
    }

    public void getOntheWayProducts(JTable table, String search, String supplier) {
        purchase.getOntheWayProducts(table, search, supplier);
    }

    public void getSuppDeliProducts(JTable table, String search, String supplier) {
        purchase.getSuppDeliProducts(table, search, supplier);
    }

    public void transaction(JTable table, String search) {
        purchase.transaction(table, search);
    }

    public void loadPurchases(javax.swing.JTable table, String keyword, int userId) {
        purchase.getProductsValue(table, keyword, userId);
    }

    public boolean setSuppStatus(int id, String supp, String status) {
        try {
            purchase.setSuppStatus(id, supp, status);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
