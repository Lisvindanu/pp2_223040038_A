package main.java.Pertemuan6_Tugas.Service;

import main.java.Pertemuan6_Tugas.GUI.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        System.out.println("Model identity in TableServices: " + System.identityHashCode(tableModel));
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