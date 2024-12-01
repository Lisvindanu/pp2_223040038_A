package main.java.pertemuan7.Tugas.Test;

import main.java.pertemuan7.Tugas.dao.ContactInfoDao;
import main.java.pertemuan7.Tugas.model.ContactInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactInfoDaoTest {
    private ContactInfoDao contactInfoDao;

    @BeforeEach
    public void setUp() throws Exception {
        contactInfoDao = new ContactInfoDao();
    }

    @Test
    public void insert() throws Exception {
        ContactInfo contactInfo = new ContactInfo(0, "082254223714", "Naruto@gmail.com", "Jl setbut", 5);
        int result = contactInfoDao.addContactInfo(contactInfo);
        assertEquals(1, result, "Jenis Tabungan Should be added successfully.");
        System.out.println("Add Jenis Tabungan : " + contactInfo);
    }
}
