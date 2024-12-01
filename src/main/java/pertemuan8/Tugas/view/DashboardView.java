package main.java.pertemuan8.Tugas.view;


import main.java.pertemuan8.Tugas.util.RoleUtil;

public class DashboardView {
    public void showDashboard(String username) {
        String role = RoleUtil.getRoleByUsername(username);

        if ("admin".equalsIgnoreCase(role)) {
            System.out.println("Welcome, Admin!");
            // Tampilkan menu untuk admin
            showAdminMenu();
        } else {
            System.out.println("Welcome, User!");
            // Tampilkan menu untuk user
            showUserMenu();
        }
    }

    private void showAdminMenu() {
        System.out.println("1. Manage Products");
        System.out.println("2. Manage Users");
        System.out.println("3. View Reports");
    }

    private void showUserMenu() {
        System.out.println("1. View Products");
        System.out.println("2. Purchase Products");
    }
}
