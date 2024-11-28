package main.java.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.pertemuan7.Tugas.dao.UserDao;
import main.java.pertemuan7.Tugas.model.ContactInfo;
import main.java.pertemuan7.Tugas.model.User;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        userDao = new UserDao();
    }

    @Test
    public void testAddUser() throws SQLException {
        ContactInfo contactInfo = new ContactInfo(0, "08123456789", "test@example.com", "Jl. main.java.Test", 0);
        User user = new User(0, "sasuke", "1990-01-01", 5, false, 1, 1, null, null);
        user.setContactInfo(contactInfo);

        int result = userDao.addUser(user);
        assertEquals(1, result, "User should be added successfully.");
        System.out.println("Add User main.java.Test: User added successfully with result: " + result);
    }

    @Test
    public void testUpdateUser() throws SQLException {
        int userId = 5;

        User userToUpdate = userDao.getUserById(userId);
        assertNotNull(userToUpdate, "User should not be null");

        ContactInfo originalContactInfo = userToUpdate.getContactInfo();
        assertNotNull(originalContactInfo, "ContactInfo should not be null");


        // Perbarui data pengguna dan contact info
        userToUpdate.setNama("Naruto User");
        userToUpdate.setWna(true);
        originalContactInfo.setNoHp("08123456780");
        originalContactInfo.setEmail("updated@example.com");
        originalContactInfo.setAlamat("Jl. Updated");
        userToUpdate.setContactInfo(originalContactInfo);

        // Panggil metode update
        int updateResult = userDao.updateUser(userToUpdate);
        assertEquals(1, updateResult, "User should be updated successfully.");

        // Verifikasi perubahan
        User updatedUser = userDao.getUserById(userId);
        assertNotNull(updatedUser, "Updated user should not be null.");
        ContactInfo updatedContactInfo = updatedUser.getContactInfo();
        assertNotNull(updatedContactInfo, "Updated ContactInfo should not be null.");

        assertEquals("Naruto User", updatedUser.getNama(), "User name should be updated.");
        assertEquals("08123456780", updatedContactInfo.getNoHp(), "User phone number should be updated.");
        assertEquals("updated@example.com", updatedContactInfo.getEmail(), "User email should be updated.");
        assertEquals("Jl. Updated", updatedContactInfo.getAlamat(), "User address should be updated.");

        System.out.println("Update User main.java.Test: User updated successfully to " + updatedUser);


    }

    @Test
    public void testGetAllUsers() throws SQLException {
        List<User> users = userDao.getAllUser();
        assertNotNull(users, "User list should not be null.");
        assertTrue(users.size() > 0, "There should be at least one user.");

        System.out.println("Get All Users main.java.Test:");
        for (User user : users) {
            String formattedUser = String.format(
                    "User { id=%d, nama='%s', tanggalLahir='%s', pekerjaanId=%d, jenisTabunganId=%d," +
                            " frekuensiTransaksi=%d, wna=%b, pekerjaan='%s', jenisTabungan='%s'," +
                            " ContactInfo { no_Hp='%s', email='%s', alamat='%s' } }",
                    user.getId(), user.getNama(), user.getTanggalLahir(), user.getPekerjaanId(),
                    user.getJenisTabunganId(), user.getFrekuensiTransaksi(), user.isWna(),
                    user.getPekerjaanLabel(), user.getJenisTabunganLabel(),
                    user.getContactInfo().getNoHp(), user.getContactInfo().getEmail(), user.getContactInfo().getAlamat()
            );
            System.out.println("  - " + formattedUser);
        }
    }

    @Test
    public void testGetUserById() throws SQLException {
        int userId = 11;
        User user = userDao.getUserById(userId);
        assertNotNull(user, "User should not be null.");
        assertEquals(userId, user.getId(), "User ID should match.");
        assertNotNull(user.getContactInfo(), "ContactInfo should not be null.");

        System.out.println("Get User By ID main.java.Test: Retrieved user: " + user);
    }

    @Test
    public void testDeleteUser() throws SQLException {
        int userId = 4;
        int result = userDao.deleteUser(userId);
        assertEquals(1, result, "User should be deleted successfully.");
        System.out.println("Delete User main.java.Test: User with ID " + userId + " deleted successfully with result: " + result);
    }
}
