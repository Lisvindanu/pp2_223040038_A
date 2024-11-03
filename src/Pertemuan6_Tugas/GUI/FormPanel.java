package Pertemuan6_Tugas.GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Pertemuan6_Tugas.Listener.*;
import Pertemuan6_Tugas.Service.TableServices;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import javax.swing.table.DefaultTableModel;


public class FormPanel extends JPanel  {

    private JTextField kotakNama, kotakNoHp;
    private JTextArea areaBiodata, areaDeskripsi;
    public JRadioButton radioPria;
    public JRadioButton radioWanita;
    public JCheckBox checkBox;
    private JComboBox<String> comboBoxPekerjaan;
    public JList<String> listJenisTabungan;
    public JSlider slider;
    private JSpinner spinner;
    public JButton addButton;
    public JButton removeButton;
    private JTable table;
    private DefaultTableModel tableModel;

//    public DefaultTableModel getTableModel() {
//        return tableModel;
//    }

    public FormPanel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        System.out.println("Model identity in FormPanel: " + System.identityHashCode(tableModel));
        setLayout(new net.miginfocom.swing.MigLayout("wrap 2", "[grow, fill]10[grow, fill]", "[]10[]"));

        //inisialisasi
        kotakNama = new JTextField(15);
        kotakNoHp = new JTextField(15);
        areaDeskripsi = new JTextArea(5, 20);
        areaBiodata = new JTextArea(5, 20);
        areaBiodata.setEditable(false);
        radioPria = new JRadioButton("Pria");
        radioWanita = new JRadioButton("Wanita");
        checkBox = new JCheckBox("Ya");
        String[] opsiPekerjaan = {"Pelajar", "Pegawai", "Wiraswasta", "Bank"};
        comboBoxPekerjaan = new JComboBox<>(opsiPekerjaan);
        String[] jenisTabungan = {"Simpanan", "Tabungan", "Giro"};
        listJenisTabungan = new JList<> (jenisTabungan);
        listJenisTabungan.setVisibleRowCount(4);
        slider = new JSlider(0, 100, 0);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(5);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        spinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, "dd-MM-yyyy");
        spinner.setEditor(dateEditor);
        addButton = new JButton("Tambah Data");
        removeButton = new JButton("Hapus Data");

        //table
        table = new JTable(this.tableModel);
//        String[] namaKolom = {"Nama", "No Hp", "Jenis Kelamin", "Warga Negara Asing ?", "Jenis Tabungan", "Frekuensi Transaksi", "Tanggal Lahir", "Pekerjaan", "Deskripsi"};
//        tableModel = new DefaultTableModel(namaKolom, 0);
        table.setAutoCreateRowSorter(true);
        table.setPreferredScrollableViewportSize(new Dimension(500,150));
        table.setFillsViewportHeight(true);

        JScrollPane tableScroll = new JScrollPane(table);
        add(tableScroll, "span 2");

        //set Component
        add(new JLabel("Nama : "));
        add(kotakNama);
        add(new JLabel("No Hp : "));
        add(kotakNoHp);
        add(new JLabel("Jenis Kelamin : "));
        JPanel panelJK = new JPanel();
        ButtonGroup JKGroup = new ButtonGroup();
        JKGroup.add(radioPria);
        JKGroup.add(radioWanita);
        panelJK.add(radioPria);
        panelJK.add(radioWanita);
        add(panelJK);
        add(new JLabel("Warga Negara Asing ? : "));
        add(checkBox);
        add(new JLabel("Jenis Tabungan : "));
        add(new JScrollPane(listJenisTabungan));
        add(new JLabel("Frekuensi Transaksi:"));
        add(slider);
        slider.revalidate();
        slider.repaint();
        add(new JLabel("Tanggal Lahir:"));
        add(spinner);
        add(new JLabel("Pekerjaan:"));
        add(comboBoxPekerjaan);
        add(new JLabel("Deskripsi:"));
        add(new JScrollPane(areaDeskripsi));
        add(new JLabel("Biodata Output:"));
        add(new JScrollPane(areaBiodata));

        //panel Tombol
        JPanel panelTombol = new JPanel();
        panelTombol.add(addButton);
        panelTombol.add(removeButton);
        add(panelTombol, "span 2");
        add(tableScroll, "span 2");



//        TableServices tableServices = new TableServices(tableModel, this);

        //listener
        ActionHandler actionHandler = new ActionHandler(this, tableModel);
        addButton.addActionListener(actionHandler::handleAction);
        removeButton.addActionListener(actionHandler::handleAction);

        itemHandler itemHandler = new itemHandler(this);
        radioPria.addItemListener(itemHandler::handleItemChange);
        radioWanita.addItemListener(itemHandler::handleItemChange);
        checkBox.addItemListener(itemHandler::handleItemChange);

        ListSelectionHandler listSelectionHandler = new ListSelectionHandler(this);
        listJenisTabungan.addListSelectionListener(listSelectionHandler::handleListSelection);

        ChangeHandler changeHandler = new ChangeHandler(this);
        slider.addChangeListener(changeHandler::handleChange);

    }

    public JTable getTable() {
        return table;
    }

    public JTextField getKotakNama() {
        return kotakNama;
    }

    public JTextField getKotakNoHp() {
        return kotakNoHp;
    }

    public JRadioButton getRadioPria() {
        return radioPria;
    }

    public JRadioButton getRadioWanita() {
        return radioWanita;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public JList<String> getListJenisTabungan() {
        return listJenisTabungan;
    }

    public JSlider getSlider() {
        return slider;
    }

    public JSpinner getSpinner() {
        return spinner;
    }

    public JComboBox<String> getComboBoxPekerjaan() {
        return comboBoxPekerjaan;
    }

    public JTextArea getAreaDeskripsi() {
        return areaDeskripsi;
    }

    public JTextArea getAreaBiodata() {
        return areaBiodata;
    }

    public static void main(String[] args) {
        // Setup the FlatLaf Arc Dark Orange theme
        FlatArcDarkOrangeIJTheme.setup();

        // Run the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Form Data");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FormPanel(new DefaultTableModel()));
            frame.pack();
            frame.setVisible(true);
        });
    }
}