package main.java.pertemuan7.Tugas.model;

public class User {
        private int id, pekerjaanId, jenisTabunganId, frekuensiTransaksi;
        private String nama, tanggalLahir;
        private boolean wna;

        private String pekerjaanLabel;
        private String jenisTabunganLabel;

        private ContactInfo contactInfo;

        public User(int id, String nama, String tanggalLahir,
                    int frekuensiTransaksi, boolean wna, int pekerjaanId, int jenisTabunganId,
                    String pekerjaanLabel, String jenisTabunganLabel) {
            this.id = id;
            this.nama = nama;
            this.tanggalLahir = tanggalLahir;
            this.frekuensiTransaksi = frekuensiTransaksi;

            this.wna = wna;
            this.pekerjaanId = pekerjaanId;
            this.jenisTabunganId = jenisTabunganId;
            this.pekerjaanLabel = pekerjaanLabel;
            this.jenisTabunganLabel = jenisTabunganLabel;
        }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }
        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getTanggalLahir() {
            return tanggalLahir;
        }
        public void setTanggalLahir(String tanggalLahir) {
            this.tanggalLahir = tanggalLahir;
        }

        public int getPekerjaanId() {
            return pekerjaanId;
        }
        public void setPekerjaanId(int pekerjaanId) {
            this.pekerjaanId = pekerjaanId;
        }
        public int getJenisTabunganId() {
            return jenisTabunganId;
        }
        public void setJenisTabunganId(int jenisTabunganId) {
            this.jenisTabunganId = jenisTabunganId;
        }


        public int getFrekuensiTransaksi() { return frekuensiTransaksi; }
        public void setFrekuensiTransaksi(int frekuensiTransaksi) {
            this.frekuensiTransaksi = frekuensiTransaksi;
        }

        public boolean isWna() {
            return wna;
        }
        public void setWna(boolean wna) {
            this.wna = wna;
        }

    public String getPekerjaanLabel() {
        return pekerjaanLabel;
    }
    public void setPekerjaanLabel(String pekerjaanLabel) {
        this.pekerjaanLabel = pekerjaanLabel;
    }

    public String getJenisTabunganLabel() {
        return jenisTabunganLabel;
    }
    public void setJenisTabunganLabel(String jenisTabunganLabel) {
        this.jenisTabunganLabel = jenisTabunganLabel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", tanggalLahir='" + tanggalLahir + '\'' +
                ", pekerjaanId=" + pekerjaanId +
                ", jenisTabunganId=" + jenisTabunganId +
                ", frekuensiTransaksi=" + frekuensiTransaksi +
                ", wna=" + wna +
                ", pekerjaanLabel='" + pekerjaanLabel + '\'' +
                ", jenisTabunganLabel='" + jenisTabunganLabel + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }
    }



