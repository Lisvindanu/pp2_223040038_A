package main.java.pertemuan7.Tugas.Test;

import main.java.pertemuan7.Tugas.dao.PekerjaanDao;
import main.java.pertemuan7.Tugas.model.Pekerjaan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PekerjaanDaoTest {
    PekerjaanDao pekerjaanDao = new PekerjaanDao();

    @BeforeEach public void setUp() throws Exception {
        pekerjaanDao = new PekerjaanDao();
    }

    @Test
    public void insertPekerjaan() throws Exception {
        Pekerjaan pekerjaan = new Pekerjaan(0, "Makan Orang");
        int result = pekerjaanDao.addPekerjaan(pekerjaan);
        assertEquals(1,result, "Data added");
        System.out.println("Pekerjaan " + pekerjaan);
    }

    @Test
    public void getPekerjaanMap() throws Exception {
        Map<Integer, String> pekerjaanMap = pekerjaanDao.getPekerjaanMap();
        assertNotNull(pekerjaanMap, "Gaboleh Kosong");

//        assertTrue(pekerjaanMap.containsKey(1), "Map should contain key 1");
//        assertEquals("Software Engineer", pekerjaanMap.get(1), "Label for ID 1 should be 'Software Engineer'");
//
//        assertTrue(pekerjaanMap.containsKey(2), "Map should contain key 2");
//        assertEquals("Designer", pekerjaanMap.get(2), "Label for ID 2 should be 'Designer'");

        // Print out the map for visual verification
        System.out.println("Pekerjaan Map main.java.Test: " + pekerjaanMap);
    }
}
