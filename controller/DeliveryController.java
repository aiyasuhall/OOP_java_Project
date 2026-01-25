package controller;

import dao.Statistics;
import dao.PurchaseDao;
import dao.SupplierDao;

import javax.swing.JTable;

public class DeliveryController {

    Statistics statistics = new Statistics();
    SupplierDao supplierDao = new SupplierDao();
    PurchaseDao purchaseDao = new PurchaseDao();

    public void getOntheWayProducts(JTable table, String search, String supplier) {
        purchaseDao.getOntheWayProducts(table, search, supplier);
    }

    public void getSupplierData(JTable table, String search) {
        supplierDao.getSupplierValue(table, search);
    }

    public void getStatistics(String name) {
        statistics.supplier(name);
    }
}
