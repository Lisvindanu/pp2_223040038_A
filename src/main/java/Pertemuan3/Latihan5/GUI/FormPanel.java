package main.java.Pertemuan3.Latihan5.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class FormPanel extends JPanel {
    private JLabel headerLabel = new JLabel("Form Biodata", JLabel.CENTER);
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();

    private JTextField kotakNama;
    private JTextField kotakNoHp;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private ButtonGroup bg;
    private JCheckBox checkBox;
    private JList <String> listJenisTabungan;
    private DefaultListModel<String> listModel;
    private JSlider slider;
    private JSpinner spinner;

    public FormPanel() {
        this.setLayout(layout);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10,10,10);

        //header Label
        gbc.gridx = 7;
        gbc.gridy = -1;
        gbc.gridwidth = 2;
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headerLabel, gbc);


        //field nama
       gbc.gridx = 0;
       gbc.gridy = 1;
       gbc.gridwidth = 1;
       add(new JLabel("Nama : "), gbc);

       gbc.gridx = 1;
        kotakNama = new JTextField(20);
       add(kotakNama, gbc);

        // field no hp
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Nomor Hp : "), gbc);

        gbc.gridx = 1;
        kotakNoHp = new JTextField(20);
        add(kotakNoHp, gbc);

        // radio
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Jenis Kelamin : "), gbc);

        gbc.gridx = 1;
        JPanel jenisKelaminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radio1 = new JRadioButton("Laki-Laki");
        radio2 = new JRadioButton("Perempuan");
        bg = new ButtonGroup();
        bg.add(radio1);
        bg.add(radio2);
        jenisKelaminPanel.add(radio1);
        jenisKelaminPanel.add(radio2);
        add(jenisKelaminPanel, gbc);

        // checkbox
        gbc.gridx = 1;
        gbc.gridy = 4;
        checkBox = new JCheckBox("Warga Negara Asing");
        add(checkBox, gbc);


        //JList tabungan
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Jenis Tabungan : "), gbc);

        gbc.gridx = 1;

        listModel = new DefaultListModel<>();
        listModel.addElement("Tabungan Rumah");
        listModel.addElement("Tabungan Pendidikan");
        listModel.addElement("Tabungan Hari tua");
        listModel.addElement("Tabungan Anak");

        listJenisTabungan = new JList<>(listModel);
        listJenisTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(listJenisTabungan);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(listScrollPane, gbc);

        // Slider
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(new JLabel("frekuensi transaksi perbulan : "), gbc);
        gbc.gridx = 1;
        slider = new JSlider(0, 100, 0);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(5);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        add(slider, gbc);

        //Spinner
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Tanggal Lahir : " ), gbc);

        gbc.gridx = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1990);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);

        SpinnerDateModel model = new SpinnerDateModel(calendar.getTime(),
                null,
                null, calendar.DAY_OF_MONTH);
        spinner = new JSpinner(model);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner,
                "dd-MM-yyyy");
        spinner.setEditor(dateEditor);
        add(spinner, gbc);

    }


    public JTextField getKotakNama() {
        return kotakNama;
    }

    public JTextField getKotakNoHp() {
        return kotakNoHp;
    }

    public JRadioButton getRadio1() {
        return radio1;
    }

    public JRadioButton getRadio2() {
        return radio2;
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
}
