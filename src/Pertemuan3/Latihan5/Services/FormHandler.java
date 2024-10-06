package Pertemuan3.Latihan5.Services;

import Pertemuan3.Latihan5.Interface.MActionListener;
import Pertemuan3.Latihan5.Interface.MChangeListener;
import Pertemuan3.Latihan5.Interface.MItemListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormHandler implements MActionListener, MItemListener, MChangeListener {
    private boolean isCheckBoxSelected;

    @Override
    public void handlerButtonSaveAction(ActionEvent e,
                                        JTextField kotakNama,
                                        JTextField kotakNoHp,
                                        JRadioButton radio1,
                                        JRadioButton radio2,
                                        JCheckBox checkBox,
                                        JList<String> listJenisTabungan,
                                        JSlider slider, JTextArea areaBiodata,
                                        JSpinner spinner) {
        boolean passwordBerhasil = false;

        while (!passwordBerhasil) {
            JPasswordField passwordField = new JPasswordField();
            int masukkanPassword = JOptionPane.showConfirmDialog(
                    null,
                    passwordField,
                    "Masukkan Password Anda,",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (masukkanPassword == JOptionPane.OK_OPTION) {
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);

                if (passwordString.equals("PasswordKu")) {
                    passwordBerhasil = true;

                    String nama = kotakNama.getText();
                    String noHp = kotakNoHp.getText();
                    Date tanggalLahir = (Date) spinner.getValue();
                    String tanggal = new SimpleDateFormat("dd-MM-yyyy").format(tanggalLahir);
                    String JK = "";

                    if (radio1.isSelected()) {
                        JK = radio1.getText();
                    }
                    if (radio2.isSelected()) {
                        JK = radio2.getText();
                    }

                    String dataTabungan;
                    if (listJenisTabungan.getSelectedValue() != null) {
                        dataTabungan = listJenisTabungan.getSelectedValue();
                    } else {
                        dataTabungan = "Tidak ada tabungan yang dipilih";
                    }

                    String wnaStatus;
                    if (isCheckBoxSelected) {
                        wnaStatus = "WNA : Ya";
                    } else {
                        wnaStatus = "WNA : Bukan";
                    }

                    int frekuensiTransaksi = slider.getValue();

                    String biodata = "Nama: " + nama +
                            "\nNomor Telepon: " + noHp +
                            "\n" + "Jenis Kelamin : " +
                            JK + "\n" +
                            wnaStatus + "\n" + "Tabungan yang dipilih : " +
                            dataTabungan + "\n" + "Frekuensi Transaksi : " +
                            frekuensiTransaksi + "$" + "\n" + "Tanggal Lahir : " +
                            tanggal + "\n" + "=".repeat(30) + "\n";

                    areaBiodata.append(biodata);

                    JOptionPane.showMessageDialog(null,
                            "Berhasil Menambah Data",
                            "tittle", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Password Anda Salah",
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                break;
            }
        }


    }

    @Override
    public void handleResetMenuAction(ActionEvent e,
                                      JTextField kotakNama,
                                      JTextField kotakNoHp,
                                      JRadioButton radio1,
                                      JRadioButton radio2,
                                      JCheckBox checkBox,
                                      JList<String> listJenisTabungan,
                                      JTextArea areaBiodata) {
        int konfirmasi = JOptionPane.showConfirmDialog(null,
                "Apakah Anda yakin?",
                "Konfirmasi Reset Data", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            boolean passwordBerhasil = false;

            while (!passwordBerhasil) {
                JPasswordField passwordField = new JPasswordField();
                int masukkanPassword = JOptionPane.showConfirmDialog(
                        null,
                        passwordField,
                        "Masukkan Password Anda,",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (masukkanPassword == JOptionPane.OK_OPTION) {
                    char[] password = passwordField.getPassword();
                    String passwordString = new String(password);

                    if (passwordString.equals("PasswordKu")) {
                        passwordBerhasil = true;
                        kotakNama.setText("");
                        kotakNoHp.setText("");
                        radio1.setSelected(false);
                        radio2.setSelected(false);
                        checkBox.setSelected(false);
                        listJenisTabungan.clearSelection();
                        areaBiodata.setText("");

                        JOptionPane.showMessageDialog(null,
                                "Berhasil Mereset Field",
                                "title", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Password anda salah",
                                "peringatan", JOptionPane.WARNING_MESSAGE );
                    }
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public void handleExitMenuAction(ActionEvent e) {
        System.exit(0);
    }


    @Override
    public void handleSliderChange(ChangeEvent e) {

    }

    @Override
    public void handleItemStateChange(ItemEvent e) {
        isCheckBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
    }
}

