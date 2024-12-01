package main.java.pertemuan8.Tugas.util;

public class RoleUtil {
    public static String getRoleByUsername(String username) {
        if("Danu".equalsIgnoreCase(username)) {
            return "Admin";
        }
        return "User";
    }
}
