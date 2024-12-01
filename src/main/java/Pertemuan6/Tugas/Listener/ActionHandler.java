package main.java.Pertemuan6.Tugas.Listener;

import main.java.Pertemuan6.Tugas.GUI.FormPanel;
import main.java.Pertemuan6.Tugas.Interface.MActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Date;

public class ActionHandler implements MActionListener {
    private FormPanel formPanel;
    private DefaultTableModel tableModel;

    public ActionHandler(FormPanel formPanel, DefaultTableModel tableModel) {
        this.formPanel = formPanel;
        this.tableModel = tableModel;
        System.out.println("Model identity in ActionHandler: " + System.identityHashCode(tableModel));
    }

    @Override
    public void handleAction(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();

        switch (sourceButton.getText()) {
            case "Tambah Data":
                addDataToTable();
                break;
            case "Hapus Data":
                removeSelectedData();
                break;
            default:
                System.out.println("Unrecognized action.");
        }
    }

    private void addDataToTable() {
        String nama = formPanel.getKotakNama().getText().trim();
        String noHp = formPanel.getKotakNoHp().getText().trim();
        String jenisKelamin = formPanel.getRadioPria().isSelected() ? "Pria" : "Wanita";
        String wargaNegaraAsing = formPanel.getCheckBox().isSelected() ? "Ya" : "Tidak";
        String jenisTabungan = formPanel.getListJenisTabungan().getSelectedValue();
        String frekuensiTransaksi = formPanel.getSlider().getValue() + " Juta";
        String tanggalLahir = formPanel.getSpinner().getValue().toString();
        String pekerjaan = (String) formPanel.getComboBoxPekerjaan().getSelectedItem();
        String deskripsi = formPanel.getAreaDeskripsi().getText().trim();

        System.out.println("Adding Data: " + nama + ", " + noHp + ", " + jenisKelamin +
                ", " + wargaNegaraAsing + ", " + jenisTabungan + ", " +
                frekuensiTransaksi + ", " + tanggalLahir + ", " + pekerjaan + ", " + deskripsi);

        if (nama.isEmpty() || noHp.isEmpty() || jenisTabungan == null || pekerjaan.isEmpty()) {
            JOptionPane.showMessageDialog(formPanel, "Please fill in all required fields.");
            return;
        }

        String[] rowData = {
                nama, noHp, jenisKelamin, wargaNegaraAsing, jenisTabungan,
                frekuensiTransaksi, tanggalLahir, pekerjaan, deskripsi
        };

        SwingUtilities.invokeLater(() -> {
            tableModel.addRow(rowData);
            System.out.println("Row added. Current row count: " + tableModel.getRowCount());

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    System.out.print(tableModel.getValueAt(i, j) + " ");
                }
                System.out.println();
            }

            tableModel.fireTableDataChanged();
            refreshTable();
        });

        displayBiodata(nama, noHp, jenisKelamin, wargaNegaraAsing, jenisTabungan,
                frekuensiTransaksi, tanggalLahir, pekerjaan, deskripsi);

        JOptionPane.showMessageDialog(formPanel, "Data added successfully!");
    }

    private void displayBiodata(String... data) {
        String biodata = String.format(
                "Nama: %s\nNo HP: %s\nJenis Kelamin: %s\nWarga Negara Asing: %s\n" +
                        "Jenis Tabungan: %s\nFrekuensi Transaksi: %s\nTanggal Lahir: %s\nPekerjaan: %s\nDeskripsi: %s",
                (Object[]) data
        );
        formPanel.getAreaBiodata().setText(biodata);
    }

    private void removeSelectedData() {
        int selectedRow = formPanel.getTable().getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(formPanel, "Selected data removed!");
        } else {
            JOptionPane.showMessageDialog(formPanel, "Please select a row to remove.");
        }
    }

    private void clearFormInputs() {
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

    private void refreshTable() {
        formPanel.getTable().revalidate();
        formPanel.getTable().repaint();
    }
}
