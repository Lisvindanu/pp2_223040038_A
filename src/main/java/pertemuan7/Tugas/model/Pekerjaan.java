package main.java.pertemuan7.Tugas.model;

public class Pekerjaan {
    private int id;
    private String label;

    public Pekerjaan(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pekerjaan{" +
                "id=" + id +
                ", label='" + label + '\'' + '}';
    }
}
