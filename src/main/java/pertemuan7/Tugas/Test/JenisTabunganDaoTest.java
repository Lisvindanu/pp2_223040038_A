package main.java.pertemuan7.Tugas.Test;

import main.java.pertemuan7.Tugas.dao.JenisTabunganDao;
import main.java.pertemuan7.Tugas.model.JenisTabungan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JenisTabunganDaoTest {
    private JenisTabunganDao JenisTabunganDao;

    @BeforeEach
    public void setUp() throws Exception {
        JenisTabunganDao = new JenisTabunganDao();
    }

    @Test
    public void TestaAddJenisTabungan() throws Exception {
        JenisTabungan JenisTabungan = new JenisTabungan(1, "Memancing Keributan");
        int result = JenisTabunganDao.addJenisTabungan(JenisTabungan);
        assertEquals(1, result, "Jenis Tabungan Should be added successfully.");
        System.out.println("Add Jenis Tabungan : " + JenisTabungan);
    }

    @Test
    public void TestaUpdateJenisTabungan() throws Exception {

    }
}
