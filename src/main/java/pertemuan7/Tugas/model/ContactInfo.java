package main.java.pertemuan7.Tugas.model;

public class ContactInfo {
    private int id, userId;
    private String no_Hp, email, alamat;

    public ContactInfo(int id, String no_Hp, String email, String alamat,  int userId) {
        this.id = id;
        this.no_Hp = no_Hp;
        this.email = email;
        this.alamat = alamat;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNoHp() {
        return no_Hp;
    }

    public void setNoHp(String noHp) {
        this.no_Hp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "id=" + id +
                ", noHp='" + no_Hp + '\'' +
                ", email='" + email + '\'' +
                ", alamat='" + alamat + '\'' +
                ", userId=" + userId +
                '}';
    }
}
