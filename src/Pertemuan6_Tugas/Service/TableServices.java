package Pertemuan6_Tugas.Service;

import Pertemuan6_Tugas.GUI.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class TableServices {

    private FormPanel formPanel;
    private DefaultTableModel tableModel;

    private String nama;
    private String noHp;
    private String jenisKelamin;
    private String wargaNegaraAsing;
    private String jenisTabungan;
    private String frekuensiTransaksi;
    private String tanggalLahir;
    private String pekerjaan;
    private String deskripsi;

    public TableServices(DefaultTableModel tableModel, FormPanel formPanel) {
        this.tableModel = tableModel;
        this.formPanel = formPanel;
    }
public DefaultTableModel getTableModel() {
        return tableModel;
}

    private boolean validateInputs() {
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(formPanel, "Nama tidak boleh kosong.");
            return false;
        }
        if (noHp.isEmpty()) {
            JOptionPane.showMessageDialog(formPanel, "No HP tidak boleh kosong.");
            return false;
        }
        if (jenisTabungan == null || jenisTabungan.isEmpty()) {
            JOptionPane.showMessageDialog(formPanel, "Jenis Tabungan harus dipilih.");
            return false;
        }
        if (frekuensiTransaksi.isEmpty() || frekuensiTransaksi.equals("0 Juta")) {
            JOptionPane.showMessageDialog(formPanel, "Frekuensi Transaksi tidak boleh kosong.");
            return false;
        }
        if (tanggalLahir.isEmpty()) {
            JOptionPane.showMessageDialog(formPanel, "Tanggal Lahir tidak boleh kosong.");
            return false;
        }
        if (pekerjaan == null || pekerjaan.isEmpty()) {
            JOptionPane.showMessageDialog(formPanel, "Pekerjaan tidak boleh kosong.");
            return false;
        }
        return true;
    }


    public void tambahDataKeTabel() {
        nama = formPanel.getKotakNama().getText().trim();
        noHp = formPanel.getKotakNoHp().getText().trim();
        jenisKelamin = formPanel.getRadioPria().isSelected() ? "Pria" : "Wanita";
        wargaNegaraAsing = formPanel.getCheckBox().isSelected() ? "Ya" : "Tidak";
        jenisTabungan = formPanel.getListJenisTabungan().getSelectedValue();
        frekuensiTransaksi = String.valueOf(formPanel.getSlider().getValue() + " Juta");
        tanggalLahir = formPanel.getSpinner().getValue().toString();
        pekerjaan = (String) formPanel.getComboBoxPekerjaan().getSelectedItem();
        deskripsi = formPanel.getAreaDeskripsi().getText().trim();

        if(validateInputs()) {
            System.out.println("Nama: " + nama);
            System.out.println("No HP: " + noHp);
            System.out.println("Jenis Kelamin: " + jenisKelamin);
            System.out.println("Warga Negara Asing: " + wargaNegaraAsing);
            System.out.println("Jenis Tabungan: " + jenisTabungan);
            System.out.println("Frekuensi Transaksi: " + frekuensiTransaksi);
            System.out.println("Tanggal Lahir: " + tanggalLahir);
            System.out.println("Pekerjaan: " + pekerjaan);
            System.out.println("Deskripsi: " + deskripsi);


        String[] data = {
              nama, noHp, jenisKelamin, wargaNegaraAsing, jenisTabungan, frekuensiTransaksi, tanggalLahir, pekerjaan, deskripsi
        };
        tableModel.addRow(data);
        System.out.println("baris ditambahkan " + tableModel.getRowCount());

        String biodata = String.format("Nama: %s\nNo HP: %s\nJenis Kelamin: %s\nWarga Negara Asing: %s\n" +
                "Jenis Tabungan: %s\nFrekuensi Transaksi: %s\nTanggal Lahir: %s\nPekerjaan: %s\nDeskripsi: %s",
                 nama, noHp, jenisKelamin, wargaNegaraAsing, jenisTabungan, frekuensiTransaksi, tanggalLahir, pekerjaan, deskripsi);
            saveDataToFile();
            formPanel.getAreaBiodata().setText(biodata);
            clearInput();
    }
    }

    public void saveDataToFile() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("D:\\Kuliah Sem5\\Praktikum Pemrograman 2\\PP2\\data.txt"));
            System.out.println("Menyimpan data...");

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Tidak ada data untuk disimpan.");
                return;
            }

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    row.append(tableModel.getValueAt(i, j)).append(", ");
                }
                String rowData = row.toString().replaceAll(", $", "");
                System.out.println("Row to save: " + rowData); // Debug print
                writer.write(rowData);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Data gagal disimpan: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void hapusDataDariTabel() {
        int barisDipilih = formPanel.getTable().getSelectedRow();
        if(barisDipilih != -1){
            tableModel.removeRow(barisDipilih);
        }else {
            JOptionPane.showMessageDialog(formPanel, "Silakan pilih data yang ingin dihapus.");
        }
    }




    public void clearInput() {
        formPanel.getKotakNama().setText("");
        formPanel.getKotakNoHp().setText("");
        formPanel.getRadioPria().setSelected(false);
        formPanel.getRadioWanita().setSelected(false);
        formPanel.getCheckBox().setSelected(false);
        formPanel.getListJenisTabungan().clearSelection();
        formPanel.getSlider().setValue(0);
        formPanel.getSpinner().setValue(new Date());
        formPanel.getComboBoxPekerjaan().setSelectedIndex(0);
        formPanel.getAreaDeskripsi().setText("");
    }


}
