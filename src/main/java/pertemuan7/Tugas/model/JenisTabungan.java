package main.java.pertemuan7.Tugas.model;

public class JenisTabungan {
    private int id;
    private String label;

    public JenisTabungan(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "JenisTabungan{" +
                "id=" + id +
                ", label='" + label + '\'' + '}';
    }
}
