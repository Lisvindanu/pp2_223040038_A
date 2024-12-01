package main.java.pertemuan8.Tugas.main;
import main.java.pertemuan8.Tugas.view.DashboardView;

public class Main {
    public static void main(String[] args) {
        String username = "Danu"; // Contoh input username
        DashboardView dashboardView = new DashboardView();
        dashboardView.showDashboard(username);
    }
}
