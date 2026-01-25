package controller;

import dao.Statistics;

public class AdminController {

    private final Statistics statistics = new Statistics();

    public void loadAdminStats() {
        statistics.admin();
    }
}
